import MathElements.Matrix3x3;
import MathElements.Matrix4x4;
import MathElements.Point3;
import MathElements.Point4;

import static MathElements.Matrix3x3.times;

public class Transforms {
    public static Point3 calculateCenter(Point3[] vertexes) {
        double sumX = 0;
        double sumY = 0;
        for (Point3 vertex : vertexes) {
            sumX += vertex.getX();
            sumY += vertex.getY();
        }
        double centerX = sumX / vertexes.length;
        double centerY = sumY / vertexes.length;
        return new Point3(centerX, centerY, 1);
    }

    public static void applyTransformation(Point3[] vertexes, Edge[] edges, Matrix3x3 translationMatrix,
                                           Matrix3x3 rotationMatrix, Matrix3x3 scalingMatrix, int flagTransformation) {
        for (int i = 0; i < vertexes.length; i++) {
            if (flagTransformation == 1) {
                vertexes[i] = times(translationMatrix, vertexes[i]);
            } else if (flagTransformation == 2) {
                vertexes[i] = times(rotationMatrix, vertexes[i]);
            } else if (flagTransformation == 3) {
                vertexes[i] = times(scalingMatrix, vertexes[i]);
            }
        }
        for (Edge edge : edges) {
            if (flagTransformation == 1) {
                edge.setStart(times(translationMatrix, edge.getStart()));
                edge.setEnd(times(translationMatrix, edge.getEnd()));
            } else if (flagTransformation == 2) {
                edge.setStart(times(rotationMatrix, edge.getStart()));
                edge.setEnd(times(rotationMatrix, edge.getEnd()));
            } else if (flagTransformation == 3) {
                edge.setStart(times(scalingMatrix, edge.getStart()));
                edge.setEnd(times(scalingMatrix, edge.getEnd()));
            }

        }
    }

    public static void translate(Point3[] vertexes, Edge[] edges, Matrix3x3 translationMatrix, int x, int y, int numberOfRotations) {
        Point3 pointToMove = new Point3(x,y,1);
        if (numberOfRotations!=0){
            Point3 newPoint = Matrix3x3.times(Matrix3x3.rotation(10*numberOfRotations),pointToMove);
            translationMatrix = Matrix3x3.translation(newPoint.getX(), newPoint.getY());
            applyTransformation(vertexes, edges, translationMatrix, null, null, 1);
        }
        else{
            translationMatrix = Matrix3x3.translation(x, y);
            applyTransformation(vertexes, edges, translationMatrix, null, null, 1);
        }
    }

    public static void rotate(Point3[] vertexes, Edge[] edges, Matrix3x3 translationMatrix, Matrix3x3 rotationMatrix,
                              int degrees) {
        Point3 center = calculateCenter(vertexes);
        translationMatrix = Matrix3x3.translation(-center.getX(), -center.getY());
        applyTransformation(vertexes, edges, translationMatrix, null, null, 1);
        rotationMatrix = Matrix3x3.rotation(degrees);
        applyTransformation(vertexes, edges, null, rotationMatrix, null, 2);
        translationMatrix = Matrix3x3.translation(center.getX(), center.getY());
        applyTransformation(vertexes, edges, translationMatrix, null, null, 1);
    }

    public static void scale(Point3[] vertexes, Edge[] edges, Matrix3x3 translationMatrix, Matrix3x3 scalingMatrix,
                             double factor) {
        Point3 center = calculateCenter(vertexes);
        translationMatrix = Matrix3x3.translation(-center.getX(), -center.getY());
        applyTransformation(vertexes, edges, translationMatrix, null, null, 1);
        scalingMatrix = Matrix3x3.scaling(factor, factor);
        applyTransformation(vertexes, edges, null, null, scalingMatrix, 3);
        translationMatrix = Matrix3x3.translation(center.getX(), center.getY());
        applyTransformation(vertexes, edges, translationMatrix, null, null, 1);
    }
}
