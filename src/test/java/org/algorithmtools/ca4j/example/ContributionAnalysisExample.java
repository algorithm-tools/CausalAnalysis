package org.algorithmtools.ca4j.example;

import org.algorithmtools.ca4j.engine.PlusContributionAnalysisEngin;
import org.algorithmtools.ca4j.enumtype.IndicatorStatType;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.IndicatorSeries;
import org.algorithmtools.ca4j.pojo.result.ContributionResult;

import java.util.Arrays;
import java.util.List;

/**
 * Example of Contribution analysis
 */
public class ContributionAnalysisExample {

    public static void main(String[] args) {
        // 1. Transfer biz data to indicator series info
        long currentTime = System.currentTimeMillis();
        List<IndicatorSeries> indicatorSeriesX0 = Arrays.asList(new IndicatorSeries(currentTime - 86400000 + 1, 1, "A")
                , new IndicatorSeries(currentTime - 86400000 + 2, 2, "B")
                , new IndicatorSeries(currentTime - 86400000 + 3, 3, "C")
                , new IndicatorSeries(currentTime - 86400000 + 4, 6, "D")
                , new IndicatorSeries(currentTime - 86400000 + 5, 5, "E")
        );
        List<IndicatorSeries> indicatorSeriesX1 = Arrays.asList(new IndicatorSeries(currentTime + 1, 1, "A")
                , new IndicatorSeries(currentTime + 2, 1.5, "B")
                , new IndicatorSeries(currentTime + 3, 3, "C")
                , new IndicatorSeries(currentTime + 4, 8, "D")
                , new IndicatorSeries(currentTime + 5, 3, "E")
        );
        IndicatorPairSeries series = new IndicatorPairSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesX1, indicatorSeriesX0);

        // 2. Get a PlusContributionAnalysisEngin object
        // PlusContributionAnalysisEngin the calculation of contributions to indicators of the additive/subtractive type.
        // MultiplyContributionAnalysisEngin the calculation of the contribution of indicators to the multiplication type.
        // DivisionContributionAnalysisEngin the calculation of the contribution to the indicators of division type
        PlusContributionAnalysisEngin engin = new PlusContributionAnalysisEngin(CausalAnalysisContext.createDefault());

        // 3. analyse
        ContributionResult result = engin.analyse(series);

        // 4. Business process analysis result. Like Records,Alarms,Print,Deep analysis...
        System.out.println(result);
    }

}
