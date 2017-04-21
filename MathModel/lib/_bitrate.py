#########################################
# Author: vvthang
# Created data: 20/apr/2017
#########################################
from __future__ import print_function
import numpy as np

class Bitrate(object):
	trace = None
	numOfVersion = 0
	QPArray = None
	path = ""
	SD = 2
	numOfSample = 0
	avrBitrate = None
	maxBitrate = None
	minBitrate = None
	#numOfInterval = 0
	quantizedBitrate = None

	def __init__(self, path, QPArray):
		print("\nBitrate.__init__(): Init bitrates from: " + path)
		self.QPArray = QPArray
		self.numOfVersion = QPArray.shape[0]
		self.path = path

	def readBitrate(self, startIdx, numOfSample):
		print("Bitrate.readBitrate(): Read bitrate of all versions from index " + 
			str(startIdx) + " to index " + str(startIdx + numOfSample))
		self.numOfSample = numOfSample
		self.trace = np.zeros((self.numOfVersion, numOfSample))
		for i in range(self.numOfVersion):
			file = open(self.path + "Q" + str(self.QPArray[i]) + ".txt", "r")
			lineCount = 0
			for line in file:
				lineCount = lineCount + 1
				if(lineCount < startIdx):
					continue
				if(lineCount - startIdx == numOfSample):
					break
				self.trace[i][lineCount - startIdx] = float(line)/self.SD
			file.close()
		self.avrBitrate = np.mean(self.trace, axis=1)
		self.maxBitrate = np.amax(self.trace, axis=1)
		self.minBitrate = np.amin(self.trace, axis=1)

	# Interval not use here
	# def quantizedByInterval(self,numOfInterval):
	# 	self.numOfInterval = numOfInterval
	# 	quantizedStep = (self.maxBitrate - self.minBitrate)/self.numOfInterval
	# 	idxArr = np.array([(i + 0.5) for i in range(self.numOfInterval)])
	# 	idxArr = idxArr[np.newaxis].T
	# 	self.quantizedBitrate = self.minBitrate + quantizedStep*idxArr






