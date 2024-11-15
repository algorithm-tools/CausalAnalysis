package org.algorithmtools.ca4j.calculator;

import org.algorithmtools.ca4j.enumtype.IndicatorStatType;
import org.algorithmtools.ca4j.pojo.*;
import org.algorithmtools.ca4j.pojo.result.ContributionResult;
import org.algorithmtools.ca4j.pojo.result.JSDivergenceResult;
import org.algorithmtools.ca4j.utils.IndicatorSeriesUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestCalculator {

    public CausalAnalysisLog log;

    @Before
    public void before(){
        log = new CausalAnalysisLog();
    }

    @Test
    public void testJSDivergence(){
        double[] dataP = new double[]{1,1.5,2,3,4,5,6,7};
        double[] dataQ = new double[]{1,1,2,3,4,5,4,7};
        List<IndicatorSeries> indicatorSeriesP = IndicatorSeriesUtil.transferFromArray(dataP);
        List<IndicatorSeries> indicatorSeriesQ = IndicatorSeriesUtil.transferFromArray(dataQ);
        IndicatorPairSeries pairInfo = new IndicatorPairSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesP, indicatorSeriesQ);

        JSDivergenceCalculator calculator = new JSDivergenceCalculator();
        calculator.init(new CausalAnalysisContext());
        calculator.checkCompatibility(pairInfo, log);
        JSDivergenceResult calculate = calculator.calculate(pairInfo, log);

        System.out.println(calculate);
    }

    @Test
    public void testContributionPlusCalculator(){
        List<IndicatorSeries> indicatorSeriesX0 = Arrays.asList(new IndicatorSeries(2, 1, "B")
                , new IndicatorSeries(3, 2, "C")
                , new IndicatorSeries(3, 3, "D")
                , new IndicatorSeries(3, 6, "E")
                , new IndicatorSeries(3, 5, "F")
        );
        List<IndicatorSeries> indicatorSeriesX1 = Arrays.asList(new IndicatorSeries(2, 1, "A")
                , new IndicatorSeries(3, 1.5, "B")
                , new IndicatorSeries(3, 3, "C")
                , new IndicatorSeries(3, 8, "D")
                , new IndicatorSeries(3, 3, "E")
        );
        IndicatorPairSeries pairInfo = new IndicatorPairSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesX1, indicatorSeriesX0);

        PlusContributionCalculator calculator = new PlusContributionCalculator();
        calculator.init(new CausalAnalysisContext());
        calculator.checkCompatibility(pairInfo, log);
        ContributionResult calculate = calculator.calculate(pairInfo, log);

        System.out.println(calculate);
    }

    @Test
    public void testContributionMultiplyCalculator(){
        List<IndicatorSeries> indicatorSeriesX0 = Arrays.asList(new IndicatorSeries(2, 100, "A"), new IndicatorSeries(3, 100, "B"));
        List<IndicatorSeries> indicatorSeriesX1 = Arrays.asList(new IndicatorSeries(1, 128, "A"), new IndicatorSeries(2, 64, "B"));
        IndicatorPairSeries pairInfo = new IndicatorPairSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesX1, indicatorSeriesX0);

        MultiplyContributionCalculator calculator = new MultiplyContributionCalculator();
        calculator.init(new CausalAnalysisContext());
        calculator.checkCompatibility(pairInfo, log);
        ContributionResult calculate = calculator.calculate(pairInfo, log);

        System.out.println(calculate);
    }

    @Test
    public void testContributionDivisionCalculator(){
        List<IndicatorSeries> indicatorSeriesX1Numerator = Arrays.asList(new IndicatorSeries(2, 15, "B"), new IndicatorSeries(3, 20, "C"));
        List<IndicatorSeries> indicatorSeriesX1Denominator = Arrays.asList(new IndicatorSeries(1, 15, "A"), new IndicatorSeries(2, 15, "B"), new IndicatorSeries(3, 25, "C"));
        List<IndicatorSeries> indicatorSeriesX0Numerator = Arrays.asList(new IndicatorSeries(3, 25, "C"), new IndicatorSeries(4, 30, "D"));
        List<IndicatorSeries> indicatorSeriesX0Denominator = Arrays.asList(new IndicatorSeries(1, 30, "B"), new IndicatorSeries(3, 35, "C"), new IndicatorSeries(4, 40, "D"));
        IndicatorDivisionSeries series = new IndicatorDivisionSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesX1Numerator, indicatorSeriesX1Denominator, indicatorSeriesX0Numerator, indicatorSeriesX0Denominator);

        DivisionContributionCalculator calculator = new DivisionContributionCalculator();
        calculator.init(new CausalAnalysisContext());
        calculator.checkCompatibility(series, log);
        ContributionResult calculate = calculator.calculate(series, log);

        System.out.println(calculate);
    }



}
