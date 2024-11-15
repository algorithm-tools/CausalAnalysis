package org.algorithmtools.ca4j.pojo;

import org.algorithmtools.ca4j.enumtype.IndicatorStatType;

import java.util.List;

/**
 * series pair list
 */
public class IndicatorPairSeries extends AbstractIndicatorCalculateSeries {

    /**
     * current indicator series
     */
    private List<IndicatorSeries> currentList;
    /**
     * comparison period indicator series
     */
    private List<IndicatorSeries> comparisonList;

    public IndicatorPairSeries(String indicator, String indicatorName, IndicatorStatType statType) {
        super(indicator, indicatorName, statType);
    }

    public IndicatorPairSeries(String indicator, String indicatorName, IndicatorStatType statType, List<IndicatorSeries> currentList, List<IndicatorSeries> comparisonList) {
        super(indicator, indicatorName, statType);
        this.currentList = currentList;
        this.comparisonList = comparisonList;
    }

    public List<IndicatorSeries> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<IndicatorSeries> currentList) {
        this.currentList = currentList;
    }

    public List<IndicatorSeries> getComparisonList() {
        return comparisonList;
    }

    public void setComparisonList(List<IndicatorSeries> comparisonList) {
        this.comparisonList = comparisonList;
    }
}
