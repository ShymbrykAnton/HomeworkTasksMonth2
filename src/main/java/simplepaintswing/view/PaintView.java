package simplepaintswing.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaintView {
    int thicknessValue = 5;
    Color cursorColor = Color.BLACK;
    private final Container container = new Container();

    public PaintView() {
        JFrame frame = new JFrame("Paint");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DrawingField drawingField = new DrawingField(container);

        JPanel buttonPanel = new JPanel();
        Box buttonContainer = Box.createHorizontalBox();

        JButton dotButton = new JButton();
        Icon dotButtonIcon = new ImageIcon("./src/main/java/simplepaintswing/utils/images/Brush.png");
        dotButton.setIcon(dotButtonIcon);
        dotButton.addActionListener(e -> {
            container.setCurrentAction(1);
            container.setCursorColor(Color.BLACK);
        });

        JButton lineButton = new JButton();
        Icon lineButtonIcon = new ImageIcon("./src/main/java/simplepaintswing/utils/images/Line.png");
        lineButton.setIcon(lineButtonIcon);
        lineButton.addActionListener(e -> {
            container.setCurrentAction(2);
            container.setCursorColor(Color.BLACK);
        });

        JButton colorButton = new JButton();
        Icon colorButtonIcon = new ImageIcon("./src/main/java/simplepaintswing/utils/images/Fill.png");
        colorButton.setIcon(colorButtonIcon);
        colorButton.addActionListener(e -> {
            cursorColor = JColorChooser.showDialog(null, "Выберите желаемый цвет", Color.BLACK);
            container.setCursorColor(cursorColor);
        });

        JLabel thickness = new JLabel("      Толщина кисти: 5");
        JSlider transSlider = new JSlider(1, 99, 5);
        transSlider.addChangeListener(e -> {
            thickness.setText("      Толщина кисти: " + transSlider.getValue());
            thicknessValue = transSlider.getValue();
            container.setThicknessValue(thicknessValue);
        });

        JButton saveButton = new JButton();
        Icon saveButtonIcon = new ImageIcon("./src/main/java/simplepaintswing/utils/images/Save.png");
        saveButton.setIcon(saveButtonIcon);
        saveButton.addActionListener(e -> {
            String savedImage = JOptionPane.showInputDialog(
                    null,
                    "В формате \"yourFileName.format\". В качестве формата доступны jpg, png, gif, jpeg, bmp",
                    "Введите название вашей картинки",
                    JOptionPane.INFORMATION_MESSAGE
            );
            String format = savedImage.substring(savedImage.lastIndexOf(".") + 1);
            if (savedImage.contains(".") && (format.equals("png") || format.equals("gif") || format.equals("jpeg") ||
                    format.equals("jpg") || format.equals("bmp"))) {
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
                Icon errorIcon = new ImageIcon("./src/main/java/simplepaintswing/utils/images/Error.png");
                JOptionPane.showMessageDialog(null,"Неправильно введенный формат","Error",JOptionPane.ERROR_MESSAGE,errorIcon);
            }
        });


        JButton loadButton = new JButton();
        Icon loadButtonIcon = new ImageIcon("./src/main/java/simplepaintswing/utils/images/Load.png");
        loadButton.setIcon(loadButtonIcon);
        loadButton.addActionListener(e -> {
            String image = JOptionPane.showInputDialog(
                    null,
                    "В формате \"yourFileName.format\". В качестве формата доступны jpg, png, gif, jpeg, bmp",
                    "Введите название вашей картинки",
                    JOptionPane.INFORMATION_MESSAGE
            );
            container.setImage(image);
        });
        JButton eraserButton = new JButton();
        Icon eraserButtonIcon = new ImageIcon("./src/main/java/simplepaintswing/utils/images/Eraser.png");
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
