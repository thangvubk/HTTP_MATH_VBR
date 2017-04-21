#########################################
# Author: vvthang
# Created data: 20/apr/2017
#########################################
from __future__ import print_function
import numpy as np
import math
from lib._bandwidth import Bandwidth
from lib._bitrate import Bitrate
from lib._buffer import Buffer
import lib._config


class DataModel(object):

	cost = None
	def __init__(self, bitrate, bandwidth, buf):
		self.bandwidth = bandwidth
		self.bitrate = bitrate
		self.buf = buf
		self.nVer = bitrate.numOfVersion
		self.nBW = bandwidth.numOfQuantizeStep
		self.nBuf = buf.numOfLevels
		self.nDec = self.nVer
		self.nState = self.nBW*self.nBuf*self.nVer

	# This follows the paper: 
	def buildCostWithOutTrans(self, alpha, beta, gamma):

		costWithoutTrans = np.zeros((self.nDec, self.nState))
		iState = -1
		for iBuf in range(self.nBuf):
			for iBW in range(self.nBW):
				for iVer in range(self.nVer):
					iState = iState + 1
					for iDec in range(self.nDec):
						brVar = self.bandwidth.quantizedLevels[iBW] - self.bitrate.avrBitrate[iDec];
						costSuffix = beta*(iVer - iDec) + gamma*((iBuf/self.buf.optLevel - 1)**2);
						#if brVar >= 0:
						costWithoutTrans[iDec][iState] = alpha*np.abs(brVar) + costSuffix
						#else:
						#	costWithoutTrans[iDec][iState] = alpha*(1-math.exp(brVar)) + costSuffix
		return costWithoutTrans

	def buildCost(self, alpha, beta, gamma):
		print("\nDataModel.buildCost(): {alpha, beta, gamma} = " + str(alpha) + " " + str(beta) + " " + str(gamma))
		costWithoutTrans = self.buildCostWithOutTrans(alpha, beta, gamma)
		self.cost = np.zeros((self.nDec, self.nState, self.nState))
		for iSource in range(self.nState):
			for iDest in range(self.nState):
				for iDec in range(self.nDec):
					destVer = iDest % self.nVer
					if(destVer == iDec): # Transition cost based on destVer and iDec
						self.cost[iDec][iSource][iDest] = costWithoutTrans[iDec][iSource]


	def buildProb(self):
		print("DataModel.buildProb():")
		# probExpanded expands nDestState into nBuf, nBW, and nVer to calculate iBuf for each VBR segment
		probExpanded = np.zeros((self.nDec, self.nState, self.nBuf, self.nBW, self.nVer)) 
		pBuf = 1 / self.bitrate.numOfSample # probability for buffer pBuf is distributed based on segment size
		
		iState = -1
		for iBuf in range(self.nBuf):
			for iBW in range(self.nBW):
				for iVer in range(self.nVer):
					iState = iState + 1
					for iBrSeg in range(self.bitrate.numOfSample): # for calculate iDestBuf in VBR
						for iDestBW in range(self.nBW):
							for iDestVer in range(self.nVer):
								if (self.bandwidth.transitProb[iBW][iDestBW] > 0): # for optimizing
									for iDec in range(self.nDec):
										if iDec == iDestVer:
											#calculate iDestBuf
											iDestBuf = iBuf + 1 - math.ceil(self.bitrate.trace[iDec][iBrSeg]/self.bandwidth.quantizedLevels[iDestBW]);
											if(iDestBuf < 0): #corner case
												iDestBuf = 0
											if(iDestBuf > self.nBuf -1): #corner case
												iDestBuf = self.nBuf -1 
											#calculate prob expanded
											probExpanded[iDec][iState][iDestBuf][iDestBW][iDestVer] += self.bandwidth.transitProb[iBW][iDestBW] * pBuf;
		self.prob = np.zeros((self.nDec, self.nState, self.nState))
		print("check point 1")
		#merge probExpanded
		self.prob = np.reshape(probExpanded,((self.nDec, self.nState, self.nState)));
		print("check point 2")













					
						



