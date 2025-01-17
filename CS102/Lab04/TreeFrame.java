package Lab04;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
public class TreeFrame extends JFrame{
    TreePanel t = new TreePanel();
    public TreeFrame(){
        super("SeasonDisplay");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        panel.add(new ButtonsPanel(t),BorderLayout.SOUTH);
        panel.add(t,BorderLayout.CENTER);
        add(panel);
        setSize(296, 306+54);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

    }
    

}
