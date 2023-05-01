import java.util.ArrayList;

public class Sphere extends Object3D
{
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();
    private double radius;
    private int frequency;

    public Sphere(int x, int y, int z, int frequency)
    {
        super();
        this.frequency = frequency;
        this.radius = 100;

        // (x, y, z) x=right/left y=up/down z=forward/backward
        
        //Generate the triangle faces of the geodesic sphere to the given frequency and radius
        

        Triangle[] triArray = new Triangle[triangles.size()];
        for(int i = 0; i < triangles.size(); i++)
            triArray[i] = triangles.get(i);

        setTriangles(triArray);
        translate(x, y, z);
        setAnchorToCenter();
    }

    public void setRadius(double radius){this.radius = radius;}
    public void setfrequency(int frequency){this.frequency = frequency;}
    public double getRadius(){return radius;}
    public int getfrequency(){return frequency;}
}
