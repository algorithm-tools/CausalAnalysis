package org.algorithmtools.ca4j.utils;

import org.algorithmtools.ca4j.comparator.IndicatorSeriesLogicIndexComparator;
import org.algorithmtools.ca4j.comparator.IndicatorSeriesValueComparator;
import org.algorithmtools.ca4j.enumtype.IndicatorStatType;
import org.algorithmtools.ca4j.pojo.IndicatorDivisionSeries;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.IndicatorSeries;
import org.algorithmtools.ca4j.pojo.ProbabilityDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.*;
import java.util.stream.Collectors;

public class IndicatorCalculateUtil {

    /**
     * calculate define range
     *
     * @param data          indicator series
     * @param iqrMultiplier iqrMultiplier
     * @param lowerQuantile lowerQuantile
     * @param upperQuantile upperQuantile
     * @return [lowerBound, upperBound]
     */
    public static double[] quantileBound(List<IndicatorSeries> data, double iqrMultiplier, double lowerQuantile, double upperQuantile) {
        // sort
        List<IndicatorSeries> sortList = sortAsc(data);

        // calculate quantile value
        double Q1 = quantile(sortList, lowerQuantile);
        double Q3 = quantile(sortList, upperQuantile);

        // calculate IQR
        double IQR = Q3 - Q1;

        // calculate bound
        double lowerBound = Q1 - iqrMultiplier * IQR;
        double upperBound = Q3 + iqrMultiplier * IQR;
        return new double[]{lowerBound, upperBound};
    }

    /**
     * interquartile range
     *
     * @param data indicator series
     * @return [lowerBound, upperBound]
     */
    public static double[] quantileIQR(List<IndicatorSeries> data) {
        return quantileBound(data, 1.5, 0.25, 0.75);
    }

    public static List<IndicatorSeries> excludeOutlier(List<IndicatorSeries> data) {
        double[] bound = quantileIQR(data);
        double lowerBound = bound[0];
        double upperBound = bound[1];

        // find normal series
        return data.stream().filter(v -> v.getValue() <= upperBound && v.getValue() >= lowerBound).collect(Collectors.toList());
    }

    public static double quantile(List<IndicatorSeries> sortedData, double quantile) {
        int n = sortedData.size();
        double index = quantile * (n - 1);
        int lowerIndex = (int) Math.floor(index);
        int upperIndex = (int) Math.ceil(index);

        if (lowerIndex == upperIndex) {
            return sortedData.get(lowerIndex).getValue();
        }

        double weight = index - lowerIndex;
        return (1 - weight) * sortedData.get(lowerIndex).getValue() + weight * sortedData.get(upperIndex).getValue();
    }

    public static DescriptiveStatistics initStatistic(DescriptiveStatistics stats, List<IndicatorSeries> dataList, List<Integer> excludeIndexList) {
        if (Objects.isNull(stats)) {
            stats = new DescriptiveStatistics();
        } else {
            stats.clear();
        }

        for (int i = 0; i < dataList.size(); i++) {
            if (excludeIndexList == null || !excludeIndexList.contains(i)) {
                stats.addValue(dataList.get(i).getValue());
            }
        }
        return stats;
    }

    public static List<IndicatorSeries> sortAsc(List<IndicatorSeries> data) {
        return data.stream().sorted(new IndicatorSeriesValueComparator()).collect(Collectors.toList());
    }

