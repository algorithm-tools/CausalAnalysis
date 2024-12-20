package org.algorithmtools.ca4j.calculator;

import org.algorithmtools.ca4j.enumtype.CalculatorType;
import org.algorithmtools.ca4j.enumtype.InfluenceType;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.CausalAnalysisLog;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.IndicatorSeries;
import org.algorithmtools.ca4j.pojo.result.IndicatorCalculateResult;
import org.algorithmtools.ca4j.pojo.result.ContributionResult;
import org.algorithmtools.ca4j.utils.CollectionUtil;
import org.algorithmtools.ca4j.utils.IndicatorCalculateUtil;

/**
 * Contribution Multiply Calculator
 *
 * @author mym
 */
public class MultiplyContributionCalculator extends AbstractCalculator<IndicatorPairSeries, ContributionResult>{
    public MultiplyContributionCalculator() {
        super("ContributionMultiplyCalculator", CalculatorType.Contribution_Multiply);
    }

    @Override
    public void init(CausalAnalysisContext context) {

    }

    @Override
    public ContributionResult calculate(IndicatorPairSeries calculateData, CausalAnalysisLog log) {
        IndicatorPairSeries sortedData = IndicatorCalculateUtil.indicatorAlignThenSorted(calculateData);

        ContributionResult result = new ContributionResult();
        IndicatorSeries x1_i;
        IndicatorSeries x0_i;
        IndicatorCalculateResult indicatorCalculateResult;
        double X1 = 0;
        double X0 = 0;
        // Calculate change
        for (int i = 0; i < sortedData.getCurrentList().size(); i++) {
            x1_i = sortedData.getCurrentList().get(i);
            x0_i = sortedData.getComparisonList().get(i);
            X1 = IndicatorCalculateUtil.exclude0Multiply(X1, x1_i.getValue());
            X0 = IndicatorCalculateUtil.exclude0Multiply(X0, x0_i.getValue());

            indicatorCalculateResult = new IndicatorCalculateResult(x1_i, x0_i);
            indicatorCalculateResult.setChangeValue(x1_i.getValue() - x0_i.getValue());
            indicatorCalculateResult.setChangeRate(IndicatorCalculateUtil.rateDivide(indicatorCalculateResult.getChangeValue(), x0_i.getValue()));

            result.add(indicatorCalculateResult);
        }

        // TODO if X0 or X1 is 0, how to calculate
        result.setIndicatorComparisonValue(X0);
        result.setIndicatorCurrentValue(X1);
        result.setIndicatorChangeValue(X1 - X0);
        result.setIndicatorChangeRate(IndicatorCalculateUtil.rateDivide(result.getIndicatorChangeValue(), X0));

        // Calculate log mean
        double L_X1_X0 = (X1 - X0) / (Math.log(X1) - Math.log(X0));

        // Calculate contribution
        IndicatorCalculateResult calculateResult;
        double totalAbsContributeRate = 0;
        for (int i = 0; i < result.getCalculateResults().size(); i++) {
            calculateResult = result.getCalculateResults().get(i);
            calculateResult.setContributeValue(L_X1_X0 * Math.log(calculateResult.getCurrentFactorTermIndicator().getValue() / calculateResult.getComparisonFactorTermIndicator().getValue()));
            calculateResult.setContributeRate(IndicatorCalculateUtil.rateDivide(calculateResult.getContributeValue(), X0));
            calculateResult.setInfluenceType(calculateResult.getChangeValue() > 0 ? InfluenceType.UP : InfluenceType.DOWN);
            totalAbsContributeRate += Math.abs(calculateResult.getContributeRate());
        }

        // Calculate contribution proportion
        final double _totalAbsContributeRate = totalAbsContributeRate;
        result.getCalculateResults().forEach(v -> v.setContributeProportion(IndicatorCalculateUtil.rateDivide(Math.abs(v.getContributeRate()), _totalAbsContributeRate)));

        return result;
    }

    @Override
    public void checkCompatibility(IndicatorPairSeries calculateData, CausalAnalysisLog log) {
        if (CollectionUtil.isEmpty(calculateData.getCurrentList()) && CollectionUtil.isEmpty(calculateData.getComparisonList())) {
            throw new IllegalArgumentException("Empty data list! data:" + calculateData);
        }
    }
}
