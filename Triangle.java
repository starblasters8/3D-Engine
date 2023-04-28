import java.awt.Color;
public class Triangle 
{
    private Vertex vertex1, vertex2, vertex3;
    private Vertex[] vertices;
    private Color color;

    public Triangle(Vertex vertex1, Vertex vertex2, Vertex vertex3)
    {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        vertices = new Vertex[]{vertex1, vertex2, vertex3};
        randColor();
    }

    public Color getColor(){return color;}
    public void setColor(Color color){this.color = color;}
    public void randColor(){color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));}

    public Vertex getVertex1(){return vertex1;}
    public Vertex getVertex2(){return vertex2;}
    public Vertex getVertex3(){return vertex3;}

    public void setVertex1(Vertex vertex1){this.vertex1 = vertex1;}
    public void setVertex2(Vertex vertex2){this.vertex2 = vertex2;}
    public void setVertex3(Vertex vertex3){this.vertex3 = vertex3;}

    public int[] getXCoords(){return new int[]{vertex1.getX(), vertex2.getX(), vertex3.getX()};}
    public int[] getYCoords(){return new int[]{vertex1.getY(), vertex2.getY(), vertex3.getY()};}
    public int[] getZCoords(){return new int[]{vertex1.getZ(), vertex2.getZ(), vertex3.getZ()};}

    public Vertex getMidVertex()
    {
        int x = (vertex1.getX() + vertex2.getX() + vertex3.getX()) / 3;
        int y = (vertex1.getY() + vertex2.getY() + vertex3.getY()) / 3;
        int z = (vertex1.getZ() + vertex2.getZ() + vertex3.getZ()) / 3;
        return new Vertex(x, y, z);
    }

    public void setVertices(Vertex[] vertices)
    {
        if(vertices.length == 3)
        {
            this.vertices = vertices;
            vertex1 = vertices[0];
            vertex2 = vertices[1];
            vertex3 = vertices[2];
        }
        else
            throw new IllegalArgumentException("Vertices array length must be equal to 3");
    }
    public Vertex[] getVertices(){return vertices;}

    public void translateX(int dx)
    {
        for(Vertex vertex : vertices)
            vertex.translateX(dx);
    }
    public void translateY(int dy)
    {
        for(Vertex vertex : vertices)
            vertex.translateY(dy);
    }
    public void translateZ(int dz)
    {
        for(Vertex vertex : vertices)
            vertex.translateZ(dz);
    }
    public void translate(int dx, int dy, int dz)
    {
        for(Vertex vertex : vertices)
            vertex.translate(dx, dy, dz);
    }
}
