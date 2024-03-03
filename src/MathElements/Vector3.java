package MathElements;

import java.lang.Math;
public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y,2) + Math.pow(this.z,2));
    }

    public void normalize(){
        double temp = magnitude();
        this.x = this.x/temp;
        this.y = this.y/temp;
        this.z = this.z/temp;
    }

    public static double dotProduct(Vector3 v1, Vector3 v2){
        return (v1.x*v2.x)+(v1.y*v2.y)+(v1.z*v2.z);
    }

    public static Vector3 crossProduct(Vector3 v1, Vector3 v2){
        return new Vector3(((v1.y*v2.z)-(v1.z*v2.y)),
                -1*((v1.x*v2.z)-(v1.z*v2.x)),
                ((v1.x*v2.y)-(v1.y*v2.x)));
    }

    public static void main(String[] args) {
        Vector3 v1 = new Vector3(1,2,3);
        Vector3 v2 = new Vector3(4,5,6);
        System.out.println(crossProduct(v1,v2).x);
        System.out.println(crossProduct(v1,v2).y);
        System.out.println(crossProduct(v1,v2).z);
    }
    public String toString(){
        return this.x + ", " + this.y + ", " + this.z + "\n";
    }
}