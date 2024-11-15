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
        IndicatorDivisionSeries sortedData = IndicatorCalculateUtil.indicatorAlignThenSorted(calculateData);
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
        double X0 = y0 / z0;
        double X1 = y1 / z1;
        // TODO if X0 or X1 is 0, how to calculate
        result.setIndicatorComparisonValue(X0);
        result.setIndicatorCurrentValue(X1);
        result.setIndicatorChangeValue(X1 - X0);
        result.setIndicatorChangeRate(result.getIndicatorChangeValue() / X0);

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
        // calculate change
        for (int i = 0; i < sortedData.getCurrentNumeratorList().size(); i++) {
            X1_i = sortedData.getCurrentNumeratorList().get(i).getValue() / sortedData.getCurrentDenominatorList().get(i).getValue();
            X0_i = sortedData.getComparisonNumeratorList().get(i).getValue() / sortedData.getComparisonDenominatorList().get(i).getValue();
            Z0_i = sortedData.getComparisonDenominatorList().get(i).getValue() / z0;
            Z1_i = sortedData.getCurrentDenominatorList().get(i).getValue() / z1;
            A_i = (X1_i - X0_i) * Z0_i;
            B_i = (Z1_i - Z0_i) * (X1_i - X0);

            indicator_x1_i = new IndicatorSeries(sortedData.getCurrentNumeratorList().get(i).getTime(), X1_i, sortedData.getCurrentNumeratorList().get(i).getLogicalIndex());
            indicator_x0_i = new IndicatorSeries(sortedData.getComparisonNumeratorList().get(i).getTime(), X0_i, sortedData.getComparisonNumeratorList().get(i).getLogicalIndex());
            indicatorCalculateResult = new IndicatorCalculateResult(indicator_x1_i, indicator_x0_i);
            indicatorCalculateResult.setChangeValue(indicator_x1_i.getValue() - indicator_x0_i.getValue());
            indicatorCalculateResult.setChangeRate(indicatorCalculateResult.getChangeValue() / indicator_x0_i.getValue());
            indicatorCalculateResult.setInfluenceType(indicatorCalculateResult.getChangeValue() > 0 ? InfluenceType.UP : InfluenceType.DOWN);
            indicatorCalculateResult.setContributeValue(A_i + B_i);
            indicatorCalculateResult.setContributeRate(indicatorCalculateResult.getContributeValue() / X0);
            totalAbsContributeRate += Math.abs(indicatorCalculateResult.getContributeRate());

            result.add(indicatorCalculateResult);
        }

        final double _totalAbsContributeRate = totalAbsContributeRate;
        result.getCalculateResults().forEach(v -> v.setContributeProportion(IndicatorCalculateUtil.divide(Math.abs(v.getContributeRate()), _totalAbsContributeRate)));

        return result;
    }

    @Override
    public boolean checkCompatibility(IndicatorDivisionSeries calculateData, CausalAnalysisLog log) {
        if (CollectionUtil.isEmpty(calculateData.getCurrentNumeratorList()) && CollectionUtil.isEmpty(calculateData.getCurrentDenominatorList())) {
            return false;
        }
        return true;
    }
}
