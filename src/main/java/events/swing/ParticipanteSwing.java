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
import events.dao.ParticipanteDao;
import events.dao.propriedades.Menu.Head;
import events.dao.Contrutores.ParticipanteConstrutor;

public class ParticipanteSwing {
    static ParticipanteConstrutor construtor = new ParticipanteConstrutor();
    static ParticipanteDao participanteDao = new ParticipanteDao(construtor);

    public static void main(String[] args) {
        ParticipanteFrame pFrame = new ParticipanteFrame();
        pFrame.setVisible(true);
    }

    public static class ParticipanteFrame extends JFrame {
        public ParticipanteFrame() {
            setTitle("Área do Participante");
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
            add(campoNome);

            CampoCPF campoCPF = new CampoCPF();
            add(campoCPF);

            CampoDtNascimento campoDtNascimento = new CampoDtNascimento();
            add(campoDtNascimento);

            CampoSexo campoSexo = new CampoSexo();
            add(campoSexo);

            CampoInscricao campoInscricao = new CampoInscricao();
            add(campoInscricao);

            JButton botaoSalvar = new JButton("Salvar");
            botaoSalvar.setBounds(50, 450, 100, 40);
            botaoSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
            botaoSalvar.setForeground(Color.white);
            botaoSalvar.setBackground(Color.black);
            botaoSalvar.setBorder(BorderFactory.createEtchedBorder());
            botaoSalvar.setFocusable(false);
            botaoSalvar.addActionListener(e -> {
                ParticipanteDao participanteDao = new ParticipanteDao(construtor);
                participanteDao.Conexao();
                participanteDao.Inserir();
            });
            add(botaoSalvar);

            Visualizar visualizar = new Visualizar(participanteDao);
            BotaoVisualizar botaoVisualizar = new BotaoVisualizar();
            botaoVisualizar.addActionListener(e -> {
                participanteDao.Conexao();
                participanteDao.Leitura(visualizar.getTextArea());
            });
            add(visualizar);
        }
    }

    public static class CampoNome extends JTextField {
        public CampoNome() {
            setBounds(50, 50, 200, 50);
            setFont(new Font("Arial", Font.PLAIN, 14));
            setForeground(Color.black);
            setBackground(Color.white);
            setBorder(BorderFactory.createTitledBorder("Nome do Participante"));
            construtor.setNome(this);
        }
    }

    public static class CampoCPF extends JTextField {
        public CampoCPF() {
            setBounds(50, 110, 200, 50);
            setFont(new Font("Arial", Font.PLAIN, 14));
            setForeground(Color.black);
            setBackground(Color.white);
            setBorder(BorderFactory.createTitledBorder("CPF do Participante"));
            construtor.setCpf(this);
        }
    }

    public static class CampoDtNascimento extends JTextField {
        public CampoDtNascimento() {
            setBounds(50, 170, 200, 50);
            setFont(new Font("Arial", Font.PLAIN, 14));
            setForeground(Color.black);
            setBackground(Color.white);
            setBorder(BorderFactory.createTitledBorder("Data de Nascimento do Participante"));
            construtor.setDt_nascimento(this);
        }
    }

    public static class CampoSexo extends JTextField {
        public CampoSexo() {
            setBounds(50, 230, 200, 50);
            setFont(new Font("Arial", Font.PLAIN, 14));
            setForeground(Color.black);
            setBackground(Color.white);
            setBorder(BorderFactory.createTitledBorder("Sexo do Participante"));
            construtor.setSexo(this);
        }
    }

    public static class CampoInscricao extends JTextField {
        public CampoInscricao() {
            setBounds(50, 290, 200, 50);
            setFont(new Font("Arial", Font.PLAIN, 14));
            setForeground(Color.black);
            setBackground(Color.white);
            setBorder(BorderFactory.createTitledBorder("Inscrição do Participante"));
            construtor.setInscricao(this);
        }
    }

    public static class Visualizar extends JPanel {
        private JTextArea textArea;

        public Visualizar(ParticipanteDao participanteDao) {
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
            scrollPane.setBorder(BorderFactory.createTitledBorder("Participantes Cadastrados"));
            add(scrollPane);
        }

        public JTextArea getTextArea() {
            return textArea;
        }
    }

    // Adicione esta classe se não existir
    public static class BotaoVisualizar extends JButton {
        public BotaoVisualizar() {
            super("Visualizar");
            setBounds(50, 370, 200, 50);
            setFont(new Font("Arial", Font.PLAIN, 14));
            setForeground(Color.white);
            setBackground(Color.black);
            setBorder(BorderFactory.createEtchedBorder());
            setFocusable(false);
        }
    }
}