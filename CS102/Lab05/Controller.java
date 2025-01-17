import java.awt.image.BufferedImage;

public class Controller  {
    
    public BufferedImage canva;
    public SettingsFrame settings;
    public PaintFrame paint;
    public ToolsFrame tool;
    public int tolerance = 1;
    
    

    public static void main(String[] args) {
        Controller c = new Controller();
        c.settings = new SettingsFrame(c);
        
        
        

        
        
        
    }
}
