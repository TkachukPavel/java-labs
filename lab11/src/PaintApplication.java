import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tkachuk on 29.11.16.
 */
public class PaintApplication extends JFrame{
    private JButton buttonRed;
    private JButton buttonBlue;
    private JButton buttonGreen;

    private BufferedImage
            buffer;

    private JMenuBar menuBar;

    private JPanel panelButton;
    private JPanel panelDraw;

    private ArrayList<Point2D> points;
    private ArrayList<Color> colors;

    private Color currColor = Color.GREEN;

    public PaintApplication(){
        points = new ArrayList<>();
        colors = new ArrayList<>();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JFileChooser fileopen = new JFileChooser();
                    int ret = fileopen.showDialog(null, "Open file");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file= fileopen.getSelectedFile();
                        Image img = ImageIO.read(file);
                        setSize(new Dimension(getWidth(), getHeight()));
                        buffer.getGraphics().drawImage(img, 0, 0, null);
                        repaint();
                    }
                }
                catch(IOException e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JFileChooser fileopen = new JFileChooser();
                    int ret = fileopen.showDialog(null, "Save file");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file= fileopen.getSelectedFile();
                        String filePath = file.getPath();
                        ImageIO.write(buffer, "png", new File(filePath + ".png"));
                    }
                }
                catch(IOException arg)
                {
                    JOptionPane.showMessageDialog(null, arg.toString());
                }
            }
        });
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        setJMenuBar(menuBar);

        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        buttonGreen = new JButton("Green");
        buttonGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                currColor = Color.GREEN;
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
        panelButton.add(buttonGreen);
        panelButton.add(buttonBlue);
        add(panelButton, BorderLayout.SOUTH);

        buffer = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
        buffer.setRGB(255, 255, 255);
        panelDraw = (new JPanel(){
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g2 = (Graphics2D) buffer.getGraphics();
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
                graphics.drawImage(buffer, 0, 0, null);
            }
        });
        panelDraw.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                points.add(mouseEvent.getPoint());
                colors.add(currColor);
                repaint();//paintPoints();
            }
        });

        panelDraw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                points.add(null);
                colors.add(null);
            }
        });
        panelDraw.setLayout(null);
        add(panelDraw, BorderLayout.CENTER);
        panelDraw.setPreferredSize(screenSize);
        JScrollPane scrollPane = new JScrollPane(panelDraw);
        scrollPane.setVisible(true);
        add(scrollPane, BorderLayout.CENTER);
    }
    public static void main(String[] args){
        PaintApplication paintApp = new PaintApplication();
        paintApp.setTitle("Paint Application");
        paintApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paintApp.setVisible(true);
    }
}
