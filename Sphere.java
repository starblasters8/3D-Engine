import java.util.ArrayList;

public class Sphere extends Object3D
{
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();
    private double radius;
    private int resolution;

    public Sphere(int x, int y, int z, int resolution)
    {
        super();
        this.resolution = resolution;
        this.radius = 100;

        // (x, y, z) x=right/left y=up/down z=forward/backward
        
        //Generate the triangle faces of the sphere to the given resolution and radius
        

        setTriangles((Triangle[])(triangles.toArray()));
        translate(x, y, z);
        setAnchorToCenter();
    }

    public void setRadius(double radius){this.radius = radius;}
    public void setResolution(int resolution){this.resolution = resolution;}
    public double getRadius(){return radius;}
    public int getResolution(){return resolution;}
}
