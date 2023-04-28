import java.awt.*;
public class Object3D 
{
    private Triangle[] triangles;
    private double scaleX=1, scaleY=1, scaleZ=1;
    private double rotX=0, rotY=0, rotZ=0;
    private Vertex anchor = new Vertex(0, 0, 0);

    public Object3D(Triangle[] triangles){this.triangles = triangles;}
    public Object3D(){}

    public void setTriangle(int index, Triangle triangle)
    {
        if(index >= 0 && index < triangles.length)
            triangles[index] = triangle;
        else
            throw new IllegalArgumentException("Index out of bounds");
    }
    public void setTriangles(Triangle[] triangles){this.triangles = triangles;}

    public Triangle getTriangle(int index)
    {
        if(index >= 0 && index < triangles.length)
            return triangles[index];
        else
            throw new IllegalArgumentException("Index out of bounds");
    }
    public Triangle[] getTriangles(){return triangles;}

    public void draw(Graphics g, Camera c) // Draw the Object3D in order from farthest from camera to closest to camera.
    {
        for (Triangle triangle : triangles) 
        {
            g.setColor(triangle.getColor());
            g.fillPolygon(triangle.getXCoords(), triangle.getYCoords(), 3);
        }
    }

    // Translation
    public void translateX(int dx)
    {
        for(Triangle triangle : triangles)
            triangle.translateX(dx);
    }
    public void translateY(int dy)
    {
        for(Triangle triangle : triangles)
            triangle.translateY(dy);
    }
    public void translateZ(int dz)
    {
        for(Triangle triangle : triangles)
            triangle.translateZ(dz);
    }
    public void translate(int dx, int dy, int dz)
    {
        for(Triangle triangle : triangles)
            triangle.translate(dx, dy, dz);
    }


    // Anchor point
    public Vertex getAnchor(){return anchor;}
    public void setAnchor(Vertex anchor){this.anchor = anchor;}
    public void translateAnchor(int dx, int dy, int dz){anchor.translate(dx, dy, dz);}
    public void translateAnchorX(int dx){anchor.translateX(dx);}
    public void translateAnchorY(int dy){anchor.translateY(dy);}
    public void translateAnchorZ(int dz){anchor.translateZ(dz);}

        // Set anchor point to exact middle of 3D object
    public void setAnchorToCenter()
    {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE, maxZ = Integer.MIN_VALUE;

        for(Triangle triangle : triangles)
        {
            for(Vertex vertex : triangle.getVertices())
            {
                if(vertex.getX() < minX) minX = vertex.getX();
                if(vertex.getX() > maxX) maxX = vertex.getX();
                if(vertex.getY() < minY) minY = vertex.getY();
                if(vertex.getY() > maxY) maxY = vertex.getY();
                if(vertex.getZ() < minZ) minZ = vertex.getZ();
                if(vertex.getZ() > maxZ) maxZ = vertex.getZ();
            }
        }

        anchor.setX((minX + maxX) / 2);
        anchor.setY((minY + maxY) / 2);
        anchor.setZ((minZ + maxZ) / 2);
    }

    // Scaling
    public void setScale(double scaleX, double scaleY, double scaleZ)
    {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        scale();
    }
    public void uniformScale(double scale){setScale(scale, scale, scale);}
    public void setScaleX(double scaleX){this.scaleX = scaleX; scale();}
    public void setScaleY(double scaleY){this.scaleY = scaleY; scale();}
    public void setScaleZ(double scaleZ){this.scaleZ = scaleZ; scale();}

    private void scale()
    {
        for(Triangle triangle : triangles)
        {
            for(Vertex vertex : triangle.getVertices())
            {
                vertex.setX((int)((vertex.getX() - anchor.getX()) * scaleX + anchor.getX()));
                vertex.setY((int)((vertex.getY() - anchor.getY()) * scaleY + anchor.getY()));
                vertex.setZ((int)((vertex.getZ() - anchor.getZ()) * scaleZ + anchor.getZ()));
            }
        }
    }

    public double getScaleX(){return scaleX;}
    public double getScaleY(){return scaleY;}
    public double getScaleZ(){return scaleZ;}

    // Rotation
    public void setRotation(double rotX, double rotY, double rotZ)
    {
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        rotate();
    }
    public void rotateX(double rotX){this.rotX += rotX; rotate();}
    public void rotateY(double rotY){this.rotY += rotY; rotate();}
    public void rotateZ(double rotZ){this.rotZ += rotZ; rotate();}

    private void rotate()
    {
        double rotXRad = Math.toRadians(rotX);
        double rotYRad = Math.toRadians(rotY);
        double rotZRad = Math.toRadians(rotZ);

        double sinX = Math.sin(Math.toRadians(rotXRad));
        double cosX = Math.cos(Math.toRadians(rotXRad));
        double sinY = Math.sin(Math.toRadians(rotYRad));
        double cosY = Math.cos(Math.toRadians(rotYRad));
        double sinZ = Math.sin(Math.toRadians(rotZRad));
        double cosZ = Math.cos(Math.toRadians(rotZRad));

        for(Triangle triangle : triangles)
        {
            for(Vertex vertex : triangle.getVertices())
            {
                double x = vertex.getX() - anchor.getX();
                double y = vertex.getY() - anchor.getY();
                double z = vertex.getZ() - anchor.getZ();

                // Rotate around X-axis
                double newY = y * cosX - z * sinX;
                double newZ = y * sinX + z * cosX;

                // Rotate around Y-axis
                double newX = x * cosY + newZ * sinY;
                newZ = -x * sinY + newZ * cosY;

                // Rotate around Z-axis
                newY = newY * cosZ - newX * sinZ;
                newX = newY * sinZ + newX * cosZ;

                vertex.setX((int)(newX + anchor.getX()));
                vertex.setY((int)(newY + anchor.getY()));
                vertex.setZ((int)(newZ + anchor.getZ()));
            }
        }
    }

    public int getRotX(){return (int)rotX;}
    public int getRotY(){return (int)rotY;}
    public int getRotZ(){return (int)rotZ;}
}
