public class Camera 
{
    private int x, y, z;
    private double rotX, rotY, rotZ;

    public Camera(int x, int y, int z)
    {
        this.x=x;
        this.y=y;
        this.z=z;

        rotX = 0;
        rotY = 0;
        rotZ = 0;
    }

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setZ(int z){this.z = z;}
    public void setRotX(double rotX){this.rotX = rotX;}
    public void setRotY(double rotY){this.rotY = rotY;}
    public void setRotZ(double rotZ){this.rotZ = rotZ;}

    public int getX(){return x;}
    public int getY(){return y;}
    public int getZ(){return z;}
    public double getRotX(){return rotX;}
    public double getRotY(){return rotY;}
    public double getRotZ(){return rotZ;}
}
