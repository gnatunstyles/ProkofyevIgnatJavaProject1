package org.spbstu.prokofyev.task1;

import java.util.Map;
import java.util.TreeMap;

public class Function {
    private TreeMap<Double, Double> map;

    public Function() {
        map = new TreeMap<>();
    }

    /**
     * Add new point
     *
     * @param x coordinate in the axis of abscissa
     * @param y coordinate in the axis of ordinate
     */
    public void add(double x, double y) {
        if (!map.containsKey(x))
            map.put(x, y);
    }

    /**
     * @return copy of function multiple
     */
    public TreeMap getMultiple() {
        return new TreeMap<>(map);
    }

    /**
     * Removes point by coordinate in the axis of abscissa
     *
     * @param x coordinate in the axis of abscissa
     */
    public void remove(double x) {
        if (map.containsKey(x)) map.remove(x);
    }

    /**
     * Searches the coordinate in the axis of ordinate
     * by the coordinate in the axis of abscissa
     * @param x coordinate in the axis of abscissa
     * @return coordinate in the axis of ordinate if it exists,
     * or null if it does not
     */
    public Double search(double x) {
        if (map.containsKey(x))
            return map.get(x);
        return null;
    }

    /**
     * Searches the nearest coordinate in the axis of ordinate
     *
     * @param x coordinate in the axis of abscissa
     * @return the nearest value in the given point
     */
    public Double searchNearest(double x) {
        if (x < map.firstKey()) return map.firstEntry().getValue();
        if (x > map.lastKey()) return map.lastEntry().getValue();
        double floor = map.floorKey(x);
        double ceiling = map.ceilingKey(x);
        return Math.abs(x - floor) < Math.abs(x - ceiling) ? map.get(floor) : map.get(ceiling);
    }

    /**
     * Interpolate value of the function by the Lagrange's method
     *
     * @param x coordinate in the axis of abscissa
     * @return interpolated value
     */
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