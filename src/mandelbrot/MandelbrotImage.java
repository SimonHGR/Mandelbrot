package mandelbrot;

/*
 * MandelbrotImage.java
 * Basic Mandelbrot image class, for use as a starting point for Java
 * concurrency exercises
 *
 * Author Simon Roberts
 *
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

public final class MandelbrotImage {

    private static final Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int BUFFER_WIDTH = SCREEN_DIMENSION.width;
    private static final int BUFFER_HEIGHT = SCREEN_DIMENSION.height;

    private double x0, y0;
    private double scale;
    private BufferedImage image;
    private List<Component> repaintListeners = new ArrayList<>();

    public MandelbrotImage(double originX, double originY, double scale) {
        image = new BufferedImage(BUFFER_WIDTH, BUFFER_HEIGHT, BufferedImage.TYPE_INT_RGB);
        zoomTo(originX, originY, scale);
    }
    
    public void zoomTo(double originX, double originY, double scale) {
        x0 = originX;
        y0 = originY;
        this.scale = scale;
        recalculate();
    }

    public void panTo(double originX, double originY) {
        x0 = originX;
        y0 = originY;
        recalculate();
    }
    
    public void scaleTo(double scale) {
        this.scale = scale;
        recalculate();
    }
    
    public void scaleBy(double factor) {
        scale *= factor;
        recalculate();
    }

    private void recalculate() {
        for (int y = 0; y < BUFFER_HEIGHT; y++) {
            for (int x = 0; x < BUFFER_WIDTH; x++) {
                int val = compute(getX(x), getY(y));
                image.setRGB(x, y, Color.HSBtoRGB((360 * val) / 4000.0F, 1.0F,
                        (val == 4000 ? 0.0F : 0.5F)));
            }
        }
        notifyListeners();
    }
    
    public Image getImage() {
        return image;
    }

    public static int compute(double x0, double y0) {
        double x = 0;
        double y = 0;

        int iteration = 0;
        int max_iteration = 4000;

        while ((x * x + y * y) < 4 && iteration < max_iteration) {
            double xtemp = x * x - y * y + x0;
            y = 2 * x * y + y0;
            x = xtemp;
            iteration++;
        }
        return iteration;
    }
    
    public double getX(int x) {
        return x0 + ((x - (BUFFER_WIDTH/2)) * scale);
    }
    
    public double getY(int y) {
        return y0 - ((y - (BUFFER_HEIGHT/2)) * scale);
    }
    
    public void addRepaintListener(Component obs) {
        repaintListeners.add(obs);
    }
    
    private void notifyListeners() {
        for (Component c : repaintListeners) {
            c.repaint();
        }
    }
}
