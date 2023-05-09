import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Camera extends Vertex
{
    private double rotX=0, rotY=0, rotZ=0;

    public Camera(double x, double y, double z)
    {
        super(x, y, z);
    }

    // Draw all objects in the scene
    public void drawAll(Graphics2D g, HashMap<String, Object3D> objectHash, boolean orthographic)
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

        // for(Object3D obj : objects)
        // {
        //     Vertex tempAnchor = obj.getAnchor();
        //     obj.setAnchor(new Vertex(0, 0, 0));
        //     obj.rotateX(rotX);

        //     obj.setAnchor(tempAnchor);
        // }

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
        triangleArray = sortByVert(triangleArray);


        if(orthographic)
        {
            // Draw the triangles from orthographic
            for (Triangle triangle : triangleArray) 
            {
                int[] xCoords = new int[3];
                int[] yCoords = new int[3];
                for(int i = 0; i < triangle.getXCoords().length; i++)
                    xCoords[i] = (int)(triangle.getXCoords()[i]);
                for(int i = 0; i < triangle.getYCoords().length; i++)
                    yCoords[i] = (int)(triangle.getYCoords()[i]);

                g.setColor(triangle.getColor());
                g.fillPolygon(xCoords, yCoords, 3);
            }
        }
        else
        {
            // Draw the triangles from perspective
            drawFromPerspective(g, triangleArray);
        }
    }

    public void drawFromPerspective(Graphics2D g, Triangle[] triangleArray) // Draw the triangles from the perspective of the camera already sorted
    {

    }

    public Triangle[] sortByVert(Triangle[] triangles) // Sort the triangles by the distance to the camera from their individual vertices. This fixes the issue of triangles being drawn in the wrong order when they are close to each other. (Especially during rotation)
    {
        Triangle[] sorted = new Triangle[triangles.length];
        ArrayList<Triangle> unSorted = new ArrayList<Triangle>();

        // Add all triangles to the unsorted arraylist
        for(Triangle triangle : triangles)
            unSorted.add(triangle);

        // Find the furthest triangle and add it to the sorted array
        for(int i = 0; i < sorted.length; i++)
        {
            double maxDistance = 0;
            int maxIndex = 0;
            for(int j = 0; j < unSorted.size(); j++)
            {
                double distance = 0;
                distance += distanceToVertexAsDouble(unSorted.get(j).getVertices()[0]);
                distance += distanceToVertexAsDouble(unSorted.get(j).getVertices()[1]);
                distance += distanceToVertexAsDouble(unSorted.get(j).getVertices()[2]);
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

    public Triangle[] sortByTri(Triangle[] triangles) // Sort the triangles by the distance to the camera from the triangles midpoints. (Old method, not used anymore but may be useful in the future so I'm keeping it, feel free to experiment if you want to!)
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
