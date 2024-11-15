package org.algorithmtools.ca4j.pojo;

import org.algorithmtools.ca4j.enumtype.IndicatorStatType;

import java.io.Serializable;

public abstract class AbstractIndicatorCalculateSeries implements Serializable {

    /**
     * indicator en
     */
    protected String indicator;
    /**
     * indicator name
     */
    protected String indicatorName;

    /**
     * IndicatorStatType
     */
    protected IndicatorStatType statType;

    public AbstractIndicatorCalculateSeries(String indicator, String indicatorName, IndicatorStatType statType) {
        this.indicator = indicator;
        this.indicatorName = indicatorName;
        this.statType = statType;
    }

    public String getIndicator() {
        return indicator;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public IndicatorStatType getStatType() {
        return statType;
    }
}
