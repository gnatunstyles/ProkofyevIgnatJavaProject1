import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionTest {
    private Function fun;

    @Test
    public void add() {
        fun = new Function();
        fun.add(1.0, 0.0);
        assertEquals("[1.0=0.0]", fun.toString());
    }

    @Test
    public void remove() {
        fun = new Function();
        fun.add(1.0, 0.0);
        fun.remove(1.0);
        assertEquals("[]", fun.toString());

    }

    @Test
    public void search() {
        fun = new Function();
        fun.add(2.0, 5.0);
        fun.search(2.0);
        assertEquals("5.0", fun.search(2.0).toString());
    }

    @Test
    public void searchNearest() throws Exception {
        fun = new Function();
        fun.add(1.0, -4.0);
        fun.add(2.0, -1.0);
        fun.searchNearest(3.0);
        assertEquals("1.0", fun.searchNearest(3.0).toString());
    }

    @Test
    public void interpolation() {
        fun = new Function();
        fun.add(1.0, 1.0);
        fun.add(2.0, 2.0);
        assertEquals("1.0", fun.interpolation(1.0).toString());
    }
}
