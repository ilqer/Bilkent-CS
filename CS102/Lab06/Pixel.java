public class Pixel {
    public int RED;
    public int GREEN;
    public int BLUE;
    public Pixel(int r, int g, int b) {
        this.RED = r;
        this.GREEN = g;
        this.BLUE = b;
    }
    public int getBrightness() {
        return 2126 * RED + 7152 * GREEN + 722 * BLUE;
    }
}
