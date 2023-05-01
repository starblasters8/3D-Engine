import java.util.ArrayList;

public class Sphere extends Object3D
{
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();
    private double radius;
    private int detailLevel;

    public Sphere(int x, int y, int z, int detailLevel)
    {
        super();
        this.detailLevel = detailLevel;
        this.radius = 100;

        // (x, y, z) x=right/left y=up/down z=forward/backward
        
        //Generate the triangle faces of the geodesic sphere to the given detailLevel and radius
        
        

        Triangle[] triArray = new Triangle[triangles.size()];
        for(int i = 0; i < triangles.size(); i++)
            triArray[i] = triangles.get(i);

        setTriangles(triArray);
        translate(x, y, z);
        setAnchorToCenter();
    }

    public void setRadius(double radius){this.radius = radius;}
    public void setdetailLevel(int detailLevel){this.detailLevel = detailLevel;}
    public double getRadius(){return radius;}
    public int getdetailLevel(){return detailLevel;}
}
