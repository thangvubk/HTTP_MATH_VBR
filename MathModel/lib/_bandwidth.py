#########################################
# Author: vvthang
# Created data: 20/apr/2017
#########################################
from __future__ import print_function
import numpy as np
import matplotlib.pyplot as plt
import sys

class Bandwidth(object):
	trace = None  #bandwidth trace
	quantizedLevels = None  #bandwidth discretized level
	quantizedStep = 0 
	minBW = 0
	maxBW = 0
	quantizedBandwidth = None
	numOfQuantizeStep = 0
	transitProb = None
	quantizedIdx = []
	numOfSample = 0
	def __init__(self, path):
		print("\nBandwidth.__init__(): init bandwidth from path " + path)
		self.path = path
		self.genTransitProb()


	def readBandwidthTrace(self, startIdx, numOfSample):
		print("Bandwidth.readBandwidthTrace(): start index: " + str(startIdx) + " numOfSample: " + str(numOfSample));	
		self.numOfSample = numOfSample
		self.startIdx = startIdx
		bwTrace = []
		file = open(self.path, "r")
		sampleCount = 0
		for line in file:
			sampleCount = sampleCount + 1
			if(sampleCount < startIdx):
				continue
			if(sampleCount - startIdx == numOfSample):
				break
			bwSample = line.split("\t")[1]
			bwTrace.append(int(bwSample))
		self.trace = np.array(bwTrace)


	def getQuantizedLevels(self, numOfQuantizeStep):
		if(len(self.trace) == 0):
			print("Error: trace is empty")
		else:
			self.minBW = min(self.trace)
			self.maxBW = max(self.trace)
			self.quantizedStep = (self.maxBW - self.minBW)/self.numOfQuantizeStep
			idxArr = np.array([(i + 0.5) for i in range(self.numOfQuantizeStep)])
			self.quantizedLevels = self.minBW + np.dot(self.quantizedStep, idxArr)


	def quantizeBandwidth(self, numOfQuantizeStep):
		print("Bandwidth.quantizeBandwidth(): numOfQuantizeStep: " + str(numOfQuantizeStep))
		self.numOfQuantizeStep = numOfQuantizeStep 
		self.getQuantizedLevels(numOfQuantizeStep)
		for i in range(self.numOfSample):
			quantizedIdxSample = (self.trace[i] - self.minBW)/self.quantizedStep
			if(quantizedIdxSample >= self.numOfQuantizeStep): #corner case
				quantizedIdxSample = self.numOfQuantizeStep - 1
			self.quantizedIdx.append(int(quantizedIdxSample))
		self.quantizedBandwidth = self.quantizedLevels[self.quantizedIdx]


	def genTransitProb(self):
		numOfSampleEachLevel = np.zeros(self.numOfQuantizeStep)
		for i in range(self.numOfSample-1):
			numOfSampleEachLevel[self.quantizedIdx[i]] = numOfSampleEachLevel[self.quantizedIdx[i]] + 1
		bwTransit = np.zeros((self.numOfQuantizeStep, self.numOfQuantizeStep))

		for i in range(self.numOfSample-1):
			bwTransit[self.quantizedIdx[i]][self.quantizedIdx[i+1]] = bwTransit[self.quantizedIdx[i]][self.quantizedIdx[i+1]] + 1

		numOfSampleEachLevel = numOfSampleEachLevel[np.newaxis]
		self.transitProb = bwTransit / numOfSampleEachLevel.T; 
		

	def plotBandwidth(self):
		timestamp = [i for i in range(self.numOfSample)]
		plt.plot(timestamp, self.trace, "b-")
		plt.plot(timestamp, self.quantizedBandwidth, "r-")
		plt.show()








