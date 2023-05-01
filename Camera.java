import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Camera extends Vertex
{
    private double rotX=0, rotY=0, rotZ=0;

    public Camera(int x, int y, int z)
    {
        super(x, y, z);
    }

// Draw all objects in the scene
public void drawAll(Graphics2D g, HashMap<String, Object3D> objectHash)
{
    // Remove all invis objects from being rendered
    ArrayList<Object3D> unSorted = new ArrayList<Object3D>();

    for(String key : objectHash.keySet())
    {
        if(objectHash.get(key).getRender())
            unSorted.add(objectHash.get(key));
    }

    // Convert to an array
    Object3D[] objects = new Object3D[unSorted.size()];
    for(int i = 0; i < objects.length; i++)
        objects[i] = unSorted.get(i);

    // Get the number of triangles
    int tris = 0;
    for(Object3D obj : objects)
        tris += obj.getTriNum();

    // Create the array of triangles
    Triangle[] triangleArray = new Triangle[tris];
    // Populate the array
    int count = 0;
    for(Object3D obj : objects)
    {
        Triangle[] objTris = obj.getTriangles();
        for(int i = 0; i < objTris.length; i++)
            triangleArray[count++] = objTris[i];
    }

    // Sort the triangles
    triangleArray = sortTriangles(triangleArray);
    // Draw the triangles
    for (Triangle triangle : triangleArray) 
    {
        g.setColor(triangle.getColor());
        g.fillPolygon(triangle.getXCoords(), triangle.getYCoords(), 3);
    }
}

public Triangle[] sortTriangles(Triangle[] triangles) // Sort the Triangles from furthest away to closest to the camera
{
    Triangle[] sorted = new Triangle[triangles.length];
    ArrayList<Triangle> unSorted = new ArrayList<Triangle>();

    for(Triangle triangle : triangles)
        unSorted.add(triangle);

    for(int i = 0; i < sorted.length; i++)
    {
        double maxDistance = 0;
        int maxIndex = 0;
        for(int j = 0; j < unSorted.size(); j++)
        {
            double distance = distanceToVertexAsDouble(unSorted.get(j).getMidVertex());
            if(distance > maxDistance)
            {
                maxDistance = distance;
                maxIndex = j;
            }
        }
        sorted[i] = unSorted.get(maxIndex);
        unSorted.remove(maxIndex);
    }

    return sorted;
}

    public void rotateX(double rotX){this.rotX += rotX;}
    public void rotateY(double rotY){this.rotY += rotY;}
    public void rotateZ(double rotZ){this.rotZ += rotZ;}
    public void setRotX(double rotX){this.rotX = rotX;}
    public void setRotY(double rotY){this.rotY = rotY;}
    public void setRotZ(double rotZ){this.rotZ = rotZ;}
    public double getRotX(){return rotX;}
    public double getRotY(){return rotY;}
    public double getRotZ(){return rotZ;}
}
