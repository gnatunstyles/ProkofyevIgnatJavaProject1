import java.util.LinkedList;

public class Function {
    private LinkedList<Point> list;

    public Function() {
        list = new LinkedList<>();
    }

    public void add(double x, double y) {
        list.add(new Point(x, y));
    }

    public void add(Point point) {
        list.add(point);
    }

}
