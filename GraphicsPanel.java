import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

public class GraphicsPanel extends JPanel
{
    private Camera camera = new Camera(0, 0, 0);
    private HashMap<String, Object3D> objects = new HashMap<String, Object3D>();
    private int maxFPS = 5;

    public GraphicsPanel(int w, int h)
    {
        this.setPreferredSize(new Dimension(w,h));
        setBackground(Color.BLACK);

        objects.put("cube 1", new Cube(200, 200, 500));
        objects.put("sphere 1", new Sphere(200, 200, 500, 25));


        objects.get("cube 1").toggleRender();
        for(Object3D object : objects.values())
            object.uniformScale(2);

        Timer timer = new Timer(1000/maxFPS, new ActionListener() {public void actionPerformed(ActionEvent e) {
                //objects.get("sphere 1").rotateX(5);
                repaint();
            }});

        timer.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        // Draw a cube with each triangle a different color
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        camera.drawAll(g2d, objects);
    }

    // JFrame
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("GraphicsPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GraphicsPanel(800,800));
        frame.pack();
        frame.setVisible(true);
    }
}