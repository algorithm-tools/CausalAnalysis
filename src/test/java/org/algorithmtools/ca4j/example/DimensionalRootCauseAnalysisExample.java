package org.algorithmtools.ca4j.example;

import org.algorithmtools.ca4j.engine.DimensionalRootCauseAnalysisEngin;
import org.algorithmtools.ca4j.enumtype.IndicatorStatType;
import org.algorithmtools.ca4j.pojo.CausalAnalysisContext;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.IndicatorSeries;
import org.algorithmtools.ca4j.pojo.result.JSDivergenceResult;

import java.util.Arrays;
import java.util.List;

/**
 * Example of dimensional root cause analysis
 */
public class DimensionalRootCauseAnalysisExample {

    public static void main(String[] args) {
        // 1. Transfer biz data to indicator series info
        long currentTime = System.currentTimeMillis();
        List<IndicatorSeries> indicatorSeriesP = Arrays.asList(new IndicatorSeries(currentTime - 86400000 + 1, 1, "A")
                , new IndicatorSeries(currentTime - 86400000 + 1, 2, "B")
                , new IndicatorSeries(currentTime - 86400000 + 1, 3, "C")
                , new IndicatorSeries(currentTime - 86400000 + 1, 6, "D")
                , new IndicatorSeries(currentTime - 86400000 + 1, 5, "E")
        );
        List<IndicatorSeries> indicatorSeriesQ = Arrays.asList(new IndicatorSeries(2, 1, "A")
                , new IndicatorSeries(currentTime + 1, 1.5, "B")
                , new IndicatorSeries(currentTime + 1, 3, "C")
                , new IndicatorSeries(currentTime + 1, 8, "D")
                , new IndicatorSeries(currentTime + 1, 3, "E")
        );
        IndicatorPairSeries series = new IndicatorPairSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesP, indicatorSeriesQ);

        // 2. Get a DimensionalRootCauseAnalysisEngin object
        DimensionalRootCauseAnalysisEngin engin = new DimensionalRootCauseAnalysisEngin(CausalAnalysisContext.createDefault());

        // 3. analyse
        JSDivergenceResult result = engin.analyse(series);

        // 4. Business process analysis result. Like Records,Alarms,Print,Deep analysis...
        System.out.println(result);
    }

}
