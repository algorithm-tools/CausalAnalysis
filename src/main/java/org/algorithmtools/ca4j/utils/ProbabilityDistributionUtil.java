package org.algorithmtools.ca4j.utils;

import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.ProbabilityDistribution;
import smile.stat.distribution.GaussianDistribution;

import java.util.*;

public class ProbabilityDistributionUtil {

    /** Cumulative indicator Converted to probability distribution
     * @param info
     * @return {@link ProbabilityDistribution}
     */
    public static ProbabilityDistribution transferPDForAddIndicator(IndicatorPairSeries info) {
        Map<String, Integer> logicIndexMap = new HashMap<>();
        int len = info.getCurrentList().size() + info.getComparisonList().size();
        double[] pArr = new double[len];
        double pTotal = 0.0;
        double[] qArr = new double[len];
        double qTotal = 0.0;
        String logicalIndex;
        int largeSize = Math.max(info.getCurrentList().size(), info.getComparisonList().size());
        for (int i = 0; i < largeSize; i++) {
            // Current
            if(info.getCurrentList().size() - 1 >= i){
                pTotal += info.getCurrentList().get(i).getValue();
                logicalIndex = info.getCurrentList().get(i).getLogicalIndex();
                if (logicIndexMap.containsKey(logicalIndex)) {
                    pArr[logicIndexMap.get(logicalIndex)] += info.getCurrentList().get(i).getValue();
                } else {
                    logicIndexMap.put(logicalIndex, i);
                    pArr[logicIndexMap.get(logicalIndex)] = info.getCurrentList().get(i).getValue();
                }

            }

            // Comparison
            if(info.getComparisonList().size() - 1 >= i){
                qTotal += info.getComparisonList().get(i).getValue();
                logicalIndex = info.getComparisonList().get(i).getLogicalIndex();
                if (logicIndexMap.containsKey(logicalIndex)) {
                    qArr[logicIndexMap.get(logicalIndex)] += info.getComparisonList().get(i).getValue();
                } else {
                    logicIndexMap.put(logicalIndex, i);
                    qArr[logicIndexMap.get(logicalIndex)] = info.getComparisonList().get(i).getValue();
                }

            }

        }

        for (int i = 0; i < pArr.length; i++) {
            pArr[i] = pArr[i] / pTotal;
            qArr[i] = qArr[i] / qTotal;
        }

        return new ProbabilityDistribution(pArr, qArr);
    }

    /**
     * Non-cumulative discrete metrics Conversion to probability distribution
     * @param info
     * @return {@link ProbabilityDistribution}
     */
    public static ProbabilityDistribution transferPDForUniqueDiscreteIndicator(IndicatorPairSeries info) {
        Map<Double, Integer> logicIndexMap = new HashMap<>();
        int len = info.getCurrentList().size() + info.getComparisonList().size();
        double[] pArr = new double[len];
        double pTotal = 0.0;
        double[] qArr = new double[len];
        double qTotal = 0.0;
        Double key;
        int largeSize = Math.max(info.getCurrentList().size(), info.getComparisonList().size());
        for (int i = 0; i < largeSize; i++) {
            // Current
            if(info.getCurrentList().size() - 1 >= i){
                pTotal += 1;
                key = info.getCurrentList().get(i).getValue();
                if (logicIndexMap.containsKey(key)) {
                    pArr[logicIndexMap.get(key)] += 1;
                } else {
                    logicIndexMap.put(key, i);
                    pArr[logicIndexMap.get(key)] = 1;
                }

            }

            // Comparison
            if(info.getComparisonList().size() - 1 >= i){
                qTotal += 1;
                key = info.getComparisonList().get(i).getValue();
                if (logicIndexMap.containsKey(key)) {
                    qArr[logicIndexMap.get(key)] += 1;
                } else {
                    logicIndexMap.put(key, i);
                    qArr[logicIndexMap.get(key)] = 1;
                }

            }

        }

        for (int i = 0; i < pArr.length; i++) {
            pArr[i] = pArr[i] / pTotal;
            qArr[i] = qArr[i] / qTotal;
        }

        return new ProbabilityDistribution(pArr, qArr);
    }

    /**
     * Non-cumulative continuous metrics Conversion to probability distribution
     * @param info
     * @return {@link ProbabilityDistribution}
     */
    public static ProbabilityDistribution transferPDForUniqueContinuityIndicator(IndicatorPairSeries info) {
        Map<String, Integer> logicIndexMap = new HashMap<>();
        int len = info.getCurrentList().size() + info.getComparisonList().size();
        double[] pArr = new double[len];
        double[] qArr = new double[len];
        String logicalIndex;
        int largeSize = Math.max(info.getCurrentList().size(), info.getComparisonList().size());
        for (int i = 0; i < largeSize; i++) {
            // Current
            if(info.getCurrentList().size() - 1 >= i){
                logicalIndex = info.getCurrentList().get(i).getLogicalIndex();
                if (logicIndexMap.containsKey(logicalIndex)) {
                    pArr[logicIndexMap.get(logicalIndex)] = info.getCurrentList().get(i).getValue();
                } else {
                    logicIndexMap.put(logicalIndex, i);
                    pArr[logicIndexMap.get(logicalIndex)] = info.getCurrentList().get(i).getValue();
                }

            }

            // Comparison
            if(info.getComparisonList().size() - 1 >= i){
                logicalIndex = info.getComparisonList().get(i).getLogicalIndex();
                if (logicIndexMap.containsKey(logicalIndex)) {
                    qArr[logicIndexMap.get(logicalIndex)] = info.getComparisonList().get(i).getValue();
                } else {
                    logicIndexMap.put(logicalIndex, i);
                    qArr[logicIndexMap.get(logicalIndex)] = info.getComparisonList().get(i).getValue();
                }

            }

        }

        // build GaussianDistribution
        double bandwidth = 0.5;
        List<GaussianDistribution> pKernels = new ArrayList<>();
        List<GaussianDistribution> qKernels = new ArrayList<>();
        for (int i = 0; i < pArr.length; i++) {
            pKernels.add(new GaussianDistribution(pArr[i], bandwidth));
            qKernels.add(new GaussianDistribution(qArr[i], bandwidth));
        }

        // get ProbabilityDistribution
        for (int i = 0; i < pArr.length; i++) {
            double pValue = pArr[i];
            double qValue = qArr[i];
            // TODO Reduce cycle
            pArr[i] = pKernels.stream().mapToDouble(kernel -> kernel.p(pValue)).average().orElse(0);
            qArr[i] = qKernels.stream().mapToDouble(kernel -> kernel.p(qValue)).average().orElse(0);
        }

        return new ProbabilityDistribution(pArr, qArr);
    }
}
