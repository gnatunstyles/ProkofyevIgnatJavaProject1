import javafx.util.Pair;

import java.util.LinkedList;

public class Function {
    private LinkedList<Pair<Double, Double>> list;

    public Function() {
        list = new LinkedList();
    }

    public void add(double x, double y) {
        list.add(new Pair(x, y));
    }

    public void add(Pair pair) {
        list.add(pair);
    }

    public void remove(double x) {
        for (Pair<Double, Double> pair : list) {
            if (pair.getKey() == x) {
                list.remove(pair);
                return;
            }
        }
    }

    public Double search(double x) {
        for (Pair<Double, Double> pair : list) {
            if (x == pair.getKey()) {
                return pair.getValue();
            }
        }
        return null;
    }

    public Double searchNearest(double x) {
        double distance = .0;
        double nearest = .0;
        for (int i = 0; i < list.size(); i++) {
            if (distance == .0) {
                distance = Math.abs((Double) list.get(i).getKey() - x);
                nearest = (Double) list.get(i).getValue();
            }
            if (Math.abs((Double) list.get(i).getKey() - x) <= distance) {
                distance = Math.abs((Double) list.get(i).getKey() - x);
                nearest = (Double) list.get(i).getValue();
            }
        }
        return Math.abs(nearest);
    }

    public Double interpolation(double x) {
        double result = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            double basicsPolynom = 1.0;
            for (int j = 0; j < size; j++) {
                if (j != i) {
                    basicsPolynom *= (x - list.get(j).getKey()) / (list.get(i).getKey() - list.get(j).getKey());
                }
            }
            result += basicsPolynom * list.get(i).getValue();
        }
        return result;

    }

    @Override
    public String toString() {
        return list.toString();
    }

}