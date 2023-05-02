public class Vertex 
{  
    public double[] vertex = new double[3];
    public Vertex(double x, double y, double z) 
    { 
        vertex[0] = x;
        vertex[1] = y;
        vertex[2] = z;
    }

    public double getX(){return vertex[0];}
    public double getY(){return vertex[1];}
    public double getZ(){return vertex[2];}

    public void setX(double x){vertex[0] = x;}
    public void setY(double y){vertex[1] = y;}
    public void setZ(double z){vertex[2] = z;}

    public void translateX(double dx){vertex[0] += dx;}
    public void translateY(double dy){vertex[1] += dy;}
    public void translateZ(double dz){vertex[2] += dz;}

    public void translate(double dx, double dy, double dz)
    {
        vertex[0] += dx;
        vertex[1] += dy;
        vertex[2] += dz;
    }

    public Vertex distanceToVertexAsVertex(Vertex vertex)
    {
        return new Vertex(vertex.getX() - getX(), vertex.getY() - getY(), vertex.getZ() - getZ());
    }

    public double distanceToVertexAsDouble(Vertex vertex)
    {
        return Math.sqrt(Math.pow(vertex.getX() - getX(), 2) + Math.pow(vertex.getY() - getY(), 2) + Math.pow(vertex.getZ() - getZ(), 2));
    }

    public double[] getVertex(){return vertex;}
}
