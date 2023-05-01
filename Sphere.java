import java.util.ArrayList;

public class Sphere extends Object3D
{
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();
    private double radius;

    public Sphere(int x, int y, int z, double radius)
    {
        super();
        this.radius = 100;

        // (x, y, z) x=right/left y=up/down z=forward/backward
        
        //Generate the triangle faces of the sphere
        
        

        Triangle[] triArray = new Triangle[triangles.size()];
        for(int i = 0; i < triangles.size(); i++)
            triArray[i] = triangles.get(i);

        setTriangles(triArray);
        translate(x, y, z);
        setAnchorToCenter();
    }

}
