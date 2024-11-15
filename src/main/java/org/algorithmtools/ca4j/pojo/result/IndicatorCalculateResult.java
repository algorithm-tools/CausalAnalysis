package org.algorithmtools.ca4j.pojo.result;

import org.algorithmtools.ca4j.enumtype.InfluenceType;
import org.algorithmtools.ca4j.pojo.IndicatorSeries;

public class IndicatorCalculateResult {

    /**
     * 当期指标因子
     */
    private IndicatorSeries currentFactorTermIndicator;
    /**
     * 对比期指标因子
     */
    private IndicatorSeries comparisonFactorTermIndicator;

    /**
     * 指标因子项（对应currentIndicator的logicIndex）
     */
    private String factorTerm;

    /**
     * 变化度
     */
    private double changeValue;
    /**
     * 变化率
     */
    private double changeRate;

    /**
     * 贡献值（贡献度）
     */
    private double contributeValue;

    /**
     * 贡献率
     */
    private double contributeRate;

    /**
     * 贡献占比：ABS(contributeRate_i) / SUM(ABS(contributeRate_i))
     */
    private double contributeProportion;

    /**
     * 影响类型
     */
    private InfluenceType influenceType;

    public IndicatorCalculateResult(IndicatorSeries currentFactorTermIndicator, IndicatorSeries comparisonFactorTermIndicator) {
        this.currentFactorTermIndicator = currentFactorTermIndicator;
        this.comparisonFactorTermIndicator = comparisonFactorTermIndicator;
        this.factorTerm = currentFactorTermIndicator.getLogicalIndex();
    }

    public IndicatorSeries getCurrentFactorTermIndicator() {
        return currentFactorTermIndicator;
    }

    public void setCurrentFactorTermIndicator(IndicatorSeries currentFactorTermIndicator) {
        this.currentFactorTermIndicator = currentFactorTermIndicator;
    }

    public IndicatorSeries getComparisonFactorTermIndicator() {
        return comparisonFactorTermIndicator;
    }

    public void setComparisonFactorTermIndicator(IndicatorSeries comparisonFactorTermIndicator) {
        this.comparisonFactorTermIndicator = comparisonFactorTermIndicator;
    }

    public String getFactorTerm() {
        return factorTerm;
    }

    public void setFactorTerm(String factorTerm) {
        this.factorTerm = factorTerm;
    }

    public double getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(double changeValue) {
        this.changeValue = changeValue;
    }

    public double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(double changeRate) {
        this.changeRate = changeRate;
    }

    public double getContributeRate() {
        return contributeRate;
    }

    public void setContributeRate(double contributeRate) {
        this.contributeRate = contributeRate;
    }

    public InfluenceType getInfluenceType() {
        return influenceType;
    }

    public void setInfluenceType(InfluenceType influenceType) {
        this.influenceType = influenceType;
    }

    public double getContributeValue() {
        return contributeValue;
    }

    public void setContributeValue(double contributeValue) {
        this.contributeValue = contributeValue;
    }

    public double getContributeProportion() {
        return contributeProportion;
    }

    public void setContributeProportion(double contributeProportion) {
        this.contributeProportion = contributeProportion;
    }

    @Override
    public String toString() {
        return "ContributionResult{" +
                "currentFactorTermIndicator=" + currentFactorTermIndicator +
                ", comparisonFactorTermIndicator=" + comparisonFactorTermIndicator +
                ", factorTerm='" + factorTerm + '\'' +
                ", changeValue=" + changeValue +
                ", changeRate=" + changeRate +
                ", contributeValue=" + contributeValue +
                ", contributeRate=" + contributeRate +
                ", contributeProportion=" + contributeProportion +
                ", influenceType=" + influenceType +
                '}';
    }
}
