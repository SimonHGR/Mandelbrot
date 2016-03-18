package mandelbrot;
/*
 * MandelbrotLauncher.java
 * Basic Mandelbrot viewer launcher class, 
 * For use as a starting point for Java concurrency exercises
 *
 * Author Simon Roberts
 *
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MandelbrotLauncher {
    private JFrame frame;
    private MandelbrotImagePane panel;

    public static void main(String[] args) {
        new MandelbrotLauncher().go();
    }
    
    public void go() {
        frame = new JFrame("Mandelbrot Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MandelbrotImage mi = new MandelbrotImage(-0.75, 0, 3.5/1000);

        panel = new MandelbrotImagePane(mi);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