    /**
     * 根据logicIndex对齐两个list,并根据logicIndex按字典排序. 即，logicIndex不存在的，补充默认值，使得俩list对齐
     * <br/>before：current[a->1,b->2] comparison[b->3,c->4,d->5]
     * <br/>after：current[a->1,b->2,c->0,d->0] comparison[a->0,b->3,c->4,d->5]
     * @param pairSeries
     * @return
     */
    public static IndicatorPairSeries indicatorAlignThenSorted(IndicatorPairSeries pairSeries) {
        IndicatorPairSeries result = new IndicatorPairSeries(pairSeries.getIndicator(), pairSeries.getIndicatorName(), pairSeries.getStatType());
        Map<String, IndicatorSeries> currentMap = new HashMap<>();
        Map<String, IndicatorSeries> comparisonMap = new HashMap<>();

        int largeSize = Math.max(pairSeries.getCurrentList().size(), pairSeries.getComparisonList().size());

        IndicatorSeries currentSeries;
        IndicatorSeries comparisonSeries;
        for (int i = 0; i < largeSize; i++) {
            if(pairSeries.getCurrentList().size() - 1 >= i){
                currentSeries = pairSeries.getCurrentList().get(i);
                currentMap.put(currentSeries.getLogicalIndex(), currentSeries);
                if(!comparisonMap.containsKey(currentSeries.getLogicalIndex())){
                    comparisonMap.put(currentSeries.getLogicalIndex(), new IndicatorSeries(currentSeries.getTime(), 0, currentSeries.getLogicalIndex()));
                }
            }

            if(pairSeries.getComparisonList().size() - 1 >= i){
                comparisonSeries = pairSeries.getComparisonList().get(i);
                comparisonMap.put(comparisonSeries.getLogicalIndex(), pairSeries.getComparisonList().get(i));
                if(!currentMap.containsKey(comparisonSeries.getLogicalIndex())){
                    currentMap.put(comparisonSeries.getLogicalIndex(), new IndicatorSeries(comparisonSeries.getTime(), 0, comparisonSeries.getLogicalIndex()));
                }
            }
        }

        List<IndicatorSeries> currentSortList = currentMap.values().stream().sorted(new IndicatorSeriesLogicIndexComparator()).collect(Collectors.toList());
        List<IndicatorSeries> comparisonSortList = comparisonMap.values().stream().sorted(new IndicatorSeriesLogicIndexComparator()).collect(Collectors.toList());
        result.setCurrentList(currentSortList);
        result.setComparisonList(comparisonSortList);

        return result;
    }

