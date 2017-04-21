#########################################
# Author: vvthang
# Created data: 20/apr/2017
#########################################
import lib._config as _config

def exportPolicy(policy):
	file = open(_config.EXPORT_PATH + "policy_data.txt", "w")
	for item in policy:
		file.write("%s\n" %str(item+1)) # make policy start from 1
	file.close()

def exportBitrate(bitrate):
	for i in range(bitrate.numOfVersion):
		file = open(_config.EXPORT_PATH + "version" + str(i+1) + ".txt", "w") # make version start from 1
		for item in bitrate.trace[i]:
			file.write("%s\n" %str(item)) 
		file.close()

def exportQuantizedBandwidth(bandwidth):
	file = open(_config.EXPORT_PATH + "quantizedLevels.txt", "w")
	for item in bandwidth.quantizedLevels:
		file.write("%s\n" %str(item)) 
	file.close()

def exportBandwidth(bandwidth):
	file = open(_config.EXPORT_PATH + "bandwidth.txt", "w")
	for i in range(bandwidth.trace.shape[0]):
		file.write("%d\t%s\n" %(i + 1, str(bandwidth.trace[i]))) 
	file.close()