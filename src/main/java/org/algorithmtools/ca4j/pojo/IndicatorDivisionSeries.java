package org.algorithmtools.ca4j.pojo;

import org.algorithmtools.ca4j.enumtype.IndicatorStatType;

import java.util.List;

/**
 * series pair list. for division calculate
 */
public class IndicatorDivisionSeries extends AbstractIndicatorCalculateSeries {

    /**
     * current indicator series: numerator series
     */
    private List<IndicatorSeries> currentNumeratorList;

    /**
     * current indicator series: denominator series
     */
    private List<IndicatorSeries> currentDenominatorList;

    /**
     * comparison indicator series: numerator series
     */
    private List<IndicatorSeries> comparisonNumeratorList;

    /**
     * comparison indicator series: denominator series
     */
    private List<IndicatorSeries> comparisonDenominatorList;

    public IndicatorDivisionSeries(String indicator, String indicatorName, IndicatorStatType statType) {
        super(indicator, indicatorName, statType);
    }

    public IndicatorDivisionSeries(String indicator, String indicatorName, IndicatorStatType statType, List<IndicatorSeries> currentNumeratorList, List<IndicatorSeries> currentDenominatorList, List<IndicatorSeries> comparisonNumeratorList, List<IndicatorSeries> comparisonDenominatorList) {
        super(indicator, indicatorName, statType);
        this.currentNumeratorList = currentNumeratorList;
        this.currentDenominatorList = currentDenominatorList;
        this.comparisonNumeratorList = comparisonNumeratorList;
        this.comparisonDenominatorList = comparisonDenominatorList;
    }

    public List<IndicatorSeries> getCurrentNumeratorList() {
        return currentNumeratorList;
    }

    public void setCurrentNumeratorList(List<IndicatorSeries> currentNumeratorList) {
        this.currentNumeratorList = currentNumeratorList;
    }

    public List<IndicatorSeries> getCurrentDenominatorList() {
        return currentDenominatorList;
    }

    public void setCurrentDenominatorList(List<IndicatorSeries> currentDenominatorList) {
        this.currentDenominatorList = currentDenominatorList;
    }

    public List<IndicatorSeries> getComparisonNumeratorList() {
        return comparisonNumeratorList;
    }

    public void setComparisonNumeratorList(List<IndicatorSeries> comparisonNumeratorList) {
        this.comparisonNumeratorList = comparisonNumeratorList;
    }

    public List<IndicatorSeries> getComparisonDenominatorList() {
        return comparisonDenominatorList;
    }

    public void setComparisonDenominatorList(List<IndicatorSeries> comparisonDenominatorList) {
        this.comparisonDenominatorList = comparisonDenominatorList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("indicatorName:").append(this.indicatorName).append("(").append(this.indicator).append(",").append(this.statType).append(")");
        builder.append("\nCurrent:").append("\n");
        for (IndicatorSeries indicatorSeries : currentNumeratorList) {
            builder.append(indicatorSeries.getLogicalIndex()).append("[").append(indicatorSeries.getValue()).append("(").append(indicatorSeries.getTime()).append(")").append("]").append("\t");
        }
        builder.append("\n");
        for (IndicatorSeries indicatorSeries : currentDenominatorList) {
            builder.append(indicatorSeries.getLogicalIndex()).append("[").append(indicatorSeries.getValue()).append("(").append(indicatorSeries.getTime()).append(")").append("]").append("\t");
        }
        builder.append("\n-------------------------------------------------------------------------------------------");
        builder.append("\nComparison:").append("\n");
        for (IndicatorSeries indicatorSeries : comparisonNumeratorList) {
            builder.append(indicatorSeries.getLogicalIndex()).append("[").append(indicatorSeries.getValue()).append("(").append(indicatorSeries.getTime()).append(")").append("]").append("\t");
        }
        builder.append("\n");
        for (IndicatorSeries indicatorSeries : comparisonDenominatorList) {
            builder.append(indicatorSeries.getLogicalIndex()).append("[").append(indicatorSeries.getValue()).append("(").append(indicatorSeries.getTime()).append(")").append("]").append("\t");
        }

        return builder.toString();
    }
}
