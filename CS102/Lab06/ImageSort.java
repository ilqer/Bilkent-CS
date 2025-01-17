import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;


public class ImageSort extends JFrame implements KeyListener {
    int curr = 0;
    ArrayList<ImagePanel> images = new ArrayList<ImagePanel>();

    public ImageSort(ArrayList<String> imgs) {
        super("AllMıghtyImageSorter");
        for (String i : imgs) {
            ImagePanel imgPnl = new ImagePanel(i);
            imgPnl.setVisible(false);
            add(imgPnl);
            images.add(imgPnl);
        }
        changePicture(0);
        setSize(images.get(0).getWidth(), images.get(0).getHeight());
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        addKeyListener(this);
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent evt) {
                for (ImagePanel i : images) {
                    i.parentHeight = getHeight();
                    i.parentWidth = getWidth();
                }
            }
        });
    }

    public void changePicture(int id)
    {
        for (ImagePanel i : images) {
            i.setVisible(false);
            i.stopSorting();
        }
        images.get(id).setVisible(true);
        images.get(id).startSorting();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            images.get(curr).resetImage();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            curr--;
            if (curr == -1)
            {
                curr = images.size() - 1;
            }
            changePicture(curr);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            curr++;
            curr = curr % images.size();
            changePicture(curr);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }



    public static void main(String[] args) {
        ArrayList<String> imgs = new ArrayList<String>();
        imgs.add("gece.jpg");
        imgs.add("manzara.jpg");
        imgs.add("picasso.jpg");
        new ImageSort(imgs);

    }
}
