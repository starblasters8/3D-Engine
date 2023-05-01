public class Camera extends Vertex
{
    private double rotX=0, rotY=0, rotZ=0;

    public Camera(int x, int y, int z)
    {
        super(x, y, z);
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
