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

import events.dao.EventoDao;
import events.dao.PalestranteDao;
import events.dao.Contrutores.PalestranteConstrutor;
import events.dao.propriedades.Menu.Head;

public class PalestranteSwing {

  public static void main(String[] args) {
    PalestranteConstrutor palestranteConstrutor = new PalestranteConstrutor();
    PalestranteDao palestranteDao = new PalestranteDao(palestranteConstrutor);
    EventoDao eventoDao = new EventoDao();
    PalestranteFrame pFrame = new PalestranteFrame(palestranteDao, eventoDao);
    pFrame.setVisible(true);
  }

  public static class PalestranteFrame extends JFrame {
    public PalestranteFrame(PalestranteDao palestranteDao, EventoDao eventoDao) {
      setTitle("Área do Palestrante");
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(null);

      Head head = new Head();
      head.setBounds(0, 0, 800, 50);
      head.setBackground(Color.BLACK);

      Body body = new Body(palestranteDao, eventoDao);
      add(head);
      add(body);
    }
  }

  public static class Body extends JPanel {
    private final JTextField campoNome;
    private final JTextField campoCurriculo;
    private final JTextField campoAtuacao;
    private final JTextField campoEvento;
    private final Visualizar visualizar;

    public Body(PalestranteDao palestranteDao, EventoDao eventoDao) {
      setBounds(0, 50, 800, 550);
      setBackground(Color.WHITE);
      setLayout(null);

      campoNome = new CampoNome();
      campoCurriculo = new CampoCurriculo();
      campoAtuacao = new CampoAtuacao();
      campoEvento = new CampoEvento();

      add(campoNome);
      add(campoCurriculo);
      add(campoAtuacao);
      add(campoEvento);

      JButton botaoSalvar = new JButton("Salvar");
      botaoSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoSalvar.setForeground(Color.white);
      botaoSalvar.setBackground(Color.black);
      botaoSalvar.setBorder(BorderFactory.createEtchedBorder());
      botaoSalvar.setBounds(50, 400, 200, 50);
      botaoSalvar.addActionListener(event -> {
        // Preenche o construtor com os valores dos campos
        PalestranteConstrutor construtor = new PalestranteConstrutor();
        construtor.setNome(campoNome.getText());
        construtor.setCurriculo(campoCurriculo.getText());
        construtor.setAtuacao(campoAtuacao.getText());
        try {
          construtor.setEvento(Integer.parseInt(campoEvento.getText()));
        } catch (NumberFormatException e) {
          System.err.println("Evento deve ser um número inteiro!");
          return;
        }

        PalestranteDao novoDao = new PalestranteDao(construtor);
        novoDao.Conexao();
        novoDao.Inserir();
      });
      add(botaoSalvar);

      visualizar = new Visualizar(eventoDao);
      visualizar.setBounds(300, 50, 450, 350);
      add(visualizar);

      BotaoVisualizar botaoVisualizar = new BotaoVisualizar();
      botaoVisualizar.addActionListener(event -> {
        eventoDao.Conexao();
        visualizar.getTextArea().setText("");
        eventoDao.Leitura(visualizar.getTextArea());
      });
      add(botaoVisualizar);
    }
  }
}

class CampoNome extends JTextField {
  public CampoNome() {
    setBounds(50, 50, 200, 50);
    setBorder(BorderFactory.createTitledBorder("Nome do Palestrante"));
    setFont(new Font("Arial", Font.PLAIN, 14));
    setForeground(Color.black);
    setBackground(Color.white);
    setCaretColor(Color.black);
    setSelectionColor(Color.black);
    setSelectedTextColor(Color.white);
  }
}

class CampoCurriculo extends JTextField {
  public CampoCurriculo() {
    setBounds(50, 110, 200, 50);
    setBorder(BorderFactory.createTitledBorder("Currículo do Palestrante"));
    setFont(new Font("Arial", Font.PLAIN, 14));
    setForeground(Color.black);
    setBackground(Color.white);
    setCaretColor(Color.black);
    setSelectionColor(Color.black);
    setSelectedTextColor(Color.white);
  }
}

class CampoAtuacao extends JTextField {
  public CampoAtuacao() {
    setBounds(50, 170, 200, 50);
    setBorder(BorderFactory.createTitledBorder("Área de atuação do Palestrante"));
    setFont(new Font("Arial", Font.PLAIN, 14));
    setForeground(Color.black);
    setBackground(Color.white);
    setCaretColor(Color.black);
    setSelectionColor(Color.black);
    setSelectedTextColor(Color.white);
  }
}

class CampoEvento extends JTextField {
  public CampoEvento() {
    setBounds(50, 230, 200, 50);
    setBorder(BorderFactory.createTitledBorder("Evento vinculado ao palestrante (ID)"));
    setFont(new Font("Arial", Font.PLAIN, 14));
    setForeground(Color.black);
    setBackground(Color.white);
    setCaretColor(Color.black);
    setSelectionColor(Color.black);
    setSelectedTextColor(Color.white);
  }
}

class Visualizar extends JPanel {
  private JTextArea textArea;

  public Visualizar(EventoDao eventoDao) {
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
    scrollPane.setBorder(BorderFactory.createTitledBorder("Eventos Cadastrados"));

    add(scrollPane);
  }

  public JTextArea getTextArea() {
    return textArea;
  }
}

class BotaoVisualizar extends JButton {
  public BotaoVisualizar() {
    setBounds(300, 420, 200, 50);
    setFont(new Font("Arial", Font.PLAIN, 14));
    setForeground(Color.white);
    setBackground(Color.black);
    setBorder(BorderFactory.createEtchedBorder());
    setText("Visualizar Eventos");
  }

}