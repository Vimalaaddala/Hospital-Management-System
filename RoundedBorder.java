package javax.swing.border;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
    private final int radius;
    public RoundedBorder(int radius) { this.radius = radius; }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(c.getForeground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new RoundRectangle2D.Double(x, y, width-1, height-1, radius, radius));
        g2.dispose();
    }
}
