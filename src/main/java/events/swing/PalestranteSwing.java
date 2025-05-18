package events.swing;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import events.dao.PalestranteDao;
import events.dao.propriedades.Menu.Head;

public class PalestranteSwing {
  static events.dao.contrutores.PalestranteConstrutor construtor = new events.dao.contrutores.PalestranteConstrutor();
  static PalestranteDao palestranteDao = new PalestranteDao(construtor);

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
      add(head);

      Body body = new Body();
      add(body);
    }
  }

  public static class Body extends JPanel {
    public Body() {
      setBounds(0, 50, 800, 550);
      setBackground(Color.WHITE);
      setLayout(null);

      CampoNome campoNome = new CampoNome();
      CampoCurriculo campoCurriculo = new CampoCurriculo();
      CampoAtuacao campoAtuacao = new CampoAtuacao();
      CampoEvento campoEvento = new CampoEvento();

      JButton botaoSalvar = new JButton("Salvar");
      botaoSalvar.setBounds(50, 450, 200, 50);
      botaoSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoSalvar.setForeground(Color.white);
      botaoSalvar.setBackground(Color.black);
      botaoSalvar.setBorder(BorderFactory.createEtchedBorder());
      botaoSalvar.setFocusable(false);
      botaoSalvar.addActionListener(e -> {
        PalestranteDao novoPalestranteDao = new PalestranteDao(construtor);
        novoPalestranteDao.Conexao();
        novoPalestranteDao.Inserir();
      });
      add(botaoSalvar);

      Visualizar visualizar = new Visualizar(palestranteDao);
      BotaoVisualizar botaoVisualizar = new BotaoVisualizar();
      botaoVisualizar.addActionListener(e -> {
        palestranteDao.Conexao();
        palestranteDao.Leitura(visualizar.getTextArea());
      });

      add(botaoVisualizar);
      add(visualizar);
      add(campoNome);
      add(campoCurriculo);
      add(campoAtuacao);
      add(campoEvento);
    }
  }

  public static class CampoNome extends JTextField {
    public CampoNome() {
      setBounds(50, 50, 200, 50);
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setBorder(BorderFactory.createTitledBorder("Nome do Palestrante"));
      construtor.setNome(this);
    }
  }

  public static class CampoCurriculo extends JTextField {
    public CampoCurriculo() {
      setBounds(50, 110, 200, 50);
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setBorder(BorderFactory.createTitledBorder("Currículo do Palestrante"));
      construtor.setCurriculo(this);
    }
  }

  public static class CampoAtuacao extends JTextField {
    public CampoAtuacao() {
      setBounds(50, 170, 200, 50);
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setBorder(BorderFactory.createTitledBorder("Área de Atuação do Palestrante"));
      construtor.setAtuacao(this);
    }
  }

  public static class CampoEvento extends JTextField {
    public CampoEvento() {
      setBounds(50, 230, 200, 50);
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setBorder(BorderFactory.createTitledBorder("Evento Vinculado ao Palestrante"));
      construtor.setEvento(this);
    }
  }

  public static class BotaoVisualizar extends JButton {
    public BotaoVisualizar() {
      setBounds(300, 450, 200, 50);
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.white);
      setBackground(Color.black);
      setBorder(BorderFactory.createEtchedBorder());
      setText("Visualizar Palestrantes");
      setFocusable(false);
    }
  }

  public static class Visualizar extends JPanel {
    private JTextArea textArea;

    public Visualizar(PalestranteDao palestranteDao) {
      setBounds(0, 0, 800, 600);
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
      scrollPane.setBounds(290, 50, 450, 350);
      scrollPane.setBorder(BorderFactory.createTitledBorder("Palestrantes Cadastrados"));

      add(scrollPane);
    }

    public JTextArea getTextArea() {
      return textArea;
    }
  }
}