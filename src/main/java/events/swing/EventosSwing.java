package events.swing;

import javax.swing.*;

import events.dao.EventoDao;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventosSwing {
  static events.dao.Contrutores.EventoConstrutor construtor = new events.dao.Contrutores.EventoConstrutor();
  static EventoDao eventoDao = new EventoDao(construtor);

  public static class Evento extends JFrame {
    public Evento() {
      setTitle("Sistema de Eventos");
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(null);
      setVisible(true);

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
          Evento.this.dispose();
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
          Evento.this.dispose();
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
          Evento.this.dispose();
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

      JTextField campoNome = new JTextField();
      construtor.setNome(campoNome);
      campoNome.setBounds(50, 50, 200, 50);
      campoNome.setBorder(BorderFactory.createTitledBorder("Nome do Evento"));
      campoNome.setFont(new Font("Arial", Font.PLAIN, 14));
      campoNome.setForeground(Color.black);
      campoNome.setBackground(Color.white);
      campoNome.setCaretColor(Color.black);
      campoNome.setSelectionColor(Color.black);
      campoNome.setSelectedTextColor(Color.white);

      JTextField campoDescricao = new JTextField();
      construtor.setDescricao(campoDescricao);
      campoDescricao.setBounds(50, 110, 200, 50);
      campoDescricao.setBorder(BorderFactory.createTitledBorder("Descrição do Evento"));
      campoDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
      campoDescricao.setForeground(Color.black);
      campoDescricao.setBackground(Color.white);
      campoDescricao.setCaretColor(Color.black);
      campoDescricao.setSelectionColor(Color.black);
      campoDescricao.setSelectedTextColor(Color.white);

      JTextField campoData = new JTextField();
      construtor.setData(campoData);
      campoData.setBounds(50, 170, 200, 50);
      campoData.setBorder(BorderFactory.createTitledBorder("Data do Evento"));
      campoData.setFont(new Font("Arial", Font.PLAIN, 14));
      campoData.setForeground(Color.black);
      campoData.setBackground(Color.white);
      campoData.setCaretColor(Color.black);
      campoData.setSelectionColor(Color.black);
      campoData.setSelectedTextColor(Color.white);

      JTextField campoLocal = new JTextField();
      construtor.setLocal(campoLocal);
      campoLocal.setBounds(50, 230, 200, 50);
      campoLocal.setBorder(BorderFactory.createTitledBorder("Local do Evento"));
      campoLocal.setFont(new Font("Arial", Font.PLAIN, 14));
      campoLocal.setForeground(Color.black);
      campoLocal.setBackground(Color.white);
      campoLocal.setCaretColor(Color.black);
      campoLocal.setSelectionColor(Color.black);
      campoLocal.setSelectedTextColor(Color.white);

      JTextField campoCapacidade = new JTextField();
      construtor.setCapacidade(campoCapacidade);
      campoCapacidade.setBounds(50, 290, 200, 50);
      campoCapacidade.setBorder(BorderFactory.createTitledBorder("Capacidade do Evento"));
      campoCapacidade.setFont(new Font("Arial", Font.PLAIN, 14));
      campoCapacidade.setForeground(Color.black);
      campoCapacidade.setBackground(Color.white);
      campoCapacidade.setCaretColor(Color.black);
      campoCapacidade.setSelectionColor(Color.black);
      campoCapacidade.setSelectedTextColor(Color.white);

      JTextField campoPalestrante = new JTextField();
      construtor.setPalestrante(campoPalestrante);
      campoPalestrante.setBounds(50, 350, 200, 50);
      campoPalestrante.setBorder(BorderFactory.createTitledBorder("Palestrante do Evento"));
      campoPalestrante.setFont(new Font("Arial", Font.PLAIN, 14));
      campoPalestrante.setForeground(Color.black);
      campoPalestrante.setBackground(Color.white);
      campoPalestrante.setCaretColor(Color.black);
      campoPalestrante.setSelectionColor(Color.black);
      campoPalestrante.setSelectedTextColor(Color.white);

      JTextField campoId = new JTextField();
      construtor.setId(campoId);
      campoId.setBounds(290, 423, 200, 50);
      campoId.setBorder(BorderFactory.createTitledBorder("ID do Evento"));
      campoId.setFont(new Font("Arial", Font.PLAIN, 14));
      campoId.setForeground(Color.black);
      campoId.setBackground(Color.white);
      campoId.setCaretColor(Color.black);
      campoId.setSelectionColor(Color.black);
      campoId.setSelectedTextColor(Color.white);

      JButton botaoSalvar = new JButton("Salvar");
      botaoSalvar.setBounds(50, 430, 90, 40);
      botaoSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoSalvar.setForeground(Color.white);
      botaoSalvar.setBackground(Color.black);
      botaoSalvar.setBorder(BorderFactory.createEtchedBorder());
      botaoSalvar.setFocusable(false);
      botaoSalvar.addActionListener(e -> {
        eventoDao.Conexao();
        eventoDao.Inserir();
      });

      Visualizar visualizar = new Visualizar(eventoDao);
      JButton botaoVisualizar = new JButton("Visualizar");
      botaoVisualizar.setBounds(160, 430, 90, 40);
      botaoVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoVisualizar.setForeground(Color.white);
      botaoVisualizar.setBackground(Color.black);
      botaoVisualizar.setBorder(BorderFactory.createEtchedBorder());
      botaoVisualizar.setFocusable(false);
      botaoVisualizar.addActionListener(e -> {
        eventoDao.Conexao();
        eventoDao.Leitura(visualizar.getTextArea());
      });

      JButton botaoAtualizar = new JButton("Atualizar");
      botaoAtualizar.setBounds(510, 430, 90, 40);
      botaoAtualizar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoAtualizar.setForeground(Color.white);
      botaoAtualizar.setBackground(Color.black);
      botaoAtualizar.setBorder(BorderFactory.createEtchedBorder());
      botaoAtualizar.setFocusable(false);
      botaoAtualizar.addActionListener(e -> {
        eventoDao.Conexao();
        eventoDao.Atualizar();
      });

      JButton botaoExcluir = new JButton("Excluir");
      botaoExcluir.setBounds(620, 430, 90, 40);
      botaoExcluir.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoExcluir.setForeground(Color.white);
      botaoExcluir.setBackground(Color.black);
      botaoExcluir.setBorder(BorderFactory.createEtchedBorder());
      botaoExcluir.setFocusable(false);
      botaoExcluir.addActionListener(e -> {
        eventoDao.Conexao();
        eventoDao.Excluir();
      });

      body.add(campoNome);
      body.add(campoDescricao);
      body.add(campoData);
      body.add(campoLocal);
      body.add(campoCapacidade);
      body.add(campoPalestrante);
      body.add(campoId);
      body.add(botaoSalvar);
      body.add(botaoVisualizar);
      body.add(botaoAtualizar);
      body.add(botaoExcluir);
      body.add(visualizar);
      head.add(eventos);
      head.add(palestrante);
      head.add(participante);
      add(head);
      add(body);
    }
  }

  public static class Visualizar extends JPanel {
    private JTextArea textArea;

    public Visualizar(EventoDao eventoDao) {
      setBounds(290, 50, 450, 350);
      setBackground(Color.white);
      setLayout(null);

      textArea = new JTextArea();
      textArea.setFont(new Font("Arial", Font.PLAIN, 14));
      textArea.setForeground(Color.black);
      textArea.setBackground(Color.white);
      textArea.setCaretColor(Color.black);
      textArea.setSelectionColor(Color.black);
      textArea.setSelectedTextColor(Color.white);
      textArea.setEditable(false);

      JScrollPane scrollPane = new JScrollPane(textArea);
      scrollPane.setBounds(0, 0, 450, 350);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setBorder(BorderFactory.createTitledBorder("Eventos Cadastrados"));

      add(scrollPane);
    }

    public JTextArea getTextArea() {
      return textArea;
    }
  }
}
