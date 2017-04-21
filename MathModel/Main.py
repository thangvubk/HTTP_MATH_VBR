#########################################
# Author: vvthang
# Created data: 20/apr/2017
#########################################

from __future__ import print_function
from lib._bandwidth import Bandwidth
from lib._bitrate import Bitrate
from lib._buffer import Buffer
from lib._data_model import DataModel
import lib._config as _config
import lib._util as _util
import mdptoolbox

import numpy as np
import matplotlib.pyplot as plt

np.set_printoptions(formatter={'float': '{: 0.3f}'.format})


# build data model
bandwidth 	= Bandwidth(_config.BW_PATH)
bandwidth.readBandwidthTrace(_config.BW_START_IDX, _config.BW_NUM_OF_SAMPLE)
bandwidth.quantizeBandwidth(_config.BW_NUM_OF_QUANTIZE_STEP)
bandwidth.genTransitProb()
#bandwidth.plotBandwidth()

bitrate 	= Bitrate(_config.BR_PATH, _config.BR_QP_ARRAY)
bitrate.readBitrate(_config.BR_START_IDX, _config.BR_NUM_OF_SAMPLE)

buf 		= Buffer(_config.BUF_NUM_OF_LEVEL,  _config.BUF_OPT_LEVEL)

dataModel 	= DataModel(bitrate, bandwidth, buf)
dataModel.buildCost(_config.ALPHA, _config.BETA, _config.GAMMA)
dataModel.buildProb()

# # run MDP Policy Iteration
print("\n")
print("===============================================")
print("          RUN MDP POLICY ITERATION             ")
print("===============================================")
print("\n")
discount 	= 0.9 # recommended discount for algorithm
#uncheck stochastic or 
solver		= mdptoolbox.mdp.PolicyIteration(dataModel.prob, -dataModel.cost, discount) # sign "-" is to convert reward to cost
solver.run()
print("Policy = ", solver.policy)

print("Exporting data")
_util.exportPolicy(solver.policy)
_util.exportBitrate(bitrate)
_util.exportQuantizedBandwidth(bandwidth)
_util.exportBandwidth(bandwidth)
print("Finish!!!")
