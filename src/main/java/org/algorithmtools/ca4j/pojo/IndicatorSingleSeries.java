package org.algorithmtools.ca4j.pojo;

import org.algorithmtools.ca4j.enumtype.IndicatorStatType;

import java.util.List;

public class IndicatorSingleSeries extends AbstractIndicatorCalculateSeries {

    /**
     * current indicator series
     */
    private List<IndicatorSeries> currentList;

    public IndicatorSingleSeries(String indicator, String indicatorName, IndicatorStatType statType, List<IndicatorSeries> currentList) {
        super(indicator, indicatorName, statType);
        this.currentList = currentList;
    }

    public List<IndicatorSeries> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<IndicatorSeries> currentList) {
        this.currentList = currentList;
    }
}
