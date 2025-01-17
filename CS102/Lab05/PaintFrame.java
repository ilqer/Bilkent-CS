import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

public class PaintFrame extends JFrame implements MouseInputListener{
    
    Controller control;
    int rgb = new Color(0,0,0).getRGB();
    int x ;
    int y ;
    
    public PaintFrame(Controller c){
        super();
        control = c;
        
        
        
        setSize(new Dimension(control.settings.w,control.settings.h));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addMouseListener(this);                   
        addMouseMotionListener(this);                   
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(control.canva,0,0, null);
        
        
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();
        if(control.tool.click == 2){
            for(int i = control.tool.penSize*-1-1; i<control.tool.penSize+1;i++){
                for(int j = control.tool.penSize*-1-1; j<control.tool.penSize+1;j++){
                    control.canva.setRGB(x+j,y+i,rgb);
                }
            
            }
            repaint();
        }
        else if(control.tool.click == 4){
            laserUp(x, y-1,new Color(control.canva.getRGB(x,y)) ,new Color(rgb) );
            laserDown(x, y,new Color(control.canva.getRGB(x,y)) ,new Color(rgb) );
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if(control.tool.click == 2){
            for(int i = control.tool.penSize*-1-1; i<control.tool.penSize+1;i++){
                for(int j = control.tool.penSize*-1-1; j<control.tool.penSize+1;j++){
                    control.canva.setRGB(x+j,y+i,rgb);
                }
            
            }
            repaint();
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();
        if(control.tool.click == 2){
            for(int i = control.tool.penSize*-1-1; i<control.tool.penSize+1;i++){
                for(int j = control.tool.penSize*-1-1; j<control.tool.penSize+1;j++){
                    control.canva.setRGB(x+j,y+i,rgb);
                }
            
            }
            repaint();
        }
        repaint();
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
    }






    public void laserUp(int x,int y, Color cStart,Color cNow){
        if(y>0) {
            Color color = new Color(control.canva.getRGB(x,y));
            int diff = Math.abs(cStart.getRed()-color.getRed())+Math.abs(cStart.getBlue()-color.getBlue())+Math.abs(cStart.getGreen()-color.getGreen())/3;
            
            if(diff<control.tolerance ){
                control.canva.setRGB(x,y,cNow.getRGB());
                y--;
                laserUp(x, y, cStart, cNow);
                
            }
        }
        return;
        
            
             
        }
        
    
    public void laserDown(int x,int y, Color cStart, Color cNow){
        if(y<control.settings.h) {
            Color color = new Color(control.canva.getRGB(x,y));
            
            int diff = Math.abs(cStart.getRed()-color.getRed())+Math.abs(cStart.getBlue()-color.getBlue())+Math.abs(cStart.getGreen()-color.getGreen())/3;
            
            if(diff<control.tolerance){
                control.canva.setRGB(x,y,cNow.getRGB());
                y++;
                laserDown(x, y, cStart, cNow);
                System.out.println(y);
            }
        }
        return;
                 
        }
        
    }

