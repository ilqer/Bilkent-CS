package Lab04;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {

    Button spring = new Button("Spring");
    Button summer = new Button("Summer");
    Button fall = new Button("Fall");
    Button winter = new Button("Winter");

    public ButtonsPanel(TreePanel panel) {
        spring.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == spring) {
                    panel.setSeason(0);
                }
            }
        });
        summer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == summer) {
                    panel.setSeason(1);
                }
            }
        });
        fall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == fall) {
                    panel.setSeason(2);
                }
            }
        });
        winter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == winter) {
                    panel.setSeason(3);
                }
            }
        });
        add(spring);
        add(summer);
        add(fall);
        add(winter);
    }
}
