import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class LayoutApps extends JFrame {

    public LayoutApps() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width, screenSize.height / 2);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setSize(screenSize.width, screenSize.height / 2);
        panel.setVisible(true);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuLayout = new JMenu("Layout");
        JMenuItem borderLayoutItem = new JMenuItem("Border layout");
        borderLayoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (panel.getComponents().length != 0)
                    panel.remove(0);
                panel.add(new BorderLayoutApp());
                panel.repaint();
                panel.revalidate();
            }
        });
        JMenuItem gridLayoutItem = new JMenuItem("Grid layout");
        gridLayoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (panel.getComponents().length != 0)
                    panel.remove(0);
                panel.add(new GridLayoutApp());
                panel.repaint();
                panel.revalidate();
            }
        });
        JMenuItem radioButtonsLayout = new JMenuItem("Radio buttons");
        radioButtonsLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (panel.getComponents().length != 0)
                    panel.remove(0);
                panel.add(new RadioButtonsApp());
                panel.repaint();
                panel.revalidate();
            }
        });
        menuLayout.add(borderLayoutItem);
        menuLayout.add(gridLayoutItem);
        menuLayout.add(radioButtonsLayout);
        menuBar.add(menuLayout);
        add(menuBar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        LayoutApps app = new LayoutApps();
        app.setTitle("Layout apps");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
