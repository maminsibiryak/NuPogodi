import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() throws HeadlessException {
        setTitle("Ну, Погоди!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 665);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}
