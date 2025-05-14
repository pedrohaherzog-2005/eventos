package events;

import java.awt.*;
import javax.swing.*;

import events.Class.Evento;
import events.swing.EventosSwing;

public class Sistema {
  public static void main(String[] args) {
    Sistema sistema = new Sistema();
    sistema.Frame();
  }

  public void Frame() {
    JFrame frame = new JFrame("Sistema de Eventos");
    frame.setResizable(false);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/icon.png"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);

    frame.add(Head(frame));
    frame.add(Body());
    frame.setVisible(true);
  }

  public JPanel Head(JFrame frame) {
    JPanel head = new JPanel();
    head.setBounds(0, 0, 800, 50);
    head.setBackground(Color.black);
    head.setLayout(null);

    JLabel eventos = Eventos();
    JLabel palestrante = Palestrante();
    JLabel participante = Participante();
    JLabel versao = Versao();
    eventos.setBounds(480, 10, 100, 30);
    palestrante.setBounds(570, 10, 100, 30);
    participante.setBounds(680, 10, 100, 30);
    versao.setBounds(25, 10, 100, 30);
    eventos.setCursor(new Cursor(Cursor.HAND_CURSOR));
    palestrante.setCursor(new Cursor(Cursor.HAND_CURSOR));
    participante.setCursor(new Cursor(Cursor.HAND_CURSOR));

    eventos.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        frame.dispose();
        EventosSwing eventosSwing = new EventosSwing();
        eventosSwing.Frame();
      }
    });

    head.add(eventos);
    head.add(palestrante);
    head.add(participante);
    head.add(versao);
    return head;
  }

  public JPanel Body() {
    JPanel body = new JPanel();
    body.setBounds(0, 50, 800, 550);
    body.setBackground(Color.white);
    body.setLayout(null);

    JLabel title = Title();
    title.setBounds(300, 200, 200, 30);

    JPanel desempenho = Desempenho();
    desempenho.setBounds(600, 450, 180, 90);

    body.add(desempenho);
    body.add(title);
    return body;
  }

  public JLabel Title() {
    JLabel title = new JLabel("Sistema de Eventos");
    title.setFont(new Font("Arial", Font.BOLD, 18));
    title.setForeground(Color.BLACK);
    return title;
  }

  public JLabel Eventos() {
    JLabel eventos = new JLabel("Eventos");
    eventos.setFont(new Font("Arial", Font.BOLD, 14));
    eventos.setForeground(Color.WHITE);
    return eventos;
  }

  public JLabel Palestrante() {
    JLabel palestrante = new JLabel("Palestrante");
    palestrante.setFont(new Font("Arial", Font.BOLD, 14));
    palestrante.setForeground(Color.WHITE);
    return palestrante;
  }

  public JLabel Participante() {
    JLabel participante = new JLabel("Participante");
    participante.setFont(new Font("Arial", Font.BOLD, 14));
    participante.setForeground(Color.WHITE);
    return participante;
  }

  public JLabel Versao() {
    JLabel versao = new JLabel("Versão 1.0");
    versao.setFont(new Font("Arial", Font.BOLD, 14));
    versao.setForeground(Color.WHITE);
    return versao;
  }

  public JPanel Desempenho() {
    JPanel desempenho = new JPanel();
    desempenho.setBackground(Color.white);
    desempenho.setLayout(null);

    JLabel cpuLabel = new JLabel("CPU: --%");
    cpuLabel.setBounds(0, 30, 100, 30);

    JLabel ramLabel = new JLabel("RAM: --%");
    ramLabel.setBounds(90, 30, 100, 30);

    desempenho.add(cpuLabel);
    desempenho.add(ramLabel);

    new javax.swing.Timer(900, e -> {
      double cpuLoad = getCpuUsage();
      double ramUsage = getRamUsage();
      cpuLabel.setText(String.format("CPU: %.2f%%", cpuLoad * 100));
      ramLabel.setText(String.format("RAM: %.2f%%", ramUsage * 100));
    }).start();
    return desempenho;
  }

  private double getCpuUsage() {
    try {
      com.sun.management.OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory
          .getOperatingSystemMXBean();
      return osBean.getProcessCpuLoad();
    } catch (Exception e) {
      return 0.0;
    }
  }

  private double getRamUsage() {
    try {
      Runtime runtime = Runtime.getRuntime();
      long total = runtime.totalMemory();
      long free = runtime.freeMemory();
      return (double) (total - free) / total;
    } catch (Exception e) {
      return 0.0;
    }
  }
}
