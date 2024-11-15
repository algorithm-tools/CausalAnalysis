package org.algorithmtools.ca4j.engine;

import org.algorithmtools.ca4j.calculator.JSDivergenceCalculator;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.CausalAnalysisLog;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.result.JSDivergenceResult;
import org.algorithmtools.ca4j.utils.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dimensional root cause analyse engin
 * @author mym
 */
public class DimensionalRootCauseAnalysisEngin extends AbstractCausalAnalysisEngin<IndicatorPairSeries, JSDivergenceResult>{

    private static final Logger LOGGER = LoggerFactory.getLogger(DimensionalRootCauseAnalysisEngin.class);

    private JSDivergenceCalculator calculator;

    public DimensionalRootCauseAnalysisEngin(CausalAnalysisContext context) {
        super(context);
        this.calculator = new JSDivergenceCalculator();
        this.calculator.init(context);
    }

    @Override
    public JSDivergenceResult analyse(IndicatorPairSeries series) {
        if (CollectionUtil.isEmpty(series.getCurrentList()) && CollectionUtil.isEmpty(series.getComparisonList())) {
            return null;
        }

        CausalAnalysisLog log = new CausalAnalysisLog();
        calculator.checkCompatibility(series, log);
        JSDivergenceResult result = calculator.calculate(series, log);
        log.printThenClear();

        return result;
    }
}
