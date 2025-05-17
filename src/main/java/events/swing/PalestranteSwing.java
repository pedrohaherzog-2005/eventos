package events.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import events.Sistema.Body;
import events.dao.PalestranteDao;
import events.dao.Contrutores.PalestranteConstrutor;
import events.dao.propriedades.Menu.Head;

public class PalestranteSwing {
  PalestranteConstrutor palestranteConstructor = new PalestranteConstrutor();
  PalestranteDao palestranteDao = new PalestranteDao(palestranteConstructor);

  public static void main(String[] args) {
    PalestranteFrame pFrame = new PalestranteFrame();
    pFrame.setVisible(true);
  }

  public static class PalestranteFrame extends JFrame {
    public PalestranteFrame() {
      setTitle("Área do Palestrante");
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(null);

      Head head = new Head();
      head.setBounds(0, 0, 800, 50);
      head.setBackground(Color.BLACK);

      Body body = new Body();
      add(head);
      add(body);
    }
  }

  public static class Body extends JPanel {
    public Body() {
      setBounds(0, 50, 800, 550);
      setBackground(Color.WHITE);
      setLayout(null);
    }
  }
}
