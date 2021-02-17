package simplepaintswing.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static simplepaintswing.utils.constants.Constants.*;

public class PaintView {
    int thicknessValue = 5;
    Color cursorColor = Color.BLACK;
    private final Container container = new Container();

    public PaintView() {
        JFrame frame = new JFrame(TITTLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DrawingField drawingField = new DrawingField(container);

        JPanel buttonPanel = new JPanel();
        Box buttonContainer = Box.createHorizontalBox();

        JButton dotButton = new JButton();
        Icon dotButtonIcon = new ImageIcon(PATH_TO_BRUSH);
        dotButton.setIcon(dotButtonIcon);
        dotButton.addActionListener(e -> {
            container.setCurrentAction(1);
            container.setCursorColor(Color.BLACK);
        });

        JButton lineButton = new JButton();
        Icon lineButtonIcon = new ImageIcon(PATH_TO_LINE);
        lineButton.setIcon(lineButtonIcon);
        lineButton.addActionListener(e -> {
            container.setCurrentAction(2);
            container.setCursorColor(Color.BLACK);
        });

        JButton colorButton = new JButton();
        Icon colorButtonIcon = new ImageIcon(PATH_TO_FILL);
        colorButton.setIcon(colorButtonIcon);
        colorButton.addActionListener(e -> {
            cursorColor = JColorChooser.showDialog(null, CHOOSE_COLOR, Color.BLACK);
            container.setCursorColor(cursorColor);
        });

        JLabel thickness = new JLabel(THICKNESS + "5");
        JSlider transSlider = new JSlider(1, 99, 5);
        transSlider.addChangeListener(e -> {
            thickness.setText(THICKNESS + transSlider.getValue());
            thicknessValue = transSlider.getValue();
            container.setThicknessValue(thicknessValue);
        });

        JButton saveButton = new JButton();
        Icon saveButtonIcon = new ImageIcon(PATH_TO_SAVE);
        saveButton.setIcon(saveButtonIcon);
        saveButton.addActionListener(e -> {
            String savedImage = JOptionPane.showInputDialog(
                    null,
                    IN_FORMAT,
                    ENTER_IMAGE_NAME,
                    JOptionPane.INFORMATION_MESSAGE
            );
            String format = savedImage.substring(savedImage.lastIndexOf(".") + 1);
            if (savedImage.contains(".") && (format.equals(PNG) || format.equals(GIF) || format.equals(JPEG) ||
                    format.equals(JPG) || format.equals(BMP))) {
                Dimension dimension = drawingField.getSize();
                BufferedImage bufferedImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = bufferedImage.createGraphics();
                drawingField.printAll(graphics2D);
                graphics2D.dispose();
                try {
                    ImageIO.write(bufferedImage, format, new File("./" + savedImage));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                Icon errorIcon = new ImageIcon(PATH_TO_ERROR);
                JOptionPane.showMessageDialog(null, INCORRECT_FORMAT, ERROR, JOptionPane.ERROR_MESSAGE, errorIcon);
            }
        });

        JButton loadButton = new JButton();
        Icon loadButtonIcon = new ImageIcon(PATH_TO_LOAD);
        loadButton.setIcon(loadButtonIcon);
        loadButton.addActionListener(e -> {
            String image = JOptionPane.showInputDialog(
                    null,
                    IN_FORMAT,
                    ENTER_IMAGE_NAME,
                    JOptionPane.INFORMATION_MESSAGE
            );
            container.setImage(image);
        });
        JButton eraserButton = new JButton();
        Icon eraserButtonIcon = new ImageIcon(PATH_TO_ERASER);
        eraserButton.setIcon(eraserButtonIcon);
        eraserButton.addActionListener(e -> {
            container.setCurrentAction(1);
            Color erase = new Color(238, 238, 238);
            container.setCursorColor(erase);
        });

        buttonContainer.add(saveButton);
        buttonContainer.add(loadButton);
        buttonContainer.add(dotButton);
        buttonContainer.add(lineButton);
        buttonContainer.add(colorButton);
        buttonContainer.add(eraserButton);
        buttonContainer.add(thickness);
        buttonContainer.add(transSlider);
        buttonPanel.add(buttonContainer);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(drawingField, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }
}
