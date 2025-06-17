package org.algorithmtools.ca4j.pojo.result;

public class JSDivergenceResult extends CausalAnalysisResult{

    private String dimension;
    private double JSDivergence;

    public JSDivergenceResult(String dimension, double JSDivergence) {
        this.JSDivergence = JSDivergence;
        this.dimension = dimension;
    }

    public double getJSDivergence() {
        return JSDivergence;
    }

    public String getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        return "JSDivergenceResult{" +
                "dimension='" + dimension + '\'' +
                ", JSDivergence=" + JSDivergence +
                '}';
    }
}
