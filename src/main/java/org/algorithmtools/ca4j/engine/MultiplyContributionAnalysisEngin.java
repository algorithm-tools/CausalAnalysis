package org.algorithmtools.ca4j.engine;

import org.algorithmtools.ca4j.calculator.MultiplyContributionCalculator;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.CausalAnalysisLog;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.result.ContributionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Multiply contribution analyse engin
 * @author mym
 */
public class MultiplyContributionAnalysisEngin extends AbstractCausalAnalysisEngin<IndicatorPairSeries, ContributionResult>{

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiplyContributionAnalysisEngin.class);

    private MultiplyContributionCalculator calculator;

    public MultiplyContributionAnalysisEngin(CausalAnalysisContext context) {
        super(context);
        this.calculator = new MultiplyContributionCalculator();
        this.calculator.init(context);
    }

    @Override
    public ContributionResult analyse(IndicatorPairSeries series) {
        CausalAnalysisLog log = new CausalAnalysisLog();
        calculator.checkCompatibility(series, log);
        ContributionResult result = calculator.calculate(series, log);
        log.printThenClear();

        return result;
    }
}
