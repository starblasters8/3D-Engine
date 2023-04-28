public class Cube extends Object3D
{
    private Triangle[] triangles = new Triangle[12];
    public Cube(int x, int y, int z)
    {
        super();

        // (x, y, z) x=right/left y=up/down z=forward/backward

        // Front
        triangles[0] = new Triangle(new Vertex(0,0,0), new Vertex(100,0,0), new Vertex(100,0,100));
        triangles[1] = new Triangle(new Vertex(0,0,0), new Vertex(100,0,100), new Vertex(0,0,100));

        // Right
        triangles[2] = new Triangle(new Vertex(100,0,0), new Vertex(100,0,100), new Vertex(100,100,100));
        triangles[3] = new Triangle(new Vertex(100,0,0), new Vertex(100,100,100), new Vertex(100,100,0));

        // Top
        triangles[4] = new Triangle(new Vertex(0,0,100), new Vertex(100,0,100), new Vertex(100,100,100));
        triangles[5] = new Triangle(new Vertex(0,0,100), new Vertex(100,100,100), new Vertex(0,100,100));

        // Left
        triangles[6] = new Triangle(new Vertex(0,0,0), new Vertex(0,0,100), new Vertex(0,100,100));
        triangles[7] = new Triangle(new Vertex(0,0,0), new Vertex(0,100,100), new Vertex(0,100,0));

        // Bottom
        triangles[8] = new Triangle(new Vertex(0,0,0), new Vertex(100,0,0), new Vertex(100,100,0));
        triangles[9] = new Triangle(new Vertex(0,0,0), new Vertex(100,100,0), new Vertex(0,100,0));

        // Back
        triangles[10] = new Triangle(new Vertex(0,100,0), new Vertex(100,100,0), new Vertex(100,100,100));
        triangles[11] = new Triangle(new Vertex(0,100,0), new Vertex(100,100,100), new Vertex(0,100,100));

        setTriangles(triangles);
        translate(x, y, z);
        setAnchorToCenter();
    }
}
