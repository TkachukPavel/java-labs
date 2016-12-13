import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by tkachukp on 13.12.16.
 */
class GridLayoutApp extends JPanel {
    public GridLayoutApp(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLayout(new GridLayout(5, 5));
        MouseListener mouseListener = new MouseAdapter() {
            private String buttonName;
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JButton button = (JButton) mouseEvent.getSource();
                buttonName = button.getText();
                button.setText("Clicked");
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                JButton button = (JButton) mouseEvent.getSource();
                button.setText(buttonName);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                JButton button = (JButton) mouseEvent.getSource();
                button.setBackground(Color.MAGENTA);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                JButton button = (JButton) mouseEvent.getSource();
                button.setBackground(Color.ORANGE);
            }
        };

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                JButton butt = new JButton(String.valueOf(5 * i + j + 1));
                butt.setBackground(Color.ORANGE);
                butt.addMouseListener(mouseListener);
                add(butt);
            }
        }
    }
}
