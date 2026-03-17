import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. загрузка изображения
            File inputFile = new File("C:\\Users\\vertoleha\\IdeaProjects\\labsAI\\mylittlepony2.jpg");
            BufferedImage originalImage = ImageIO.read(inputFile);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            // подготовка массива пикселей
            Pixel[] pixels = new Pixel[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixels[y * width + x] = new Pixel(originalImage.getRGB(x, y));
                }
            }

            // 2. настройка K-means
            int k = 8;
            Pixel[] centers = new Pixel[k];
            Random rand = new Random();
            for (int i = 0; i < k; i++) {
                centers[i] = pixels[rand.nextInt(pixels.length)]; // начальные центры случайным образом
            }

            int[] clusterNumber = new int[pixels.length];
            boolean changed = true;
            int maxIterations = 50;
            int iter = 0;

            // цикл минимизации расстояний (алгоритм Штейнгауза)
            while (changed && iter < maxIterations) {
                changed = false;
                long[] sumR = new long[k], sumG = new long[k], sumB = new long[k];
                int[] count = new int[k];

                for (int i = 0; i < pixels.length; i++) {
                    Pixel p = pixels[i];
                    int bestCluster = 0;
                    long minDistanceSq = Long.MAX_VALUE;

                    for (int j = 0; j < k; j++) {
                        // Евклидово расстояние
                        long dr = p.r - centers[j].r;
                        long dg = p.g - centers[j].g;
                        long db = p.b - centers[j].b;
                        long distSq = dr * dr + dg * dg + db * db;
                        if (distSq < minDistanceSq) {
                            minDistanceSq = distSq;
                            bestCluster = j;
                        }
                    }

                    if (clusterNumber[i] != bestCluster) {
                        clusterNumber[i] = bestCluster;
                        changed = true;
                    }
                    sumR[bestCluster] += p.r;
                    sumG[bestCluster] += p.g;
                    sumB[bestCluster] += p.b;
                    count[bestCluster]++;
                }

                for (int j = 0; j < k; j++) {
                    if (count[j] > 0) {
                        centers[j] = new Pixel((int)(sumR[j]/count[j]), (int)(sumG[j]/count[j]), (int)(sumB[j]/count[j]));
                    }
                }
                iter++;
            }

            // 3. создание итогогого склеенного изображения
            int paletteHeight = 100;
            int totalWidth = width * 2 + 20; // две картинки + отступ
            int totalHeight = height + paletteHeight + 40; // высота + палитра + отступы

            BufferedImage combined = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = combined.createGraphics();

            // заливаем фон белым
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, totalWidth, totalHeight);

            // рисуем старую фотку
            g.drawImage(originalImage, 0, 0, null);
            g.setColor(Color.BLACK);
            g.drawString("Оригинал", 10, 20);

            // генерируем новую фотку (сжатую)
            BufferedImage compressedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < pixels.length; i++) {
                Pixel c = centers[clusterNumber[i]];
                compressedImage.setRGB(i % width, i / width, (c.r << 16) | (c.g << 8) | c.b);
            }
            g.drawImage(compressedImage, width + 20, 0, null);
            g.drawString("K-means (k=" + k + ")", width + 30, 20);

            // рисуем палитру
            int cellWidth = totalWidth / k;
            for (int j = 0; j < k; j++) {
                g.setColor(new Color(centers[j].r, centers[j].g, centers[j].b));
                g.fillRect(j * cellWidth, height + 20, cellWidth, paletteHeight);
                // Рисуем рамку вокруг цвета
                g.setColor(Color.GRAY);
                g.drawRect(j * cellWidth, height + 20, cellWidth, paletteHeight);
            }
            g.setColor(Color.BLACK);
            g.drawString("Цветовая палитра", 10, height + 15);

            g.dispose();

            ImageIO.write(combined, "png", new File("result_lab2.png"));
            System.out.println("Сравнение сохранено в файл: result_lab2.png");

        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла.");
        }
    }
}

class Pixel {
    int r, g, b;
    Pixel(int r, int g, int b) { this.r = r; this.g = g; this.b = b; }
    Pixel(int rgb) {
        this.r = (rgb >> 16) & 0xFF;
        this.g = (rgb >> 8) & 0xFF;
        this.b = rgb & 0xFF;
    }
}