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

    @Test
    public void testContributionDivisionCalculator_forZero(){
        List<IndicatorSeries> indicatorSeriesX1Numerator = Arrays.asList(new IndicatorSeries(1, 100, "B"), new IndicatorSeries(2, 200, "C"), new IndicatorSeries(3, 300, "D"));
        List<IndicatorSeries> indicatorSeriesX1Denominator = Arrays.asList(new IndicatorSeries(1, 20, "B"), new IndicatorSeries(2, 20, "C"), new IndicatorSeries(3, 0, "D"));
        List<IndicatorSeries> indicatorSeriesX0Numerator = Arrays.asList(new IndicatorSeries(1, 100, "B"), new IndicatorSeries(2, 100, "C"), new IndicatorSeries(3, 100, "D"));
        List<IndicatorSeries> indicatorSeriesX0Denominator = Arrays.asList(new IndicatorSeries(1, 20, "B"), new IndicatorSeries(2, 20, "C"), new IndicatorSeries(3, 0, "D"));
        IndicatorDivisionSeries series = new IndicatorDivisionSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesX1Numerator, indicatorSeriesX1Denominator, indicatorSeriesX0Numerator, indicatorSeriesX0Denominator);

        DivisionContributionCalculator calculator = new DivisionContributionCalculator();
        calculator.init(new CausalAnalysisContext());
        calculator.checkCompatibility(series, log);
        ContributionResult calculate = calculator.calculate(series, log);

        System.out.println(calculate);
    }

    @Test
    public void testContributionDivisionCalculatorFromJsonData(){
        String jsonData = "{\"comparisonDenominatorList\":[{\"logicalIndex\":\"Mobupps_agency\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"Bignox\",\"time\":1734004389699,\"value\":9.0},{\"logicalIndex\":\"Facebook\",\"time\":1734004389699,\"value\":373.0},{\"logicalIndex\":\"Google\",\"time\":1734004389699,\"value\":1020.0},{\"logicalIndex\":\"ASA\",\"time\":1734004389699,\"value\":69.0},{\"logicalIndex\":\"Unityads\",\"time\":1734004389699,\"value\":573.0},{\"logicalIndex\":\"my_media_source\",\"time\":1734004389699,\"value\":5.0},{\"logicalIndex\":\"Officialwebsite\",\"time\":1734004389699,\"value\":2.0},{\"logicalIndex\":\"organic\",\"time\":1734004389699,\"value\":836.0},{\"logicalIndex\":\"Bluestacks\",\"time\":1734004389699,\"value\":7.0},{\"logicalIndex\":\"Ldplayer\",\"time\":1734004389699,\"value\":19.0}],\"comparisonNumeratorList\":[{\"logicalIndex\":\"Mobupps_agency\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"Bignox\",\"time\":1734004389699,\"value\":95.0},{\"logicalIndex\":\"Facebook\",\"time\":1734004389699,\"value\":14166.51},{\"logicalIndex\":\"Google\",\"time\":1734004389699,\"value\":13670.95},{\"logicalIndex\":\"ASA\",\"time\":1734004389699,\"value\":862.14},{\"logicalIndex\":\"Unityads\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"my_media_source\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"Officialwebsite\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"organic\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"Bluestacks\",\"time\":1734004389699,\"value\":125.0},{\"logicalIndex\":\"Ldplayer\",\"time\":1734004389699,\"value\":190.0}],\"currentDenominatorList\":[{\"logicalIndex\":\"Mobupps_agency\",\"time\":1734004389699,\"value\":4.0},{\"logicalIndex\":\"Bignox\",\"time\":1734004389699,\"value\":7.0},{\"logicalIndex\":\"Facebook\",\"time\":1734004389699,\"value\":295.0},{\"logicalIndex\":\"Google\",\"time\":1734004389699,\"value\":1038.0},{\"logicalIndex\":\"ASA\",\"time\":1734004389699,\"value\":95.0},{\"logicalIndex\":\"Unityads\",\"time\":1734004389699,\"value\":597.0},{\"logicalIndex\":\"my_media_source\",\"time\":1734004389699,\"value\":1.0},{\"logicalIndex\":\"Officialwebsite\",\"time\":1734004389699,\"value\":5.0},{\"logicalIndex\":\"organic\",\"time\":1734004389699,\"value\":868.0},{\"logicalIndex\":\"Bluestacks\",\"time\":1734004389699,\"value\":9.0},{\"logicalIndex\":\"Ldplayer\",\"time\":1734004389699,\"value\":15.0}],\"currentNumeratorList\":[{\"logicalIndex\":\"Mobupps_agency\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"Bignox\",\"time\":1734004389699,\"value\":70.0},{\"logicalIndex\":\"Facebook\",\"time\":1734004389699,\"value\":12467.49},{\"logicalIndex\":\"Google\",\"time\":1734004389699,\"value\":12843.58},{\"logicalIndex\":\"ASA\",\"time\":1734004389699,\"value\":861.13},{\"logicalIndex\":\"Unityads\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"my_media_source\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"Officialwebsite\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"organic\",\"time\":1734004389699,\"value\":0.0},{\"logicalIndex\":\"Bluestacks\",\"time\":1734004389699,\"value\":80.0},{\"logicalIndex\":\"Ldplayer\",\"time\":1734004389699,\"value\":125.0}],\"indicator\":\"registerCost\",\"indicatorName\":\"注册账号成本\",\"statType\":\"Unique_Continuity\"}";
        IndicatorDivisionSeries series = IndicatorSeriesUtil.transferFromJson(jsonData);

        DivisionContributionCalculator calculator = new DivisionContributionCalculator();
        calculator.init(new CausalAnalysisContext());
        calculator.checkCompatibility(series, log);
        ContributionResult calculate = calculator.calculate(series, log);

        System.out.println(calculate);
    }


    @Test
    public void test(){
        double a = 0;
        double b = 0;
        double c = 1;
        System.out.println((a / b) / c);
    }


}
