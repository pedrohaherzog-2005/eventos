package events.swing;

import javax.swing.*;
import events.dao.EventoDao;
import events.dao.propriedades.Menu.Head;
import java.awt.*;

public class EventosSwing {
  static events.dao.Contrutores.EventoConstrutor construtor = new events.dao.Contrutores.EventoConstrutor();
  static EventoDao eventoDao = new EventoDao(construtor);

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
      head.setBackground(Color.black);

      Body body = new Body();
      add(head);
      add(body);
    }
  }

  public static class Body extends JPanel {
    public Body() {
      setBounds(0, 50, 800, 550);
      setBackground(Color.white);
      setLayout(null);

      CampoNome campoNome = new CampoNome();
      add(campoNome);

      CampoDescricao campoDescricao = new CampoDescricao();
      add(campoDescricao);

      CampoData campoData = new CampoData();
      add(campoData);

      CampoLocal campoLocal = new CampoLocal();
      add(campoLocal);

      CampoCapacidade campoCapacidade = new CampoCapacidade();
      add(campoCapacidade);

      CampoPalestrante campoPalestrante = new CampoPalestrante();
      add(campoPalestrante);

      JButton botaoSalvar = new JButton("Salvar");
      botaoSalvar.setBounds(50, 450, 100, 40);
      botaoSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
      botaoSalvar.setForeground(Color.white);
      botaoSalvar.setBackground(Color.black);
      botaoSalvar.setBorder(BorderFactory.createEtchedBorder());
      botaoSalvar.setFocusable(false);
      botaoSalvar.addActionListener(e -> {
        EventoDao eventoDao = new EventoDao(construtor);
        eventoDao.Conexao();
        eventoDao.Inserir();
      });
      add(botaoSalvar);

      Visualizar visualizar = new Visualizar(eventoDao);
      BotaoVisualizar botaoVisualizar = new BotaoVisualizar();
      botaoVisualizar.addActionListener(e -> {
        eventoDao.Conexao();
        eventoDao.Leitura(visualizar.getTextArea());
      });
      add(visualizar);

      BotaoAtualizar botaoAtualizar = new BotaoAtualizar();
      botaoAtualizar.addActionListener(e -> {
        eventoDao.Conexao();
        eventoDao.Atualizar();
      });
      add(botaoAtualizar);

    }
  }

  public static class CampoNome extends JTextField {
    public CampoNome() {
      construtor.setNome(this);
      setBounds(50, 50, 200, 50);
      setBorder(BorderFactory.createTitledBorder("Nome do Evento"));
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setCaretColor(Color.black);
      setSelectionColor(Color.black);
      setSelectedTextColor(Color.white);
    }
  }

  public static class CampoDescricao extends JTextField {
    public CampoDescricao() {
      construtor.setDescricao(this);
      setBounds(50, 110, 200, 50);
      setBorder(BorderFactory.createTitledBorder("Descrição do Evento"));
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setCaretColor(Color.black);
      setSelectionColor(Color.black);
      setSelectedTextColor(Color.white);
    }
  }

  public static class CampoData extends JTextField {
    public CampoData() {
      construtor.setData(this);
      setBounds(50, 170, 200, 50);
      setBorder(BorderFactory.createTitledBorder("Data do Evento"));
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setCaretColor(Color.black);
      setSelectionColor(Color.black);
      setSelectedTextColor(Color.white);
    }
  }

  public static class CampoLocal extends JTextField {
    public CampoLocal() {
      construtor.setLocal(this);
      setBounds(50, 230, 200, 50);
      setBorder(BorderFactory.createTitledBorder("Local do Evento"));
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setCaretColor(Color.black);
      setSelectionColor(Color.black);
      setSelectedTextColor(Color.white);
    }
  }

  public static class CampoCapacidade extends JTextField {
    public CampoCapacidade() {
      construtor.setCapacidade(this);
      setBounds(50, 290, 200, 50);
      setBorder(BorderFactory.createTitledBorder("Capacidade do Evento"));
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setCaretColor(Color.black);
      setSelectionColor(Color.black);
      setSelectedTextColor(Color.white);
    }
  }

  public static class CampoPalestrante extends JTextField {
    public CampoPalestrante() {
      construtor.setPalestrante(this);
      setBounds(50, 350, 200, 50);
      setBorder(BorderFactory.createTitledBorder("Palestrante do Evento"));
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.black);
      setBackground(Color.white);
      setCaretColor(Color.black);
      setSelectionColor(Color.black);
      setSelectedTextColor(Color.white);
    }
  }

  public static class Visualizar extends JPanel {
    private JTextArea textArea;

    public Visualizar(EventoDao eventoDao) {
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
      scrollPane.setBorder(BorderFactory.createTitledBorder("Eventos Cadastrados"));

      add(scrollPane);
    }

    public JTextArea getTextArea() {
      return textArea;
    }
  }

  public static class BotaoVisualizar extends JButton {
    public BotaoVisualizar() {
      setBounds(300, 450, 100, 40);
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.white);
      setBackground(Color.black);
      setBorder(BorderFactory.createEtchedBorder());
      setText("Visualizar");
      setFocusable(false);
    }
  }

  public static class BotaoAtualizar extends JButton {
    public BotaoAtualizar() {
      setBounds(50, 450, 100, 40);
      setFont(new Font("Arial", Font.PLAIN, 14));
      setForeground(Color.white);
      setBackground(Color.black);
      setBorder(BorderFactory.createEtchedBorder());
      setText("Atualizar");
      setFocusable(false);
    }
  }
}
