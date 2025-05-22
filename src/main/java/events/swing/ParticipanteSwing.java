package events.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import events.dao.ParticipanteDao;
import events.dao.contrutores.ParticipanteConstrutor;

public class ParticipanteSwing {
  static ParticipanteConstrutor construtor = new ParticipanteConstrutor();
  static ParticipanteDao participanteDao = new ParticipanteDao(construtor);

  public static class ParticipanteFrame extends JFrame {
    public ParticipanteFrame() {
      setTitle("Área do Participante");
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
          ParticipanteFrame.this.dispose();
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
          events.swing.PalestranteSwing.PalestranteFrame pFrame = new events.swing.PalestranteSwing.PalestranteFrame();
          pFrame.setVisible(true);
          ParticipanteFrame.this.dispose();
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

      head.add(eventos);
      head.add(palestrante);
      head.add(participante);

      JPanel body = new JPanel();
      body.setBounds(0, 50, 800, 550);
      body.setBackground(Color.white);
      body.setLayout(null);

      JTextField campoNome = new JTextField();
      construtor.setNome(campoNome);
      campoNome.setBounds(50, 50, 200, 50);
      campoNome.setBorder(BorderFactory.createTitledBorder("Nome do Participante"));

      JTextField campoCPF = new JTextField();
      construtor.setCpf(campoCPF);
      campoCPF.setBounds(50, 100, 200, 50);
      campoCPF.setBorder(BorderFactory.createTitledBorder("CPF do Participante"));

      JTextField campoDtNascimento = new JTextField();
      construtor.setDt_nascimento(campoDtNascimento);
      campoDtNascimento.setBounds(50, 150, 200, 50);
      campoDtNascimento.setBorder(BorderFactory.createTitledBorder("Data de Nascimento"));

      JTextField campoSexo = new JTextField();
      construtor.setSexo(campoSexo);
      campoSexo.setBounds(50, 200, 200, 50);
      campoSexo.setBorder(BorderFactory.createTitledBorder("Sexo"));

      JTextField campoInscricao = new JTextField();
      construtor.setInscricao(campoInscricao);
      campoInscricao.setBounds(50, 250, 200, 50);
      campoInscricao.setBorder(BorderFactory.createTitledBorder("Id do Evento"));

      JTextField campoId = new JTextField();
      construtor.setId(campoId);
      campoId.setBounds(290, 423, 200, 50);
      campoId.setBorder(BorderFactory.createTitledBorder("ID do Participante"));

      JButton botaoSalvar = new JButton("Salvar");
      botaoSalvar.setBounds(50, 430, 90, 40);
      botaoSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoSalvar.setForeground(Color.white);
      botaoSalvar.setBackground(Color.black);
      botaoSalvar.setBorder(BorderFactory.createEtchedBorder());
      botaoSalvar.setFocusable(false);
      botaoSalvar.addActionListener(e -> {
        participanteDao.Conexao();
        participanteDao.Inserir();
      });

      Visualizar visualizar = new Visualizar(participanteDao);
      JButton botaoVisualizar = new JButton("Visualizar");
      botaoVisualizar.setBounds(160, 430, 90, 40);
      botaoVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoVisualizar.setForeground(Color.white);
      botaoVisualizar.setBackground(Color.black);
      botaoVisualizar.setBorder(BorderFactory.createEtchedBorder());
      botaoVisualizar.setFocusable(false);
      botaoVisualizar.addActionListener(e -> {
        participanteDao.Conexao();
        participanteDao.Leitura(visualizar.getTextArea());
      });

      JButton botaoAtualizar = new JButton("Atualizar");
      botaoAtualizar.setBounds(510, 430, 90, 40);
      botaoAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
      botaoAtualizar.setForeground(Color.white);
      botaoAtualizar.setBackground(Color.black);
      botaoAtualizar.setBorder(BorderFactory.createEtchedBorder());
      botaoAtualizar.setFocusable(false);
      botaoAtualizar.addActionListener(e -> {
        participanteDao.Conexao();
        participanteDao.Atualizar();
      });

      JButton botaoExcluir = new JButton("Excluir");
      botaoExcluir.setBounds(620, 430, 90, 40);
      botaoExcluir.setFont(new Font("Arial", Font.BOLD, 14));
      botaoExcluir.setForeground(Color.white);
      botaoExcluir.setBackground(Color.black);
      botaoExcluir.setBorder(BorderFactory.createEtchedBorder());
      botaoExcluir.setFocusable(false);
      botaoExcluir.addActionListener(e -> {
        participanteDao.Conexao();
        participanteDao.Excluir();
      });
      visualizar.setBounds(290, 50, 450, 350);

      body.add(campoNome);
      body.add(campoCPF);
      body.add(campoDtNascimento);
      body.add(campoSexo);
      body.add(campoInscricao);
      body.add(campoId);
      body.add(botaoSalvar);
      body.add(botaoVisualizar);
      body.add(botaoAtualizar);
      body.add(botaoExcluir);
      body.add(visualizar);
      add(head);
      add(body);
    }
  }

  public static class Visualizar extends JPanel {
    private JTextArea textArea;

    public Visualizar(ParticipanteDao participanteDao) {
      setLayout(null);
      setBackground(Color.white);
      setBounds(290, 50, 450, 350);

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
      scrollPane.setBorder(BorderFactory.createTitledBorder("Participantes Cadastrados"));
      add(scrollPane);
    }

    public JTextArea getTextArea() {
      return textArea;
    }
  }
}