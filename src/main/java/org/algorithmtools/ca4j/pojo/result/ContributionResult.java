package org.algorithmtools.ca4j.pojo.result;

import java.util.ArrayList;
import java.util.List;

public class ContributionResult extends CausalAnalysisResult{

    private List<IndicatorCalculateResult> calculateResults;

    /**
     * Current value
     */
    private double indicatorCurrentValue;

    /**
     * Comparison value
     */
    private double indicatorComparisonValue;

    /**
     * Value of change in indicator
     */
    private double indicatorChangeValue;
    /**
     * Rate of change of indicators
     */
    private double indicatorChangeRate;

    public ContributionResult(List<IndicatorCalculateResult> resultList) {
        this.calculateResults = resultList;
    }

    public ContributionResult() {
        this.calculateResults = new ArrayList<>();
    }

    public void add(IndicatorCalculateResult result){
        this.calculateResults.add(result);
    }

    public List<IndicatorCalculateResult> getCalculateResults() {
        return calculateResults;
    }

    public double getIndicatorChangeValue() {
        return indicatorChangeValue;
    }

    public void setIndicatorChangeValue(double indicatorChangeValue) {
        this.indicatorChangeValue = indicatorChangeValue;
    }

    public double getIndicatorChangeRate() {
        return indicatorChangeRate;
    }

    public void setIndicatorChangeRate(double indicatorChangeRate) {
        this.indicatorChangeRate = indicatorChangeRate;
    }

    public double getIndicatorCurrentValue() {
        return indicatorCurrentValue;
    }

    public void setIndicatorCurrentValue(double indicatorCurrentValue) {
        this.indicatorCurrentValue = indicatorCurrentValue;
    }

    public double getIndicatorComparisonValue() {
        return indicatorComparisonValue;
    }

    public void setIndicatorComparisonValue(double indicatorComparisonValue) {
        this.indicatorComparisonValue = indicatorComparisonValue;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Overview:").append(this.indicatorComparisonValue).append("-->").append(this.indicatorCurrentValue)
                .append("\tChangeValue(").append(this.indicatorChangeValue).append(")").append("\tChangeRate(").append(this.indicatorChangeRate).append(")");
        builder.append("\nFactorTermContribution:");
        double totalContributionValue = 0;
        double totalContributionRate = 0;
        for (IndicatorCalculateResult cr : this.getCalculateResults()) {
            builder.append("\nFactorTerm:").append(cr.getFactorTerm()).append("\t").append(cr.getComparisonFactorTermIndicator().getValue()).append("-->").append(cr.getCurrentFactorTermIndicator().getValue());
            builder.append("\t").append("ChangeValue(ChangeRate):").append(cr.getChangeValue()).append("(").append(cr.getChangeRate()).append(")");
            builder.append("\t").append("ContributionValue(ContributionRate):").append(cr.getContributeValue()).append("(").append(cr.getContributeRate()).append(")").append("\t ContributionProportion:").append(cr.getContributeProportion());
            totalContributionValue += cr.getContributeValue();
            totalContributionRate += cr.getContributeRate();
        }
        builder.append("\nContribution Sum:").append(totalContributionValue).append("(").append(totalContributionRate).append(")");
        return builder.toString();
    }
}
