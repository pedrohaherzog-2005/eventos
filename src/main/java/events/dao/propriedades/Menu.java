package events.dao.propriedades;

import java.awt.*;
import javax.swing.*;

import events.Sistema.UrlEventos;
import events.Sistema.UrlPalestrante;
import events.Sistema.UrlParticipante;
import events.Sistema.Versao;

public class Menu {
  public static class Head extends JPanel {
    public Head() {
      setBounds(0, 0, 800, 50);
      setBackground(Color.black);
      setLayout(null);

      UrlEventos eventos = new UrlEventos();
      UrlPalestrante palestrante = new UrlPalestrante();
      UrlParticipante participante = new UrlParticipante();
      Versao versao = new Versao();
      eventos.setBounds(480, 10, 100, 30);
      palestrante.setBounds(570, 10, 100, 30);
      participante.setBounds(680, 10, 100, 30);
      versao.setBounds(25, 10, 100, 30);
      eventos.setCursor(new Cursor(Cursor.HAND_CURSOR));
      palestrante.setCursor(new Cursor(Cursor.HAND_CURSOR));
      participante.setCursor(new Cursor(Cursor.HAND_CURSOR));

      add(eventos);
      add(palestrante);
      add(participante);
      add(versao);
    }
  }
}
