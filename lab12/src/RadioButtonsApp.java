import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by tkachukp on 13.12.16.
 */
class RadioButtonsApp extends JPanel {
    RadioButtonsApp() {
        try {
            Image img = ImageIO.read(new File("icons.jpg"));
            int height = img.getHeight(null);
            int width = img.getWidth(null);
            BufferedImage bImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bImg.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
            ImageIcon iconSelected = new ImageIcon(bImg.getSubimage(0, 0, width / 2, height / 2));
            ImageIcon iconDisabled = new ImageIcon(bImg.getSubimage(width / 2 , 0, width / 2, height / 2));
            ImageIcon iconPressed = new ImageIcon(bImg.getSubimage(0, height / 2, width / 2, height / 2));
            ImageIcon iconPointed = new ImageIcon(bImg.getSubimage(width / 2, height / 2, width / 2, height / 2));
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            setSize(screenSize.width, screenSize.height / 2);
            setLayout(new FlowLayout());
            ButtonGroup group = new ButtonGroup();
            JRadioButton button1 = new JRadioButton("A", false);
            JRadioButton button2 = new JRadioButton("B", false);
            JRadioButton button3 = new JRadioButton("C", false);


            group.add(button1);
            group.add(button2);
            group.add(button3);

            button1.setIcon(iconDisabled);
            button1.setSelectedIcon(iconSelected);
            button1.setPressedIcon(iconPressed);
            button1.setRolloverIcon(iconPointed);
            button2.setIcon(iconDisabled);
            button2.setSelectedIcon(iconSelected);
            button2.setPressedIcon(iconPressed);
            button2.setRolloverIcon(iconPointed);
            button3.setIcon(iconDisabled);
            button3.setSelectedIcon(iconSelected);
            button3.setPressedIcon(iconPressed);
            button3.setRolloverIcon(iconPointed);
            add(button1);
            add(button2);
            add(button3);
            setVisible(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
