package mandelbrot;

/*
 * MandelbrotImagePane.java
 * Basic Component type class, for displaying mandelbrot patterns
 * For use as a starting point for Java
 * concurrency exercises
 *
 * Author Simon Roberts
 *
 */

import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public final class MandelbrotImagePane extends JPanel implements MouseListener, KeyListener {
    private MandelbrotImage mandelbrotImage;
    
    public MandelbrotImagePane(MandelbrotImage mi) {
        mandelbrotImage = mi;
        addMouseListener(this);
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        addKeyListener(this);
        mi.addRepaintListener(this);
    }
    
    @Override
    public void paint(Graphics g) {
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int bufferWidth = mandelbrotImage.getImage().getWidth(null);
        int bufferHeight = mandelbrotImage.getImage().getHeight(null);
        g.translate(-(bufferWidth-windowWidth)/2, -(bufferHeight-windowHeight)/2);
        g.drawImage(mandelbrotImage.getImage(), 0, 0, this);
    }
    
    @Override public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int bufferWidth = mandelbrotImage.getImage().getWidth(null);
        int bufferHeight = mandelbrotImage.getImage().getHeight(null);
        double targetX = mandelbrotImage.getX(x+(bufferWidth-windowWidth)/2);
        double targetY = mandelbrotImage.getY(y+(bufferHeight-windowHeight)/2);
        mandelbrotImage.panTo(targetX, targetY);
        this.repaint();
    }

    @Override public boolean isFocusable() { return true; }
            
    @Override public void keyTyped(KeyEvent e) {
        boolean changed = true;
        switch (e.getKeyChar()) {
            case '+':
                mandelbrotImage.scaleBy(0.5);
                break;
            case '-':
                mandelbrotImage.scaleBy(2);
                break;
            default:
                changed = false;
                break;
        }
        if (changed) {
            this.repaint();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}