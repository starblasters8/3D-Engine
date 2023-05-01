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

    public void drawAll(Graphics2D g, HashMap<String, Object3D> objectHash)
    {
        Object3D[] objects = new Object3D[objectHash.size()];
        int i = 0; int tris = 0;
        for(String key : objectHash.keySet())
        {objects[i] = objectHash.get(key);i++;tris+=objectHash.get(key).getTriNum();}

        Triangle[] triangleArray = new Triangle[tris];
        
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
                double distance = unSorted.get(j).getMidVertex().distanceToVertexAsDouble(this);
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
