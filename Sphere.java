import java.util.ArrayList;

public class Sphere extends Object3D
{
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();
    private int xMain, yMain, zMain;
    private double radius=100;
    private int xSlice, ySlice;

    public Sphere(int xMain, int yMain, int zMain, int xSlice, int ySlice)
    {
        super();        
        //Generate the triangle faces of a sphere
        this.xMain=xMain;
        this.yMain=yMain;
        this.zMain=zMain;
        this.xSlice=xSlice;
        this.ySlice=ySlice;

        genTris();
    }


    public void genTris()
    {
        triangles.clear();

        //Generate the triangle faces of a sphere
        double angleAroundY = 360.0 / ySlice;
        double angleAroundX = 180.0 / xSlice;

        for(int i = 0; i < xSlice; i++)
        {
            for(int j = 0; j < ySlice; j++)
            {
                double x1 = radius * Math.sin(Math.toRadians(angleAroundX * i)) * Math.cos(Math.toRadians(angleAroundY * j));
                double y1 = radius * Math.sin(Math.toRadians(angleAroundX * i)) * Math.sin(Math.toRadians(angleAroundY * j));
                double z1 = radius * Math.cos(Math.toRadians(angleAroundX * i));

                double x2 = radius * Math.sin(Math.toRadians(angleAroundX * (i + 1))) * Math.cos(Math.toRadians(angleAroundY * j));
                double y2 = radius * Math.sin(Math.toRadians(angleAroundX * (i + 1))) * Math.sin(Math.toRadians(angleAroundY * j));
                double z2 = radius * Math.cos(Math.toRadians(angleAroundX * (i + 1)));

                double x3 = radius * Math.sin(Math.toRadians(angleAroundX * (i + 1))) * Math.cos(Math.toRadians(angleAroundY * (j + 1)));
                double y3 = radius * Math.sin(Math.toRadians(angleAroundX * (i + 1))) * Math.sin(Math.toRadians(angleAroundY * (j + 1)));
                double z3 = radius * Math.cos(Math.toRadians(angleAroundX * (i + 1)));

                double x4 = radius * Math.sin(Math.toRadians(angleAroundX * i)) * Math.cos(Math.toRadians(angleAroundY * (j + 1)));
                double y4 = radius * Math.sin(Math.toRadians(angleAroundX * i)) * Math.sin(Math.toRadians(angleAroundY * (j + 1)));
                double z4 = radius * Math.cos(Math.toRadians(angleAroundX * i));

                triangles.add(new Triangle(new Vertex(x1, y1, z1), new Vertex(x2, y2, z2), new Vertex(x3, y3, z3)));
                triangles.add(new Triangle(new Vertex(x1, y1, z1), new Vertex(x3, y3, z3), new Vertex(x4, y4, z4)));
            }
        }


        Triangle[] triArray = new Triangle[triangles.size()];
        for(int i = 0; i < triangles.size(); i++)
            triArray[i] = triangles.get(i);

        setTriangles(triArray);
        translate(xMain, yMain, zMain);
        setAnchorToCenter();
    }

    public void setRadius(double radius)
    {
        this.radius=radius;
        genTris();
    }
    public double getRadius(){return radius;}

    public void setxSlice(int xSlice)
    {
        this.xSlice=xSlice;
        genTris();
    }
    public int getxSlice(){return xSlice;}

    public void setySlice(int ySlice)
    {
        this.ySlice=ySlice;
        genTris();
    }
    public int getySlice(){return ySlice;}
}
