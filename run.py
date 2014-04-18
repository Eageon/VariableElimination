
import os, sys

PGMs = ["17_4_s.binary.uai", "2_17_s.binary.uai", "2_28_s.binary.uai",
        "404.wcsp.uai", "54.wcsp.uai"]

SampleNumber = [100, 1000, 10000, 20000]

options = ["adaptive", "nonadaptive"]

for network in PGMs:
    for w in range(1, 6):
        for N in SampleNumber:
            for opt in options:
                os.system('java ImportanceSampling ' + w + ' ' + N + ' ' + opt)
            
    #os.system('java ImportanceSampling ')
