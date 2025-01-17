import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImagePanel extends JPanel {
    private String path;
    private Pixel[][] pixels;
    private int iteration;
    private int maxIteration;
    private boolean isSorting;
    private int imageWidth;
    private int imageHeight;
    public BufferedImage image;
    public int parentHeight;
    public int parentWidth;
    public ImagePanel(String path) {
        this.path = path;
        resetImage();
    }
    public void startSorting() {
        isSorting = true;
    }
    public void stopSorting() {
        isSorting = false;
    }
    public void paint(Graphics g) {
        super.paint(g);

        if (iteration < maxIteration && isSorting) {
            for (int i = 0; i < imageWidth; i++) {
                insertTop(sort(extractTop(i)), i);
            }
            for (int i = 1; i < imageHeight; i++) {
                insertLeft(sort(extractLeft(i)), i);
            }
            iteration++;
        }
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                image.setRGB(i,j, new Color(pixels[i][j].RED, pixels[i][j].GREEN, pixels[i][j].BLUE).getRGB());
            }
        }
        double factor = Math.min(
                (((double) parentHeight) / ((double) imageHeight)),
                (((double) parentWidth) / ((double) imageWidth)));
        setSize((int) (factor * imageWidth), (int) (factor * imageHeight));
        g.drawImage(image,0,0, (int) (factor * imageWidth), (int) (factor * imageHeight),null);
        repaint();
    }

    public void resetImage() {
        this.iteration = 0;
        try {
            File file = new File(path);
            image = ImageIO.read(file);
        } catch (IOException ex) {
            System.err.println("Error loading image: " + path);
            System.exit(1);
        }
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        this.maxIteration = Math.max(imageWidth, imageHeight);
        setSize(imageWidth, imageHeight);
        pixels = new Pixel[this.imageWidth][this.imageHeight];
        for (int i = 0; i < this.imageWidth; i++) {
            for (int j = 0; j < this.imageHeight; j++) {
                Color colors = new Color(image.getRGB(i, j));
                pixels[i][j] = new Pixel(colors.getRed(), colors.getGreen(), colors.getBlue());
            }
        }
    }
    public ArrayList<Pixel> extractTop(int count) {
        ArrayList<Pixel> diagonal = new ArrayList<Pixel>();
        int elementCount = Math.min(imageWidth - count, imageHeight);
        for (int i = 0; i < elementCount; i++) {
            diagonal.add(pixels[i + count][i]);
        }
        return diagonal;
    }
    public ArrayList<Pixel> extractLeft(int count) {
        ArrayList<Pixel> diagonal = new ArrayList<Pixel>();
        int elementCount = Math.min(imageWidth, imageHeight - count);
        for (int i = 0; i < elementCount; i++) {
            diagonal.add(pixels[i][i + count]);
        }
        return diagonal;
    }
    public void insertTop(ArrayList<Pixel> elements, int diagonal) {
        int elementCount = elements.size();
        for (int i = 0; i < elementCount; i++) {
            pixels[i + diagonal][i] = elements.get(i);
        }
    }
    public void insertLeft(ArrayList<Pixel> elements, int diagonal) {
        int elementCount = elements.size();
        for (int i = 0; i < elementCount; i++) {
            pixels[i][i +  diagonal] = elements.get(i);
        }
    }
    public ArrayList<Pixel> sort(ArrayList<Pixel> elements) {
        int elementCount = elements.size();
        for (int i = 0; i < Math.min(elementCount - 1, iteration); i++) {
            int maximumIndex = i;
            for (int j = i+1; j < elementCount; j++)
            {
                if (elements.get(j).getBrightness() > elements.get(maximumIndex).getBrightness())
                {
                    maximumIndex = j;
                }
            }
            if (maximumIndex != i) {
                Pixel temp = elements.get(i);
                elements.set(i, elements.get(maximumIndex));
                elements.set(maximumIndex, temp);
            }
        }
        return elements;
    }
}
