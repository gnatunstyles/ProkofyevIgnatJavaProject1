package org.spbstu.prokofyev.java.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spbstu.prokofyev.java.main.task1.Function;

import java.util.TreeMap;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionTest {

    private Function fun;

    @BeforeEach
    void inEach() {
        fun = new Function();
    }

    @Test
    void createEmpty() {
        Function fun1 = new Function();
        assertTrue(fun.equals(fun1));
        assertEquals("{}", fun.toString());
    }

    @Test
    void add() {
        fun.add(1.0, 0.0);
        assertEquals(0.0, (double) fun.search(1.0));
    }

    @Test
    void remove() {
        fun.add(1.0, 0.0);
        fun.remove(1.0);
        assertEquals(null, fun.search(1.0));

    }


    @Test
    void giveMultiple() {
        fun.add(1.0, 3.0);
        fun.add(14.0, 5.0);
        fun.add(12.0, 9.0);
        TreeMap<Double, Double> map = new TreeMap<>();
        map.put(1.0, 3.0);
        map.put(14.0, 5.0);
        map.put(12.0, 9.0);
        assertEquals(map, fun.getMultiple());
    }

    @Test
    void giveMultipleEmpty() {
        TreeMap<Double, Double> map = new TreeMap<>();
        assertEquals(map, fun.getMultiple());
    }

    @Test
    void search() {
        fun.add(2.0, 5.0);
        assertEquals(5.0, (double) fun.search(2.0));
    }

    @Test
    void searchEmpty() {
        assertEquals(null, fun.search(2.0));
    }

    @Test
    void searchNearest() {
        fun.add(1.0, -4.0);
        fun.add(2.0, -1.0);
        fun.searchNearest(3.0);
        assertEquals(-1.0, (double) fun.searchNearest(3.0));
        assertEquals(-1.0, (double) fun.searchNearest(Double.MAX_VALUE));
        assertEquals(-4.0, (double) fun.searchNearest(-Double.MAX_VALUE));
    }

    @Test
    void searchNearestEmpty() {
        assertEquals(null, fun.searchNearest(2.3));
    }

    @Test
    void interpolate() {
        fun.add(4.0, 6.0);
        fun.add(2.0, 9.0);
        assertEquals(7.5, (double) fun.interpolate(3.0));
    }

    @Test
    void interpolateInfinity() {
        fun.add(4.0, 6.0);
        fun.add(2.0, 9.0);
        assertEquals(NaN, (double) fun.interpolate(Double.MAX_VALUE));
        assertEquals(NaN, (double) fun.interpolate(-Double.MAX_VALUE));
    }

    @Test
    void interpolateEmpty() {
        assertEquals(null, fun.interpolate(0.0));
    }
}

