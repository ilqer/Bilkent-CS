

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;


public class SettingsFrame extends JFrame{
    
    
    

    int w;
    int h;
    
    Controller control;
    public SettingsFrame(Controller c){
        super();
        control = c;
        
        

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        JPanel width = new JPanel();
        JPanel height = new JPanel();
        JButton cont = new JButton("Continue");

        
        
        cont.setSize(getPreferredSize());

        JTextField fieldW = new JTextField(6);
        fieldW.setMinimumSize(getMinimumSize());
        
        JTextField fieldH = new JTextField(6);
        fieldH.setMinimumSize(getMinimumSize());
        
        width.add(new JLabel("width:"));
        width.add(fieldW);

        height.add(new JLabel("height:"));
        height.add(fieldH);

        


        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                w = Integer.parseInt(fieldW.getText());
                h = Integer.parseInt(fieldH.getText());
                if(w<0 || w>1000 || h<0 || h>1000){
                    JOptionPane.showMessageDialog(null, "Error Message!");
                }
                else{
                    control.canva = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    control.canva.getGraphics().setColor(Color.WHITE);
                    control.canva.getGraphics().fillRect(0, 0, control.settings.w, control.settings.w);
                    setVisible(false);     
                    control.paint = new PaintFrame(control);
                    control.tool = new ToolsFrame(control);

                }
                
            }
        });
        
        p.add(width,BorderLayout.NORTH);
        p.add(height,BorderLayout.CENTER);
        p.add(cont,BorderLayout.SOUTH);
        add(p);
        setSize(200, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    
    
}