    /**
     * 根据logicIndex对齐两个list,并根据logicIndex按字典排序. 即，logicIndex不存在的，补充默认值，使得俩list对齐
     * <br/>before：current[a->1,b->2] comparison[b->3,c->4,d->5]
     * <br/>after：current[a->1,b->2,c->0,d->0] comparison[a->0,b->3,c->4,d->5]
     * @param divisionSeries
     * @return
     */
    public static IndicatorDivisionSeries indicatorAlignThenSorted(IndicatorDivisionSeries divisionSeries) {
        IndicatorDivisionSeries result = new IndicatorDivisionSeries(divisionSeries.getIndicator(), divisionSeries.getIndicatorName(), divisionSeries.getStatType());
        Map<String, IndicatorSeries> currentNumeratorMap = new HashMap<>();
        Map<String, IndicatorSeries> currentDenominatorMap = new HashMap<>();
        Map<String, IndicatorSeries> comparisonNumeratorMap = new HashMap<>();
        Map<String, IndicatorSeries> comparisonDenominatorMap = new HashMap<>();

        int currentLargeSize = Math.max(divisionSeries.getCurrentNumeratorList().size(), divisionSeries.getCurrentDenominatorList().size());
        int comparisonLargeSize = Math.max(divisionSeries.getComparisonNumeratorList().size(), divisionSeries.getComparisonDenominatorList().size());
        int largeSize = Math.max(currentLargeSize, comparisonLargeSize);

        IndicatorSeries currentNumeratorSeries;
        IndicatorSeries currentDenominatorSeries;
        IndicatorSeries comparisonNumeratorSeries;
        IndicatorSeries comparisonDenominatorSeries;
        for (int i = 0; i < largeSize; i++) {
            /* Current align */
            if(divisionSeries.getCurrentNumeratorList().size() - 1 >= i){
                currentNumeratorSeries = divisionSeries.getCurrentNumeratorList().get(i);
                currentNumeratorMap.put(currentNumeratorSeries.getLogicalIndex(), currentNumeratorSeries);
                if(!currentDenominatorMap.containsKey(currentNumeratorSeries.getLogicalIndex())){
                    currentDenominatorMap.put(currentNumeratorSeries.getLogicalIndex(), new IndicatorSeries(currentNumeratorSeries.getTime(), 1, currentNumeratorSeries.getLogicalIndex()));
                }
                // Comparison -> Current align
                if(!comparisonNumeratorMap.containsKey(currentNumeratorSeries.getLogicalIndex())){
                    comparisonNumeratorMap.put(currentNumeratorSeries.getLogicalIndex(), new IndicatorSeries(currentNumeratorSeries.getTime(), 1, currentNumeratorSeries.getLogicalIndex()));
                }
                if(!comparisonDenominatorMap.containsKey(currentNumeratorSeries.getLogicalIndex())){
                    comparisonDenominatorMap.put(currentNumeratorSeries.getLogicalIndex(), new IndicatorSeries(currentNumeratorSeries.getTime(), 1, currentNumeratorSeries.getLogicalIndex()));
                }
            }

            if(divisionSeries.getCurrentDenominatorList().size() - 1 >= i){
                currentDenominatorSeries = divisionSeries.getCurrentDenominatorList().get(i);
                currentDenominatorMap.put(currentDenominatorSeries.getLogicalIndex(), currentDenominatorSeries);
                if(!currentNumeratorMap.containsKey(currentDenominatorSeries.getLogicalIndex())){
                    currentNumeratorMap.put(currentDenominatorSeries.getLogicalIndex(), new IndicatorSeries(currentDenominatorSeries.getTime(), 0, currentDenominatorSeries.getLogicalIndex()));
                }
                // Comparison -> Current align
                if(!comparisonNumeratorMap.containsKey(currentDenominatorSeries.getLogicalIndex())){
                    comparisonNumeratorMap.put(currentDenominatorSeries.getLogicalIndex(), new IndicatorSeries(currentDenominatorSeries.getTime(), 1, currentDenominatorSeries.getLogicalIndex()));
                }
                if(!comparisonDenominatorMap.containsKey(currentDenominatorSeries.getLogicalIndex())){
                    comparisonDenominatorMap.put(currentDenominatorSeries.getLogicalIndex(), new IndicatorSeries(currentDenominatorSeries.getTime(), 1, currentDenominatorSeries.getLogicalIndex()));
                }
            }

            /* Comparison align */
            if(divisionSeries.getComparisonNumeratorList().size() - 1 >= i){
                comparisonNumeratorSeries = divisionSeries.getComparisonNumeratorList().get(i);
                comparisonNumeratorMap.put(comparisonNumeratorSeries.getLogicalIndex(), comparisonNumeratorSeries);
                if(!comparisonDenominatorMap.containsKey(comparisonNumeratorSeries.getLogicalIndex())){
                    comparisonDenominatorMap.put(comparisonNumeratorSeries.getLogicalIndex(), new IndicatorSeries(comparisonNumeratorSeries.getTime(), 1, comparisonNumeratorSeries.getLogicalIndex()));
                }
                // Current -> Comparison align
                if(!currentNumeratorMap.containsKey(comparisonNumeratorSeries.getLogicalIndex())){
                    currentNumeratorMap.put(comparisonNumeratorSeries.getLogicalIndex(), new IndicatorSeries(comparisonNumeratorSeries.getTime(), 1, comparisonNumeratorSeries.getLogicalIndex()));
                }
                if(!currentDenominatorMap.containsKey(comparisonNumeratorSeries.getLogicalIndex())){
                    currentDenominatorMap.put(comparisonNumeratorSeries.getLogicalIndex(), new IndicatorSeries(comparisonNumeratorSeries.getTime(), 1, comparisonNumeratorSeries.getLogicalIndex()));
                }
            }

            if(divisionSeries.getComparisonDenominatorList().size() - 1 >= i){
                comparisonDenominatorSeries = divisionSeries.getComparisonDenominatorList().get(i);
                comparisonDenominatorMap.put(comparisonDenominatorSeries.getLogicalIndex(), comparisonDenominatorSeries);
                if(!comparisonNumeratorMap.containsKey(comparisonDenominatorSeries.getLogicalIndex())){
                    comparisonNumeratorMap.put(comparisonDenominatorSeries.getLogicalIndex(), new IndicatorSeries(comparisonDenominatorSeries.getTime(), 0, comparisonDenominatorSeries.getLogicalIndex()));
                }
                // Current -> Comparison align
                if(!currentNumeratorMap.containsKey(comparisonDenominatorSeries.getLogicalIndex())){
                    currentNumeratorMap.put(comparisonDenominatorSeries.getLogicalIndex(), new IndicatorSeries(comparisonDenominatorSeries.getTime(), 1, comparisonDenominatorSeries.getLogicalIndex()));
                }
                if(!currentDenominatorMap.containsKey(comparisonDenominatorSeries.getLogicalIndex())){
                    currentDenominatorMap.put(comparisonDenominatorSeries.getLogicalIndex(), new IndicatorSeries(comparisonDenominatorSeries.getTime(), 1, comparisonDenominatorSeries.getLogicalIndex()));
                }
            }

        }

        List<IndicatorSeries> currentNumeratorSortList = currentNumeratorMap.values().stream().sorted(new IndicatorSeriesLogicIndexComparator()).collect(Collectors.toList());
        List<IndicatorSeries> currentDenominatorSortList = currentDenominatorMap.values().stream().sorted(new IndicatorSeriesLogicIndexComparator()).collect(Collectors.toList());
        List<IndicatorSeries> comparisonNumeratorSortList = comparisonNumeratorMap.values().stream().sorted(new IndicatorSeriesLogicIndexComparator()).collect(Collectors.toList());
        List<IndicatorSeries> comparisonDenominatorSortList = comparisonDenominatorMap.values().stream().sorted(new IndicatorSeriesLogicIndexComparator()).collect(Collectors.toList());
        result.setCurrentNumeratorList(currentNumeratorSortList);
        result.setCurrentDenominatorList(currentDenominatorSortList);
        result.setComparisonNumeratorList(comparisonNumeratorSortList);
        result.setComparisonDenominatorList(comparisonDenominatorSortList);

        return result;
    }

