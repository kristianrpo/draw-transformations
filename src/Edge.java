import MathElements.Point3;

public class Edge {
    private Point3 start;
    private Point3 end;

    public Edge(Point3 start, Point3 end) {
        this.start = start;
        this.end = end;
    }

    public Point3 getStart() {
        return start;
    }

    public Point3 getEnd() {
        return end;
    }

    public void setStart(Point3 start) {
        this.start = start;
    }
    public void setEnd(Point3 end) {
        this.end = end;
    }
}
