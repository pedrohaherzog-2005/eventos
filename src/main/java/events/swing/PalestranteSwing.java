package events.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import events.dao.PalestranteDao;

public class PalestranteSwing {
  static events.dao.Contrutores.PalestranteConstrutor construtor = new events.dao.Contrutores.PalestranteConstrutor();
  static PalestranteDao palestranteDao = new PalestranteDao(construtor);

  public static class PalestranteFrame extends JFrame {
    public PalestranteFrame() {
      setTitle("Área do Palestrante");
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(null);

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
          events.swing.EventosSwing.Evento tela = new events.swing.EventosSwing.Evento();
          tela.setVisible(true);
          PalestranteFrame.this.dispose();
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

      JLabel participante = new JLabel("Participante");
      participante.setFont(new Font("Arial", Font.BOLD, 16));
      participante.setBounds(650, 10, 100, 30);
      participante.setForeground(Color.white);
      participante.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          events.swing.ParticipanteSwing.ParticipanteFrame pFrame = new events.swing.ParticipanteSwing.ParticipanteFrame();
          pFrame.setVisible(true);
          PalestranteFrame.this.dispose();
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
      campoNome.setBorder(BorderFactory.createTitledBorder("Nome do Palestrante"));
      campoNome.setFont(new Font("Arial", Font.PLAIN, 14));
      campoNome.setForeground(Color.black);
      campoNome.setBackground(Color.white);
      campoNome.setCaretColor(Color.black);
      campoNome.setSelectionColor(Color.black);
      campoNome.setSelectedTextColor(Color.white);

      JTextField campoCurriculo = new JTextField();
      construtor.setCurriculo(campoCurriculo);
      campoCurriculo.setBounds(50, 110, 200, 50);
      campoCurriculo.setBorder(BorderFactory.createTitledBorder("Currículo"));
      campoCurriculo.setFont(new Font("Arial", Font.PLAIN, 14));
      campoCurriculo.setForeground(Color.black);
      campoCurriculo.setBackground(Color.white);
      campoCurriculo.setCaretColor(Color.black);
      campoCurriculo.setSelectionColor(Color.black);
      campoCurriculo.setSelectedTextColor(Color.white);

      JTextField campoAtuacao = new JTextField();
      construtor.setAtuacao(campoAtuacao);
      campoAtuacao.setBounds(50, 170, 200, 50);
      campoAtuacao.setBorder(BorderFactory.createTitledBorder("Área de Atuação"));
      campoAtuacao.setFont(new Font("Arial", Font.PLAIN, 14));
      campoAtuacao.setForeground(Color.black);
      campoAtuacao.setBackground(Color.white);
      campoAtuacao.setCaretColor(Color.black);
      campoAtuacao.setSelectionColor(Color.black);
      campoAtuacao.setSelectedTextColor(Color.white);

      JTextField campoEvento = new JTextField();
      construtor.setEvento(campoEvento);
      campoEvento.setBounds(50, 230, 200, 50);
      campoEvento.setBorder(BorderFactory.createTitledBorder("Evento Vinculado"));
      campoEvento.setFont(new Font("Arial", Font.PLAIN, 14));
      campoEvento.setForeground(Color.black);
      campoEvento.setBackground(Color.white);
      campoEvento.setCaretColor(Color.black);
      campoEvento.setSelectionColor(Color.black);
      campoEvento.setSelectedTextColor(Color.white);

      JTextField campoId = new JTextField();
      construtor.setId(campoId);
      campoId.setBounds(290, 423, 200, 50);
      campoId.setBorder(BorderFactory.createTitledBorder("ID do Palestrante"));
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
        palestranteDao.Conexao();
        palestranteDao.Inserir();
      });

      Visualizar visualizar = new Visualizar(palestranteDao);
      JButton botaoVisualizar = new JButton("Visualizar");
      botaoVisualizar.setBounds(160, 430, 90, 40);
      botaoVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoVisualizar.setForeground(Color.white);
      botaoVisualizar.setBackground(Color.black);
      botaoVisualizar.setBorder(BorderFactory.createEtchedBorder());
      botaoVisualizar.setFocusable(false);
      botaoVisualizar.addActionListener(e -> {
        palestranteDao.Conexao();
        palestranteDao.Leitura(visualizar.getTextArea());
      });

      JButton botaoAtualizar = new JButton("Atualizar");
      botaoAtualizar.setBounds(510, 430, 90, 40);
      botaoAtualizar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoAtualizar.setForeground(Color.white);
      botaoAtualizar.setBackground(Color.black);
      botaoAtualizar.setBorder(BorderFactory.createEtchedBorder());
      botaoAtualizar.setFocusable(false);
      botaoAtualizar.addActionListener(e -> {
        palestranteDao.Conexao();
        palestranteDao.Atualizar();
      });

      JButton botaoExcluir = new JButton("Excluir");
      botaoExcluir.setBounds(620, 430, 90, 40);
      botaoExcluir.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoExcluir.setForeground(Color.white);
      botaoExcluir.setBackground(Color.black);
      botaoExcluir.setBorder(BorderFactory.createEtchedBorder());
      botaoExcluir.setFocusable(false);
      botaoExcluir.addActionListener(e -> {
        palestranteDao.Conexao();
        palestranteDao.Excluir();
      });

      body.add(campoNome);
      body.add(campoCurriculo);
      body.add(campoAtuacao);
      body.add(campoEvento);
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

    public Visualizar(PalestranteDao palestranteDao) {
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
      scrollPane.setBorder(BorderFactory.createTitledBorder("Palestrantes Cadastrados"));

      add(scrollPane);
    }

    public JTextArea getTextArea() {
      return textArea;
    }
  }
}
