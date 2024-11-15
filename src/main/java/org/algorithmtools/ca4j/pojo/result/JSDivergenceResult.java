package org.algorithmtools.ca4j.pojo.result;

public class JSDivergenceResult extends CausalAnalysisResult{

    private double JSDivergence;

    public JSDivergenceResult(double JSDivergence) {
        this.JSDivergence = JSDivergence;
    }

    public double getJSDivergence() {
        return JSDivergence;
    }

    @Override
    public String toString() {
        return "JSDivergenceResult{" +
                "JSDivergence=" + JSDivergence +
                '}';
    }
}
