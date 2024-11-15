package org.algorithmtools.ca4j.pojo;

import java.util.Arrays;

public class ProbabilityDistribution {
    private double[] P;
    private double[] Q;

    public ProbabilityDistribution(double[] p, double[] q) {
        P = p;
        Q = q;
    }


    public double[] getP() {
        return P;
    }

    public void setP(double[] p) {
        P = p;
    }

    public double[] getQ() {
        return Q;
    }

    public void setQ(double[] q) {
        Q = q;
    }

    @Override
    public String toString() {
        return "ProbabilityDistribution{" +
                "P=" + Arrays.toString(P) +
                ", Q=" + Arrays.toString(Q) +
                '}';
    }
}
