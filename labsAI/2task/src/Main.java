import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    public int distance
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO
                    .read(new File("C:\\Users\\vertoleha\\IdeaProjects\\labsAI\\channels4_profile.jpg"));
            int wight = image.getWidth();
            int height = image.getHeight();
            Pixel[] pixels =  new Pixel[wight * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < wight; x++) {
                    pixels[y * wight + x] = new Pixel(image.getRGB(x, y));
                }
            }
            int k = 8;
            Pixel[] centers = new Pixel[k];

            Random rand = new Random();
            for (int i = 0; i < k; i++) {
                centers[i] = new Pixel(image.getRGB(rand.nextInt(wight), rand.nextInt(height)));
            }
            int[] clusterNumber = new int[wight * height];
            boolean changed = true;
            while (changed) {
                int bestCluster;
                int bestDistance;
                for (int i = 0; i < pixels.length; i++) {
                    Pixel p = pixels[i];
                    for (int j = 0; j < k - 1; j++) {
                        int diffR = p.r - centers[j].r;
                        int  diffG = p.g - centers[j].g;
                        int diffB = p.b - centers[j].b;
                        int distSquared =  diffR * diffR + diffG * diffG + diffB * diffB;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Не удалось загрузить изображение");
            return;
        }
    }
}

class Pixel {
    int r, g, b;
    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public Pixel(int rgb) {
        this.r = (rgb >> 16) & 0xFF;
        this.g = (rgb >> 8) & 0xFF;
        this.b = rgb & 0xFF;
    }
}