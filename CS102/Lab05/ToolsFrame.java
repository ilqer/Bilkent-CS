import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToolsFrame extends JFrame{
    int click = 2;
    Controller control;
    int penSize = 0;
    Color choCol;
    public ToolsFrame(Controller c){
        super();
        control = c;
        JPanel p = new JPanel();

        
        p.setLayout(new GridLayout(6,1));

        JButton clear = new JButton("Clear");
        JButton pen = new JButton("Pen");
        JButton penS = new JButton("Pen Size");
        JButton laser = new JButton("Laser");
        JButton color = new JButton("Color");
        JButton tolerance = new JButton("Tolerance");


        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click = 1;
                control.canva = new BufferedImage(control.settings.w, control.settings.h, BufferedImage.TYPE_INT_RGB);
                control.canva.getGraphics().setColor(Color.WHITE);
                control.canva.getGraphics().fillRect(0, 0, control.settings.w, control.settings.w);
                control.paint.repaint();
            }
        }); 

        pen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click = 2;
            }
        });

        penS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click = 3;
                String check = JOptionPane.showInputDialog("Enter pen size:","");
                if(isNumeric(check)){
                    penSize = Integer.parseInt(check);
                }
                
        }


        });

        laser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click = 4;
            }
        });

        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click = 5;
                choCol = JColorChooser.showDialog(null, "Pick a Color!", Color.WHITE);
                control.paint.rgb = choCol.getRGB();
            }
        });

        tolerance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click = 6;
                String check = JOptionPane.showInputDialog("Enter tolerance level(0-255):","");
                if(isNumeric(check)){
                    control.tolerance = Integer.parseInt(check);
                }
            }
        });

        p.add(clear);
        p.add(pen);
        p.add(penS);
        p.add(laser);
        p.add(color);
        p.add(tolerance);
        add(p);
        setSize(100, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        


    }
    public int getClick(){
        return click;
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
