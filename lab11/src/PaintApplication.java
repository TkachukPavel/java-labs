import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by tkachuk on 29.11.16.
 */
public class PaintApplication extends JFrame{
    private JButton buttonRed;
    private JButton buttonBlue;
    private JButton buttonBlack;

    private JPanel panelButton;
    private JPanel panelDraw;

    private ArrayList<Point2D> points;
    private ArrayList<Color> colors;

    private Color currColor = Color.BLACK;

    private void paintPoints(){
        Graphics2D g2 = (Graphics2D) panelDraw.getGraphics();
        Point2D prevPoint = null;
        for (int i = 0; i < points.size(); i++){
            Point2D point = points.get(i);
            Color color = colors.get(i);
            if (point == null){
                prevPoint = null;
                continue;
            }
            if (prevPoint == null){
                prevPoint = point;
            }

            Line2D line = new Line2D.Double(prevPoint.getX(), prevPoint.getY(), point.getX(), point.getY());
            g2.setColor(color);
            g2.draw(line);
            prevPoint = point;
        }

    }

    public PaintApplication(){
        points = new ArrayList<>();
        colors = new ArrayList<>();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());



        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        buttonBlack = new JButton("Black");
        buttonBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                currColor = Color.BLACK;
            }
        });
        buttonRed = new JButton("Red");
        buttonRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                currColor = Color.RED;
            }
        });
        buttonBlue = new JButton("Blue");
        buttonBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                currColor = Color.BLUE;
            }
        });
        panelButton.add(buttonRed);
        panelButton.add(buttonBlack);
        panelButton.add(buttonBlue);
        add(panelButton, BorderLayout.SOUTH);

        panelDraw = new JPanel();
        panelDraw.setLayout(null);
      //  panelDraw.setSize(getSize());
        add(panelDraw, BorderLayout.CENTER);
        panelDraw.setPreferredSize(this.getSize());
        JScrollPane scrollPane = new JScrollPane(panelDraw);
        scrollPane.setVisible(true);
        add(scrollPane, BorderLayout.CENTER);


        panelDraw.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                points.add(mouseEvent.getPoint());
                colors.add(currColor);
                paintPoints();
            }
        });

        panelDraw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                points.add(null);
                colors.add(null);
            }
        });
        panelDraw.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                paintPoints();
            }

            @Override
            public void componentMoved(ComponentEvent componentEvent) {
                super.componentMoved(componentEvent);
                paintPoints();
            }
        });


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent componentEvent) {
                super.componentMoved(componentEvent);
                paintPoints();
            }

            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                paintPoints();
            }
        });
    }
    public static void main(String[] args){
        PaintApplication paintApp = new PaintApplication();
        paintApp.setTitle("Paint Application");
        paintApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paintApp.setVisible(true);
    }
}
