package org.algorithmtools.ca4j.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.algorithmtools.ca4j.enumtype.IndicatorStatType;
import org.algorithmtools.ca4j.pojo.IndicatorDivisionSeries;
import org.algorithmtools.ca4j.pojo.IndicatorPairSeries;
import org.algorithmtools.ca4j.pojo.IndicatorSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static IndicatorDivisionSeries transferFromJson(String jsonString){
        JSONObject jsonData = JSONObject.parseObject(jsonString);
        JSONArray currentDenominatorList = jsonData.getJSONArray("currentDenominatorList");
        JSONArray currentNumeratorList = jsonData.getJSONArray("currentNumeratorList");
        JSONArray comparisonNumeratorList = jsonData.getJSONArray("comparisonNumeratorList");
        JSONArray comparisonDenominatorList = jsonData.getJSONArray("comparisonDenominatorList");
        String indicator = jsonData.getString("indicator");
        String indicatorName = jsonData.getString("indicatorName");
        String statType = jsonData.getString("statType");

        IndicatorDivisionSeries series = new IndicatorDivisionSeries(indicator, indicatorName, IndicatorStatType.valueOf(statType));
        series.setCurrentNumeratorList(transferFromJsonArray(currentNumeratorList));
        series.setCurrentDenominatorList(transferFromJsonArray(currentDenominatorList));
        series.setComparisonNumeratorList(transferFromJsonArray(comparisonNumeratorList));
        series.setComparisonDenominatorList(transferFromJsonArray(comparisonDenominatorList));

        return series;
    }

    public static IndicatorPairSeries transferFromJsonForPairSeries(String jsonString){
        JSONObject jsonData = JSONObject.parseObject(jsonString);
        JSONArray currentList = jsonData.getJSONArray("currentList");
        JSONArray comparisonList = jsonData.getJSONArray("comparisonList");
        String indicator = jsonData.getString("indicator");
        String indicatorName = jsonData.getString("indicatorName");
        String statType = jsonData.getString("statType");

        IndicatorPairSeries series = new IndicatorPairSeries(indicator, indicatorName, IndicatorStatType.valueOf(statType));
        series.setCurrentList(transferFromJsonArray(currentList));
        series.setComparisonList(transferFromJsonArray(comparisonList));

        return series;
    }

    public static List<IndicatorSeries> transferFromJsonArray(JSONArray jsonArray){
        return jsonArray.stream().map(v -> {
            JSONObject data = (JSONObject) v;
            return new IndicatorSeries(data.getLong("time"), data.getDoubleValue("value"), data.getString("logicalIndex"));
        }).collect(Collectors.toList());
    }

}
