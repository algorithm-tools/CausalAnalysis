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
 * Contribution Plus(+/-) Calculator
 *
 * @author mym
 */
public class PlusContributionCalculator extends AbstractCalculator<IndicatorPairSeries, ContributionResult>{
    public PlusContributionCalculator() {
        super("ContributionPlusCalculator", CalculatorType.Contribution_Plus);
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
        for (int i = 0; i < sortedData.getCurrentList().size(); i++) {
            x1_i = sortedData.getCurrentList().get(i);
            x0_i = sortedData.getComparisonList().get(i);
            X1 += x1_i.getValue();
            X0 += x0_i.getValue();

            indicatorCalculateResult = new IndicatorCalculateResult(x1_i, x0_i);
            indicatorCalculateResult.setChangeValue(x1_i.getValue() - x0_i.getValue());
            indicatorCalculateResult.setChangeRate(IndicatorCalculateUtil.divide(indicatorCalculateResult.getChangeValue(), x0_i.getValue()));

            result.add(indicatorCalculateResult);
        }

        result.setIndicatorComparisonValue(X0);
        result.setIndicatorCurrentValue(X1);
        result.setIndicatorChangeValue(X1 - X0);
        result.setIndicatorChangeRate(IndicatorCalculateUtil.divide(result.getIndicatorChangeValue(), X0));

        // Calculate contribution
        IndicatorCalculateResult calculateResult;
        double totalAbsContributeRate = 0;
        for (int i = 0; i < result.getCalculateResults().size(); i++) {
            calculateResult = result.getCalculateResults().get(i);
            calculateResult.setContributeValue(calculateResult.getChangeValue());
            calculateResult.setContributeRate(IndicatorCalculateUtil.divide(calculateResult.getChangeValue() ,X0));
            totalAbsContributeRate += Math.abs(calculateResult.getContributeRate());
            calculateResult.setInfluenceType(calculateResult.getChangeValue() > 0 ? InfluenceType.UP : InfluenceType.DOWN);
        }

        // Calculate contribution proportion
        final double _totalAbsContributeRate = totalAbsContributeRate;
        result.getCalculateResults().forEach(v -> v.setContributeProportion(IndicatorCalculateUtil.divide(Math.abs(v.getContributeRate()), _totalAbsContributeRate)));

        return result;
    }

    @Override
    public boolean checkCompatibility(IndicatorPairSeries calculateData, CausalAnalysisLog log) {
        if (CollectionUtil.isEmpty(calculateData.getCurrentList()) && CollectionUtil.isEmpty(calculateData.getComparisonList())) {
            return false;
        }
        return true;
    }
}
