package org.algorithmtools.ca4j.calculator;

import org.algorithmtools.ca4j.enumtype.CalculatorType;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.CausalAnalysisLog;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.ProbabilityDistribution;
import org.algorithmtools.ca4j.pojo.result.JSDivergenceResult;
import org.algorithmtools.ca4j.utils.CollectionUtil;
import org.algorithmtools.ca4j.utils.IndicatorCalculateUtil;

/**
 * JensenShannonDivergence Calculator
 *
 * @author mym
 */
public class JSDivergenceCalculator extends AbstractCalculator<IndicatorPairSeries, JSDivergenceResult> {

    public JSDivergenceCalculator() {
        super("JSDivergenceCalculator", CalculatorType.JSDivergence);
    }

    @Override
    public void init(CausalAnalysisContext context) {
    }

    @Override
    public JSDivergenceResult calculate(IndicatorPairSeries calculateData, CausalAnalysisLog log) {
        ProbabilityDistribution probabilityDistribution = IndicatorCalculateUtil.indicatorTransferToPD(calculateData);
        double jsd = jsDivergence(probabilityDistribution.getP(), probabilityDistribution.getQ());
        return new JSDivergenceResult(jsd);
    }

    @Override
    public void checkCompatibility(IndicatorPairSeries calculateData, CausalAnalysisLog log) {
        if (CollectionUtil.isEmpty(calculateData.getCurrentList()) && CollectionUtil.isEmpty(calculateData.getComparisonList())) {
            throw new IllegalArgumentException("Empty data list! data:" + calculateData);
        }
    }

    /**
     * compute the Kullback-Leibler Divergence (KL divergence)
     * @param p
     * @param q
     * @return klDivergence
     */
    private double klDivergence(double[] p, double[] q) {
        double klDiv = 0.0;
        for (int i = 0; i < p.length; i++) {
            if (p[i] != 0 && q[i] != 0) {
                klDiv += p[i] * Math.log(p[i] / q[i]);
            }
        }
        return klDiv;
    }

    /**
     * compute the Jensen-Shannon Divergence
     * @param p
     * @param q
     * @return jsDivergence
     */
    private double jsDivergence(double[] p, double[] q) {
        double[] m = new double[p.length];

        for (int i = 0; i < p.length; i++) {
            m[i] = 0.5 * (p[i] + q[i]);
        }

        return 0.5 * klDivergence(p, m) + 0.5 * klDivergence(q, m);
    }
}