    /**
     * Indicator data to probability distribution
     *
     * @param info IndicatorPairInfo data
     * @return {@link ProbabilityDistribution}
     */
    public static ProbabilityDistribution indicatorTransferToPD(IndicatorPairSeries info) {
        if (info == null || CollectionUtil.isEmpty(info.getCurrentList())) {
            return null;
        }

        ProbabilityDistribution probabilityDistribution = null;
        if (info.getStatType() == IndicatorStatType.Add) {
            probabilityDistribution = ProbabilityDistributionUtil.transferPDForAddIndicator(info);
        } else if (info.getStatType() == IndicatorStatType.Unique_Discrete) {
            probabilityDistribution = ProbabilityDistributionUtil.transferPDForUniqueDiscreteIndicator(info);
        } else if (info.getStatType() == IndicatorStatType.Unique_Continuity) {
            probabilityDistribution = ProbabilityDistributionUtil.transferPDForUniqueContinuityIndicator(info);
        }

        return probabilityDistribution;
    }

    public static double exclude0Multiply(double a, double b){
        if (a == 0) {
            return b;
        } else if (b == 0){
            return a;
        } else {
            return a * b;
        }
    }

    public static double divide(double value, double divide){
        if (divide == 0) {
            return 1;
        } else {
            return value / divide;
        }
    }


}
