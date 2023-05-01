import java.util.ArrayList;

public class Sphere extends Object3D
{
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();

    public Sphere(int x, int y, int z, int detail)
    {
        super();        
        //Generate the triangle faces of a geodesic sphere
        

        Triangle[] triArray = new Triangle[triangles.size()];
        for(int i = 0; i < triangles.size(); i++)
            triArray[i] = triangles.get(i);

        setTriangles(triArray);
        translate(x, y, z);
        setAnchorToCenter();
    }

}
