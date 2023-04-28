import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicsPanel extends JPanel
{
    private Cube cube = new Cube(200, 200, 0);

    public GraphicsPanel(int w, int h)
    {
        this.setPreferredSize(new Dimension(w,h));
        setBackground(Color.BLACK);
        cube.uniformScale(2);

        Timer timer = new Timer(250, new ActionListener() {public void actionPerformed(ActionEvent e) {
                //cube.rotateX(10);
                //cube.rotateY(10);
                //cube.rotateZ(10);
                //cube.uniformScale(1.1);
                repaint();
            }});

        timer.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Draw a cube with each triangle a different color
        for (Triangle triangle : cube.getTriangles()) 
        {
            g.setColor(triangle.getColor());
            g.fillPolygon(triangle.getXCoords(), triangle.getYCoords(), 3);
        }
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