package org.algorithmtools.ca4j.calculator;

import org.algorithmtools.ca4j.enumtype.IndicatorStatType;
import org.algorithmtools.ca4j.pojo.IndicatorDivisionSeries;
import org.algorithmtools.ca4j.pojo.IndicatorSeries;
import org.algorithmtools.ca4j.utils.IndicatorCalculateUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestIndicatorCalculateUtil {


    @Test
    public void testAlign(){
        List<IndicatorSeries> indicatorSeriesX1Numerator = Arrays.asList(new IndicatorSeries(2, 15, "B"), new IndicatorSeries(3, 20, "C"));
        List<IndicatorSeries> indicatorSeriesX1Denominator = Arrays.asList(new IndicatorSeries(1, 15, "A"), new IndicatorSeries(2, 15, "B"), new IndicatorSeries(3, 25, "C"));
        List<IndicatorSeries> indicatorSeriesX0Numerator = Arrays.asList(new IndicatorSeries(3, 25, "C"), new IndicatorSeries(4, 30, "D"));
        List<IndicatorSeries> indicatorSeriesX0Denominator = Arrays.asList(new IndicatorSeries(1, 30, "B"), new IndicatorSeries(3, 35, "C"), new IndicatorSeries(4, 40, "D"));
        IndicatorDivisionSeries series = new IndicatorDivisionSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesX1Numerator, indicatorSeriesX1Denominator, indicatorSeriesX0Numerator, indicatorSeriesX0Denominator);
        System.out.println(series);

        System.out.println("========================================================================");
        System.out.println();

        IndicatorDivisionSeries alignResult = IndicatorCalculateUtil.indicatorAlignThenSorted(series, 0.000001);
        System.out.println(alignResult);
    }

}
