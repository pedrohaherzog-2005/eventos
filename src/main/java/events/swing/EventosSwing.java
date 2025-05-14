package events.swing;

import javax.swing.*;
import java.awt.*;

public class EventosSwing {
  public void Frame() {
    JFrame frame = new JFrame("Eventos");
    frame.setResizable(false);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/icon.png"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);
    frame.setVisible(true);
  }
}
