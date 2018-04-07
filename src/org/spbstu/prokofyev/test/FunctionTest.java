package org.spbstu.prokofyev.test;

import org.junit.jupiter.api.Test;
import org.spbstu.prokofyev.task1.Function;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTest {
    private Function fun;

    @Test
    void add() {
        fun = new Function();
        fun.add(1.0, 0.0);
        assertEquals(0.0, (double) fun.search(1.0));
    }

    @Test
    void remove() {
        fun = new Function();
        fun.add(1.0, 0.0);
        fun.remove(1.0);
        assertEquals(null, fun.search(1.0));

    }

    @Test
    void giveMultiple() {
        fun = new Function();
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
    void search() {
        fun = new Function();
        fun.add(2.0, 5.0);
        fun.search(2.0);
        assertEquals(5.0, (double) fun.search(2.0));
    }

    @Test
    void searchNearest() throws Exception {
        fun = new Function();
        fun.add(1.0, -4.0);
        fun.add(2.0, -1.0);
        fun.searchNearest(3.0);
        assertEquals(-1.0, (double) fun.searchNearest(3.0));
    }

    @Test
    void interpolate() {
        fun = new Function();
        fun.add(1.0, 1.0);
        fun.add(2.0, 2.0);
        assertEquals(3.0, (double) fun.interpolate(3.0));
    }

}