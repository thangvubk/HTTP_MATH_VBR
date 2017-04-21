#########################################
# Author: vvthang
# Created data: 20/apr/2017
#########################################
from __future__ import print_function
import numpy as np

class Buffer(object):

	numOfLevels = 0
	def __init__(self, numOfLevels, optLevel):
		print("\nBuffer.__init__(): init buffer with numOfLevel = " + 
			str(numOfLevels) + " and optimal level = " + str(optLevel))
		self.numOfLevels = numOfLevels
		self.optLevel = optLevel

	