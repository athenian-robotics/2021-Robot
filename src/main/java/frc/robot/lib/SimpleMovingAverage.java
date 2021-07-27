package frc.robot.lib;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleMovingAverage {

    // queue used to store list so that we get the average
    private final Queue<Double> Dataset = new LinkedList<Double>();
    private final int period;
    private double sum;

    // constructor to initialize period
    public SimpleMovingAverage(int period) {
        this.period = period;
    }

    public void addData(double num) {
        sum += num;
        Dataset.add(num);

        if (Dataset.size() > period) {
            sum -= Dataset.remove();
        }
    }

    public double getMean() {
        return sum / period;
    }
}

