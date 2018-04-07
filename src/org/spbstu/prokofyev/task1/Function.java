package org.spbstu.prokofyev.task1;

import java.util.Map;
import java.util.TreeMap;

public class Function {
    private TreeMap<Double, Double> map;

    public Function() {
        map = new TreeMap<>();
    }

    public void add(double x, double y) {
        map.put(x, y);
    }

    public TreeMap getMultiple() {
        return new TreeMap<>(map);
    }

    public void remove(double x) {
        if (map.containsKey(x)) map.remove(x);
    }


    public Double search(double x) {
        if (map.containsKey(x))
            return map.get(x);
        return null;
    }

    public Double searchNearest(double x) {
        if (x < map.firstKey()) return map.firstEntry().getValue();
        if (x > map.lastKey()) return map.lastEntry().getValue();
        double floor = map.floorKey(x);
        double ceiling = map.ceilingKey(x);
        return Math.abs(x - floor) < Math.abs(x - ceiling) ? map.get(floor) : map.get(ceiling);
    }

    public Double interpolate(double x) {
        double result = 0;
        for (Map.Entry entryI : map.entrySet()) {
            double basicsPolynomial = 1.0;
            for (Map.Entry entryJ : map.entrySet()) {
                if (entryJ.getKey() != entryI.getKey()) {
                    basicsPolynomial *= (x - (double) entryJ.getKey()) /
                            ((double) entryI.getKey() - (double) entryJ.getKey());
                }
            }
            result += basicsPolynomial * (double) entryI.getValue();
        }
        return result;
    }


    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass().equals(o.getClass())) {
            Function fun = (Function) o;
            return map.equals(fun.map);
        }
        return false;
    }
}