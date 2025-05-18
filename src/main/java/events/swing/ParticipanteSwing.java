// package events.swing;

// import java.awt.Color;
// import java.awt.Font;
// import javax.swing.BorderFactory;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;

// import events.Class.Participante;
// import events.dao.ParticipanteDao;
// import events.dao.propriedades.Menu.Head;
// import events.swing.PalestranteSwing.CampoEvento;

// public class ParticipanteSwing {
//   static events.dao.Construtores.ParticipanteConstrutor construtor = new events.dao.Construtores.ParticipanteConstrutor();
//     static ParticipanteDao participanteDao = new ParticipanteDao(construtor);

//     public static void main(String[] args) {
//         ParticipanteFrame pFrame = new ParticipanteFrame();
//         pFrame.setVisible(true);
//     }

//     public static class ParticipanteFrame extends JFrame {
//         public ParticipanteFrame() {
//             setTitle("Área do Participante");
//             setSize(800, 600);
//             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             setLocationRelativeTo(null);
//             setResizable(false);
//             setLayout(null);

//             Head head = new Head();
//             head.setBounds(0, 0, 800, 50);
//             head.setBackground(Color.BLACK);
//             add(head);

//             Body body = new Body();
//             add(body);
//     }

//     public static class Body extends JPanel {
//         public Body() {
//             setBounds(0, 50, 800, 550);
//             setBackground(Color.WHITE);
//             setLayout(null);

//             CampoNome campoNome = new CampoNome();
//             CampoCPF campoCPF = new CampoCPF();
//             CampoDtNascimento campoDtNascimento = new CampoDtNascimento();
//             CampoSexo campoSexo = new CampoSexo();
//             CampoInscricao campoInscricao = new CampoInscricao();

//             JButton botaoSalvar = new JButton("Salvar");
//             botaoSalvar.setBounds(50, 450, 200, 50);
//             botaoSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
//             botaoSalvar.setForeground(Color.white);
//             botaoSalvar.setBackground(Color.black);
//             botaoSalvar.setBorder(BorderFactory.createEtchedBorder());
//             botaoSalvar.setFocusable(false);
//             botaoSalvar.addActionListener(e -> {
//                 Participante participante = new Participante();
//                 (construtor;)
//                 novoParticipanteDao.Conexão();
//                 novoParticipanteDao.inserir();
//             });
//             add(botaoSalvar);

//             Visualizar visualizar = new Visualizar(participanteDao);
//             BotaoVisualizar botaoVisualizar = new BotaoVisualizar();
//             botaoVisualizar.addActionListener(e -> {
//                 participanteDao.Conexao();
//                 participanteDao.Leitura(visualizar.getTextArea());
//             });

//             add(botaoVisualizar);
//             add(visualizar);
//             add(campoNome);
//             add(campoCPF);
//             add(campoDtNascimento);
//             add(campoSexo);
//             add(campoInscricao);
//         }
//     }

//     public static class CampoNome extends JTextField {
//         public CampoNome() {
//             setBounds(50, 50, 200, 50);
//             setFont(new Font("Arial", Font.PLAIN, 14));
//             setForeground(Color.black);
//             setBackground(Color.white);
//             setBorder(BorderFactory.createTitledBorder("Nome do Participante"));
//             construtor.setNome(this);
//         }
//     }

//     public static class CampoCPF extends JTextField {
//         public CampoCPF() {
//             setBounds(50, 110, 200, 50);
//             setFont(new Font("Arial", Font.PLAIN, 14));
//             setForeground(Color.black);
//             setBackground(Color.white);
//             setBorder(BorderFactory.createTitledBorder("CPF do Participante"));
//             construtor.setCpf(this);
//         }
//     }

//     public static CampoDTNascimento extends JTextField {
//         public CampoDtNascimento() {
//             setBounds(50, 170, 200, 50);
//             setFont(new Font("Arial", Font.PLAIN, 14));
//             setForeground(Color.black);
//             setBackground(Color.white);
//             setBorder(BorderFactory.createTitledBorder("Data de Nascimento do Participante"));
//             construtor.setDt_nascimento(this);
//         }
//     }

//     public static class CampoSexo extends JTextField {
//         public CampoSexo() {
//             setBounds(50, 230, 200, 50);
//             setFont(new Font("Arial", Font.PLAIN, 14));
//             setForeground(Color.black);
//             setBackground(Color.white);
//             setBorder(BorderFactory.createTitledBorder("Sexo do Participante"));
//             construtor.setSexo(this);
//         }
//     }

//     public static class CampoInscricao extends JTextField {
//         public CampoInscricao() {
//             setBounds(50, 290, 200, 50);
//             setFont(new Font("Arial", Font.PLAIN, 14));
//             setForeground(Color.black);
//             setBackground(Color.white);
//             setBorder(BorderFactory.createTitledBorder("Inscrição do Participante"));
//             construtor.setInscricao(this);
//         }
//     }

//     public static class Visualizar extends JPanel {
//         private JTextArea textArea;

//         public Visualizar(ParticipanteDao participanteDao) {
//             setBounds(0, 0, 800, 600);
//             setBackground(Color.white);
//             setLayout(null);

//             textArea = new JTextArea();
//             textArea.setFont(new Font("Arial", Font.PLAIN, 14));
//             textArea.setForeground(Color.black);
//             textArea.setBackground(Color.white);
//             textArea.setCaretColor(Color.black);
//             textArea.setSelectionColor(Color.black);
//             textArea.setSelectedTextColor(Color.white);
//             textArea.setEditable(false);

//             JScrollPane scrollPane = new JScrollPane(textArea);
//             scrollPane.setBounds(290, 50, 450, 350);
//             scrollPane.setBorder(BorderFactory.createTitledBorder("Participantes Cadastrados"));
//             add(scrollPane);
//         }

//         public JTextArea getTextArea() {
//             return textArea;
//         }
//     }
// }
