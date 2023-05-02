public class Object3D 
{
    private Triangle[] triangles;
    private double scaleX=1, scaleY=1, scaleZ=1;
    private double tempScaleX=1, tempScaleY=1, tempScaleZ=1;
    private double rotX=0, rotY=0, rotZ=0;
    private Vertex anchor = new Vertex(0, 0, 0);
    private boolean render = true;

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

    public void setRender(boolean render){this.render = render;}
    public boolean getRender(){return render;}
    public void toggleRender(){render = !render;}
    

    public Vertex distanceToObjectAsVertex(Object3D object)
    {
        Vertex mid1 = getAnchor();
        Vertex mid2 = object.getAnchor();
        return new Vertex(mid2.getX() - mid1.getX(), mid2.getY() - mid1.getY(), mid2.getZ() - mid1.getZ());
    }

    public double distanceToObjectAsDouble(Object3D object)
    {
        Vertex distance = distanceToObjectAsVertex(object);
        return Math.sqrt(distance.getX()*distance.getX() + distance.getY()*distance.getY() + distance.getZ()*distance.getZ());
    }

    public Vertex distanceBetweenTwoVerticesAsVertex(Vertex v1, Vertex v2)
    {return new Vertex(v2.getX() - v1.getX(), v2.getY() - v1.getY(), v2.getZ() - v1.getZ());}

    public double distanceBetweenTwoVerticesAsDouble(Vertex v1, Vertex v2)
    {
        Vertex distance = distanceBetweenTwoVerticesAsVertex(v1, v2);
        return Math.sqrt(distance.getX()*distance.getX() + distance.getY()*distance.getY() + distance.getZ()*distance.getZ());
    }

    // Translation
    public void translateX(double dx)
    {
        for(Triangle triangle : triangles)
            triangle.translateX(dx);
    }
    public void translateY(double dy)
    {
        for(Triangle triangle : triangles)
            triangle.translateY(dy);
    }
    public void translateZ(double dz)
    {
        for(Triangle triangle : triangles)
            triangle.translateZ(dz);
    }
    public void translate(double dx, double dy, double dz)
    {
        for(Triangle triangle : triangles)
            triangle.translate(dx, dy, dz);
    }


    // Anchor point
    public Vertex getAnchor(){return anchor;}
    public void setAnchor(Vertex anchor){this.anchor = anchor;}
    public void translateAnchor(double dx, double dy, double dz){anchor.translate(dx, dy, dz);}
    public void translateAnchorX(double dx){anchor.translateX(dx);}
    public void translateAnchorY(double dy){anchor.translateY(dy);}
    public void translateAnchorZ(double dz){anchor.translateZ(dz);}

        // Set anchor point to exact middle of 3D object
    public void setAnchorToCenter()
    {
        double minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        double minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        double minZ = Integer.MAX_VALUE, maxZ = Integer.MIN_VALUE;

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
    public void scale(double scaleX, double scaleY, double scaleZ)
    {
        this.scaleX += scaleX;
        this.scaleY += scaleY;
        this.scaleZ += scaleZ;

        this.tempScaleX = scaleX;
        this.tempScaleY = scaleY;
        this.tempScaleZ = scaleZ;

        doScale();
    }
    public void uniformScale(double scaleIn){scale(scaleIn, scaleIn, scaleIn);}
    public void setScaleX(double scaleX){this.scaleX = scaleX; doScale();}
    public void setScaleY(double scaleY){this.scaleY = scaleY; doScale();}
    public void setScaleZ(double scaleZ){this.scaleZ = scaleZ; doScale();}

    private void doScale()
    {
        for(Triangle triangle : triangles)
        {
            for(Vertex vertex : triangle.getVertices())
            {
                vertex.setX(((vertex.getX() - anchor.getX()) * (tempScaleX+1) + anchor.getX()));
                vertex.setY(((vertex.getY() - anchor.getY()) * (tempScaleY+1) + anchor.getY()));
                vertex.setZ(((vertex.getZ() - anchor.getZ()) * (tempScaleZ+1) + anchor.getZ()));
            }
        }
    }

    public double getScaleX(){return scaleX;}
    public double getScaleY(){return scaleY;}
    public double getScaleZ(){return scaleZ;}

    // Rotation
    public void rotate(double rotX, double rotY, double rotZ)
    {
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        doRot();
    }
    public void rotateX(double rotX){this.rotX = rotX; doRot();}
    public void rotateY(double rotY){this.rotY = rotY; doRot();}
    public void rotateZ(double rotZ){this.rotZ = rotZ; doRot();}

    private void doRot()
    {
        rotX%=360; rotY%=360; rotZ%=360;

        double rotXRad = Math.toRadians(rotX);
        double rotYRad = Math.toRadians(rotY);
        double rotZRad = Math.toRadians(rotZ);

        double sinX = Math.sin(rotXRad);
        double cosX = Math.cos(rotXRad);
        double sinY = Math.sin(rotYRad);
        double cosY = Math.cos(rotYRad);
        double sinZ = Math.sin(rotZRad);
        double cosZ = Math.cos(rotZRad);

        for(Triangle triangle : triangles)
        {
            for(Vertex vertex : triangle.getVertices())
            {
                double x = vertex.getX() - anchor.getX();
                double y = vertex.getY() - anchor.getY();
                double z = vertex.getZ() - anchor.getZ();

                double newY = y * cosX - z * sinX;
                double newZ = y * sinX + z * cosX;

                double newX = x * cosY + newZ * sinY;
                newZ = -x * sinY + newZ * cosY;

                double tempY = newY;
                newY = newY * cosZ - newX * sinZ;
                newX = tempY * sinZ + newX * cosZ;

                // Rotate around X-axis
                vertex.setX(newX + anchor.getX());
                // Rotate around Y-axis
                vertex.setY(newY + anchor.getY());
                // Rotate around Z-axis
                vertex.setZ(newZ + anchor.getZ());
            }
        }
    }

    public double getRotX(){return rotX;}
    public double getRotY(){return rotY;}
    public double getRotZ(){return rotZ;}
    public double getTriNum(){return triangles.length;}
}
