package org.algorithmtools.ca4j.calculator;

import org.algorithmtools.ca4j.enumtype.CalculatorType;
import org.algorithmtools.ca4j.enumtype.InfluenceType;
import org.algorithmtools.ca4j.pojo.*;
import org.algorithmtools.ca4j.pojo.result.IndicatorCalculateResult;
import org.algorithmtools.ca4j.pojo.result.ContributionResult;
import org.algorithmtools.ca4j.utils.CollectionUtil;
import org.algorithmtools.ca4j.utils.IndicatorCalculateUtil;

/**
 * Contribution Division Calculator
 *
 * @author mym
 */
public class DivisionContributionCalculator extends AbstractCalculator<IndicatorDivisionSeries, ContributionResult>{
    public DivisionContributionCalculator() {
        super("ContributionDivisionCalculator", CalculatorType.Contribution_Division);
    }

    @Override
    public void init(CausalAnalysisContext context) {

    }

    @Override
    public ContributionResult calculate(IndicatorDivisionSeries calculateData, CausalAnalysisLog log) {
        double zeroReplaceValue = 0.000001;
        IndicatorDivisionSeries sortedData = IndicatorCalculateUtil.indicatorAlignThenSorted(calculateData, zeroReplaceValue);
        ContributionResult result = new ContributionResult();

        /*
        * Contribution=A+B
        * X=Y/Z
        * X_i=y_i/z_i
        * Z_i=z_i/z
        * y_i=y_i/y
        * A=(X1_i - X0_i) * Z0_i
        * B=(Z1_i - Z0_i) * (X1_i - X0)
        */
        double z0 = 0;
        double z1 = 0;
        double y1 = 0;
        double y0 = 0;
        for (int i = 0; i < sortedData.getCurrentNumeratorList().size(); i++) {
            z0 += sortedData.getComparisonDenominatorList().get(i).getValue();
            z1 += sortedData.getCurrentDenominatorList().get(i).getValue();
            y0 += sortedData.getComparisonNumeratorList().get(i).getValue();
            y1 += sortedData.getCurrentNumeratorList().get(i).getValue();
        }
        double X0 = IndicatorCalculateUtil.rateDivide(y0, z0);
        double X1 = IndicatorCalculateUtil.rateDivide(y1, z1);
        result.setIndicatorComparisonValue(X0);
        result.setIndicatorCurrentValue(X1);
        result.setIndicatorChangeValue(X1 - X0);
        result.setIndicatorChangeRate(IndicatorCalculateUtil.rateDivide(result.getIndicatorChangeValue(), X0));

        double X1_i;
        double X0_i;
        double Z0_i;
        double Z1_i;
        double A_i;
        double B_i;
        IndicatorCalculateResult indicatorCalculateResult;
        IndicatorSeries indicator_x1_i;
        IndicatorSeries indicator_x0_i;
        double totalAbsContributeRate = 0;
        boolean X1_i_denominatorIsZero;
        boolean X0_i_denominatorIsZero;
        // calculate change
        for (int i = 0; i < sortedData.getCurrentNumeratorList().size(); i++) {
            X1_i_denominatorIsZero = zeroReplaceValue == sortedData.getCurrentDenominatorList().get(i).getValue();
            X0_i_denominatorIsZero = zeroReplaceValue == sortedData.getComparisonDenominatorList().get(i).getValue();
            X1_i = IndicatorCalculateUtil.rateDivide(sortedData.getCurrentNumeratorList().get(i).getValue(), sortedData.getCurrentDenominatorList().get(i).getValue());
            X0_i = IndicatorCalculateUtil.rateDivide(sortedData.getComparisonNumeratorList().get(i).getValue(), sortedData.getComparisonDenominatorList().get(i).getValue());
            Z0_i = IndicatorCalculateUtil.rateDivide(sortedData.getComparisonDenominatorList().get(i).getValue(), z0);
            Z1_i = IndicatorCalculateUtil.rateDivide(sortedData.getCurrentDenominatorList().get(i).getValue(), z1);
            A_i = (X1_i - X0_i) * (Z0_i);
            B_i = (Z1_i - Z0_i) * (X1_i - X0);

            indicator_x1_i = new IndicatorSeries(sortedData.getCurrentNumeratorList().get(i).getTime(), X1_i_denominatorIsZero ? 0 : X1_i, sortedData.getCurrentNumeratorList().get(i).getLogicalIndex());
            indicator_x0_i = new IndicatorSeries(sortedData.getComparisonNumeratorList().get(i).getTime(), X0_i_denominatorIsZero ? 0 : X0_i, sortedData.getComparisonNumeratorList().get(i).getLogicalIndex());
            indicatorCalculateResult = new IndicatorCalculateResult(indicator_x1_i, indicator_x0_i);
            indicatorCalculateResult.setChangeValue(indicator_x1_i.getValue() - indicator_x0_i.getValue());
            indicatorCalculateResult.setChangeRate(IndicatorCalculateUtil.rateDivide(indicatorCalculateResult.getChangeValue(), indicator_x0_i.getValue()));
            indicatorCalculateResult.setInfluenceType(indicatorCalculateResult.getChangeValue() > 0 ? InfluenceType.UP : InfluenceType.DOWN);
            indicatorCalculateResult.setContributeValue(A_i + B_i);
            indicatorCalculateResult.setContributeRate(IndicatorCalculateUtil.rateDivide(indicatorCalculateResult.getContributeValue(), X0));
            totalAbsContributeRate += Math.abs(indicatorCalculateResult.getContributeRate());

            result.add(indicatorCalculateResult);
        }

        final double _totalAbsContributeRate = totalAbsContributeRate;
        result.getCalculateResults().forEach(v -> v.setContributeProportion(IndicatorCalculateUtil.rateDivide(Math.abs(v.getContributeRate()), _totalAbsContributeRate)));

        return result;
    }

    @Override
    public void checkCompatibility(IndicatorDivisionSeries calculateData, CausalAnalysisLog log) {
        if (CollectionUtil.isEmpty(calculateData.getCurrentNumeratorList()) && CollectionUtil.isEmpty(calculateData.getCurrentDenominatorList())) {
            throw new IllegalArgumentException("Empty data list! data:" + calculateData);
        }
    }
}
