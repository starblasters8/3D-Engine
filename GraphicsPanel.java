import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GraphicsPanel extends JPanel
{
    private Camera camera = new Camera(0, 0, 0);
    private Cube cube = new Cube(200, 200, 500);
    private Sphere sphere = new Sphere(200, 200, 500, 25);

    public GraphicsPanel(int w, int h)
    {
        this.setPreferredSize(new Dimension(w,h));
        setBackground(Color.BLACK);
        cube.uniformScale(2);
        sphere.uniformScale(2);

        Timer timer = new Timer(100, new ActionListener() {public void actionPerformed(ActionEvent e) {
                //cube.rotateX(10);
                //cube.rotateY(10);
                //cube.rotateZ(1);
                //cube.uniformScale(1.01);
                sphere.rotateX(10);
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
        //cube.draw(g2d, camera);
        sphere.draw(g2d, camera);
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