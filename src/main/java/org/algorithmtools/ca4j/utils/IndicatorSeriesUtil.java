package org.algorithmtools.ca4j.utils;

import org.algorithmtools.ca4j.pojo.IndicatorSeries;

import java.util.ArrayList;
import java.util.List;

public class IndicatorSeriesUtil {

    public static List<IndicatorSeries> transferFromArray(double[] array){
        List<IndicatorSeries> list = new ArrayList<IndicatorSeries>();
        for (int i = 0; i < array.length; i++) {
            list.add(i, new IndicatorSeries(i, array[i], String.valueOf(i)));
        }
        return list;
    }

    public static double[] transferToArray(List<IndicatorSeries> series){
        double[] resultArray = new double[series.size()];
        for (int i = 0; i < series.size(); i++) {
            resultArray[i] = series.get(i).getValue();
        }
        return resultArray;
    }

}
