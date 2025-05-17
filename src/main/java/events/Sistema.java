package events;

import java.awt.*;
import javax.swing.*;
import events.dao.propriedades.Menu.Head;

public class Sistema {
  public static void main(String[] args) {
    Tela tela = new Tela();
    tela.setVisible(true);
  }

  public static class Tela extends JFrame {
    public Tela() {
      setTitle("Sistema de Eventos");
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(null);

      Head head = new Head();
      head.setBounds(0, 0, 800, 50);
      Body body = new Body();
      //head.setBounds(0, 0, 800, 50);
      body.setBounds(0, 50, 800, 550);
      head.setBackground(Color.black);
      body.setBackground(Color.white);
      add(head);
      add(body);
    }
  }

  public static class Body extends JPanel {
    public Body() {
      setBounds(0, 50, 800, 550);
      setBackground(Color.white);
      setLayout(null);

      Titulo titulo = new Titulo();
      titulo.setBounds(300, 200, 200, 30);
      add(titulo);
    }
  }

  public static class Titulo extends JLabel {
    public Titulo() {
      setText("Sistema de Eventos");
      setFont(new Font("Arial", Font.BOLD, 18));
      setForeground(Color.BLACK);
    }
  }

  public static class UrlEventos extends JLabel {
    public UrlEventos() {
      setText("Eventos");
      setFont(new Font("Arial", Font.BOLD, 14));
      setForeground(Color.WHITE);
    }
  }

  public static class UrlPalestrante extends JLabel {
    public UrlPalestrante() {
      setText("Palestrantes");
      setFont(new Font("Arial", Font.BOLD, 14));
      setForeground(Color.WHITE);
    }
  }

  public static class UrlParticipante extends JLabel {
    public UrlParticipante() {
      setText("Participantes");
      setFont(new Font("Arial", Font.BOLD, 14));
      setForeground(Color.WHITE);
    }
  }

  public static class Versao extends JLabel {
    public Versao() {
      setText("Versão 1.0");
      setFont(new Font("Arial", Font.BOLD, 14));
      setForeground(Color.WHITE);
    }
  }
}
