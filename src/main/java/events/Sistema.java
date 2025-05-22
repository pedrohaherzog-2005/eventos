package events;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import events.swing.EventosSwing;
import events.swing.PalestranteSwing;
import events.swing.ParticipanteSwing;

public class Sistema extends JFrame {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Sistema de Eventos");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setLayout(null);
    frame.setBackground(Color.white);
    frame.setVisible(true);

    JPanel head = new JPanel();
    head.setBounds(0, 0, 800, 50);
    head.setLayout(null);
    head.setBackground(Color.black);

    JLabel eventos = new JLabel("Eventos");
    eventos.setFont(new Font("Arial", Font.BOLD, 16));
    eventos.setBounds(450, 10, 100, 30);
    eventos.setForeground(Color.white);

    eventos.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        EventosSwing.Evento tela = new EventosSwing.Evento();
        tela.setVisible(true);
        frame.dispose();
      }
      @Override
      public void mouseEntered(MouseEvent e) {
        eventos.setForeground(Color.YELLOW);
        eventos.setCursor(new Cursor(Cursor.HAND_CURSOR));
      }
      @Override
      public void mouseExited(MouseEvent e) {
        eventos.setForeground(Color.WHITE);
      }
    });

    JLabel palestrante = new JLabel("Palestrante");
    palestrante.setFont(new Font("Arial", Font.BOLD, 16));
    palestrante.setBounds(540, 10, 100, 30);
    palestrante.setForeground(Color.white);
    palestrante.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        PalestranteSwing.PalestranteFrame pFrame = new PalestranteSwing.PalestranteFrame();
        pFrame.setVisible(true);
        frame.dispose();
      }
      @Override
      public void mouseEntered(MouseEvent e) {
        palestrante.setForeground(Color.YELLOW);
        palestrante.setCursor(new Cursor(Cursor.HAND_CURSOR));
      }
      @Override
      public void mouseExited(MouseEvent e) {
        palestrante.setForeground(Color.WHITE);
      }
    });

    JLabel participante = new JLabel("Participante");
    participante.setFont(new Font("Arial", Font.BOLD, 16));
    participante.setBounds(650, 10, 100, 30);
    participante.setForeground(Color.white);
    participante.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        ParticipanteSwing.ParticipanteFrame pFrame = new ParticipanteSwing.ParticipanteFrame();
        pFrame.setVisible(true);
        frame.dispose();
      }
      @Override
      public void mouseEntered(MouseEvent e) {
        participante.setForeground(Color.YELLOW);
        participante.setCursor(new Cursor(Cursor.HAND_CURSOR));
      }
      @Override
      public void mouseExited(MouseEvent e) {
        participante.setForeground(Color.WHITE);
      }
    });

    JPanel body = new JPanel();
    body.setBounds(0, 50, 800, 550);
    body.setBackground(Color.white);
    body.setLayout(null);

    JLabel nome = new JLabel("Sistema de Eventos");
    nome.setFont(new Font("Arial", Font.BOLD, 20));
    nome.setBounds(300, 250, 250 , 60);
    nome.setForeground(Color.black);

    head.add(eventos);
    head.add(palestrante);
    head.add(participante);
    frame.add(nome);
    frame.add(head);
    frame.add(body);
  }
}
