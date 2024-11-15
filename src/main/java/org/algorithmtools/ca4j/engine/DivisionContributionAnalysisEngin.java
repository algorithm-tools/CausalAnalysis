package org.algorithmtools.ca4j.engine;

import org.algorithmtools.ca4j.calculator.DivisionContributionCalculator;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.CausalAnalysisLog;
import org.algorithmtools.ca4j.pojo.IndicatorDivisionSeries;
import org.algorithmtools.ca4j.pojo.result.ContributionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Division contribution analyse engin
 * @author mym
 */
public class DivisionContributionAnalysisEngin extends AbstractCausalAnalysisEngin<IndicatorDivisionSeries, ContributionResult>{

    private static final Logger LOGGER = LoggerFactory.getLogger(DivisionContributionAnalysisEngin.class);

    private DivisionContributionCalculator calculator;

    public DivisionContributionAnalysisEngin(CausalAnalysisContext context) {
        super(context);
        this.calculator = new DivisionContributionCalculator();
        this.calculator.init(context);
    }

    @Override
    public ContributionResult analyse(IndicatorDivisionSeries series) {
        CausalAnalysisLog log = new CausalAnalysisLog();
        calculator.checkCompatibility(series, log);
        ContributionResult result = calculator.calculate(series, log);
        log.printThenClear();

        return result;
    }
}
