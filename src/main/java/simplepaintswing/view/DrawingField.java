package simplepaintswing.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import static simplepaintswing.utils.constants.Constants.*;

public class DrawingField extends JComponent {
    private final ArrayList<Shape> figures = new ArrayList<>();
    private final ArrayList<Color> colors = new ArrayList<>();
    private Color cursorColor;
    private Point drawStart, drawEnd;
    private int currentAction;
    private final Container container;

    public DrawingField(Container container) {
        this.container = container;

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                currentAction = container.getCurrentAction();
                if (currentAction == 2) {
                    drawStart = new Point(e.getX(), e.getY());
                    drawEnd = drawStart;
                    repaint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                Shape shape;
                currentAction = container.getCurrentAction();
                cursorColor = container.getCursorColor();
                if (currentAction == 2) {
                    shape = drawLine(drawStart.x, drawStart.y,
                            e.getX(), e.getY());
                } else {
                    shape = drawBrush(e.getX(), e.getY());
                }
                figures.add(shape);
                colors.add(cursorColor);
                drawStart = null;
                drawEnd = null;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentAction = container.getCurrentAction();
                cursorColor = container.getCursorColor();
                if (currentAction == 1) {
                    int x = e.getX();
                    int y = e.getY();
                    Shape shape = drawBrush(x, y);
                    figures.add(shape);
                    colors.add(cursorColor);
                }
                drawEnd = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private boolean isFileExists(String filename) {
        File tempFile = new File("./" + filename);
        return tempFile.exists();
    }

    public void paint(Graphics g) {
        Graphics2D graphSettings = (Graphics2D) g;
        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphSettings.setStroke(new BasicStroke(4));
        String image = container.getImage();
        if (image.contains(".") && isFileExists(image)) {
            String imageFormat = image.substring(image.lastIndexOf('.') + 1);
            if (imageFormat.equals(PNG) || imageFormat.equals(GIF) || imageFormat.equals(JPEG) ||
                    imageFormat.equals(JPG) || imageFormat.equals(BMP)) {
                Image imageImage = new ImageIcon("./" + image).getImage();
                graphSettings.drawImage(imageImage, 0, 0, null);
            }
        }
        Iterator<Color> fillCounter = colors.iterator();

        for (Shape shape : figures) {
            graphSettings.draw(shape);
            graphSettings.setPaint(fillCounter.next());
            graphSettings.fill(shape);
        }

        if (drawStart != null && drawEnd != null) {
            graphSettings.setPaint(Color.LIGHT_GRAY);
            Shape aShape = null;
            if (currentAction == 2) {
                aShape = drawLine(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
            }
            graphSettings.draw(aShape);
        }
    }

    private Line2D.Float drawLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }

    private Ellipse2D.Float drawBrush(int x1, int y1) {
        return new Ellipse2D.Float(x1, y1, container.getThicknessValue(), container.getThicknessValue());
    }
}