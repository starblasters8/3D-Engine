public class Vertex 
{  
    public int[] vertex = new int[3];
    public Vertex(int x, int y, int z) 
    { 
        vertex[0] = x;
        vertex[1] = y;
        vertex[2] = z;
    }

    public int getX(){return vertex[0];}
    public int getY(){return vertex[1];}
    public int getZ(){return vertex[2];}

    public void setX(int x){vertex[0] = x;}
    public void setY(int y){vertex[1] = y;}
    public void setZ(int z){vertex[2] = z;}

    public void translateX(int dx){vertex[0] += dx;}
    public void translateY(int dy){vertex[1] += dy;}
    public void translateZ(int dz){vertex[2] += dz;}

    public void translate(int dx, int dy, int dz)
    {
        vertex[0] += dx;
        vertex[1] += dy;
        vertex[2] += dz;
    }

    public int[] getVertex(){return vertex;}
}
