/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

public class MyButton extends JButton {

    private boolean isHovered = false;
    private boolean isClicked = false;
    private Color targetColor = new Color(51, 99, 104); // Target color when clicked
    private Timer timer;

    public MyButton() {
        super();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isClicked = true;
                startAnimation();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isClicked = false;
                stopAnimation();
                repaint();
            }
        });

        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animateColor();
            }
        });
    }

    private void startAnimation() {
        timer.start();
    }

    private void stopAnimation() {
        timer.stop();
    }

    private void animateColor() {
        Color currentColor = getBackground();
        int rDiff = targetColor.getRed() - currentColor.getRed();
        int gDiff = targetColor.getGreen() - currentColor.getGreen();
        int bDiff = targetColor.getBlue() - currentColor.getBlue();

        int rStep = rDiff / 10; // Adjust the step size for smoother animation
        int gStep = gDiff / 10;
        int bStep = bDiff / 10;

        Color newColor = new Color(
                currentColor.getRed() + rStep,
                currentColor.getGreen() + gStep,
                currentColor.getBlue() + bStep
        );

        setBackground(newColor);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = 20; // Adjust the value to change the curvature of the corners

        // Draw button background
        if (isClicked) {
            g2.setPaint(new GradientPaint(0, 0, getBackground(), 0, height, getBackground().darker()));
        } else if (isHovered) {
            g2.setPaint(new GradientPaint(0, 0, new Color(51, 99, 104), 0, height, new Color(51, 99, 104)));
        } else {
            g2.setPaint(new GradientPaint(0, 0, new Color(240, 240, 240), 0, height, new Color(200, 200, 200)));
        }
        g2.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }
}
