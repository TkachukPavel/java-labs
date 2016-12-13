import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tkachukp on 13.12.16.
 */

class BorderLayoutApp extends JPanel {
    public BorderLayoutApp(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLayout(new BorderLayout());

        DefaultListModel model1 = new DefaultListModel();
        DefaultListModel model2 = new DefaultListModel();
        JList list1 = new JList(model1);
        JList list2 = new JList(model2);
        for (Object o :
                new String[]{"Klara", "u", "Karla", "ukrala", "korally"}) {
            model1.addElement(o);
        }
        for (Object o :
                new String[]{"A", "Karl", "u", "Klary", "ukral", "klarnet"}) {
            model2.addElement(o);
        }
        add(list1, BorderLayout.WEST);
        add(list2, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);

        JButton toRight = new JButton(">");
        toRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = list1.getSelectedIndex();
                while (i != -1){
                    model2.addElement(model1.get(i));
                    model1.remove(i);
                    i = list1.getSelectedIndex();
                }
            }
        });
        buttonPanel.add(toRight, BorderLayout.NORTH);

        JButton toLeft = new JButton("<");
        toLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = list2.getSelectedIndex();
                while (i != -1){
                    model1.addElement(model2.get(i));
                    model2.remove(i);
                    i = list2.getSelectedIndex();
                }
            }
        });
        buttonPanel.add(toLeft, BorderLayout.SOUTH);
    }
}
