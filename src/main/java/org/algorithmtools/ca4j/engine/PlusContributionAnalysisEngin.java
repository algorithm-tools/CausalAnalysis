package org.algorithmtools.ca4j.engine;

import org.algorithmtools.ca4j.calculator.PlusContributionCalculator;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.CausalAnalysisLog;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.result.ContributionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Plus(+/-) contribution analyse engin
 * @author mym
 */
public class PlusContributionAnalysisEngin extends AbstractCausalAnalysisEngin<IndicatorPairSeries, ContributionResult>{

    private static final Logger LOGGER = LoggerFactory.getLogger(PlusContributionAnalysisEngin.class);

    private PlusContributionCalculator calculator;

    public PlusContributionAnalysisEngin(CausalAnalysisContext context) {
        super(context);
        this.calculator = new PlusContributionCalculator();
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
