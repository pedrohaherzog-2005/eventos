package events.Participante;

import java.sql.Statement;
import java.util.Scanner;
import events.propriedades.Escolha;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import events.Participante.Componentes.Construtor;

public class Participante extends Thread {
  Construtor construtor = new Construtor();
  String conexao;
  Scanner scan = new Scanner(System.in);
  int escolha;

  public Participante() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    do {
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nÁREA DE PARTICIPANTE\n\n");
      System.out.println("[1] - ADICIONAR PARTICIPANTE");
      System.out.println("[2] - EDITAR PARTICIPANTE");
      System.out.println("[3] - EXCLUIR PARTICIPANTE");
      System.out.println("[4] - LISTAR PARTICIPANTE");
      System.out.println("[0] - VOLTAR");
      System.out.println("\n\nINFORME A OPERAÇÃO DESEJADA: \n\n");
      escolha = scan.nextInt();
      System.out.println("+--------------------------------------------------+");

      switch (escolha) {
        case 1:
          adicionarParticipante(scan);
          break;
        case 2:
          editarParticipante(scan.nextInt());
          break;
        case 3:
          excluirParticipante(scan.nextInt());
          break;
        case 4:
          listarParticipantes();
          break;
        case 0:
          new Escolha().start();
          break;
        default:
          System.err.println("+--------------------------------------------------+");
          System.err.println("\n\nESTA OPÇÃO NÃO EXISTE. FAVOR TENTE NOVAMENTE\n\n");
          System.err.println("+--------------------------------------------------+");
          break;
      }

    } while (escolha != 5);
  }

  private void listarParticipantes() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao);
        Statement stmt = conn.createStatement();) {
      String sqlSelect = "SELECT * FROM participante";
      ResultSet rs = stmt.executeQuery(sqlSelect);

      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nLISTA DE PARTICIPANTES\n\n");
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("ID"));
        System.out.println("Nome: " + rs.getString("NOME"));
        System.out.println("CPF: " + rs.getString("CPF"));
        System.out.println("Data de Nascimento: " + rs.getString("DT_NASCIMENTO"));
        System.out.println("Sexo: " + rs.getString("SEXO"));
        System.out.println("Inscrição: " + rs.getString("INSCRICAO"));
        System.out.println("\n\n");
      }
      System.out.println("+--------------------------------------------------+");

    } catch (Exception e) {
      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n--------ERRO AO LISTAR PARTICIPANTES--------\n\n");
      System.err.println("+--------------------------------------------------+");
    }
  }

  private void adicionarParticipante(Scanner scan) {
    System.out.print("\nDIGITE O NOME DO PARTICIPANTE: ");
    this.construtor.setNome(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O CPF DO PARTICIPANTE: ");
    this.construtor.setCpf(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE A DATA DE NASCIMENTO DO PARTICIPANTE: ");
    this.construtor.setDt_nascimento(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O SEXO DO PARTICIPANTE: ");
    this.construtor.setSexo(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O NOMERO DE INCRIÇÃO DO PARTICIPANTE: ");
    this.construtor.setInscricao(scan.next());
    scan.nextLine();

    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCpf());
      pStatement.setString(3, this.construtor.getDt_nascimento());
      pStatement.setString(4, this.construtor.getSexo());
      pStatement.setString(5, this.construtor.getInscricao());
      pStatement.setInt(6, (int) this.construtor.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {
      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n------ERRO AO ADICIONAR UM PARTICIPANTE------\n\n");
      System.out.println(e.getMessage());
      System.err.println("+--------------------------------------------------+");
    }
  }

  private void editarParticipante(int id) {
    System.out.print("\nDIGITE O ID DO EVENTO: ");
    this.construtor.setId(scan.nextInt());
    scan.nextLine();
    System.out.print("\nDIGITE O NOME DO PARTICIPANTE: ");
    this.construtor.setNome(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O CPF DO PARTICIPANTE: ");
    this.construtor.setCpf(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE A DATA DE NASCIMENTO DO PARTICIPANTE: ");
    this.construtor.setDt_nascimento(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O SEXO DO PARTICIPANTE: ");
    this.construtor.setSexo(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O NOMERO DE INCRIÇÃO DO PARTICIPANTE: ");
    this.construtor.setInscricao(scan.next());
    scan.nextLine();

    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCpf());
      pStatement.setString(3, this.construtor.getDt_nascimento());
      pStatement.setString(4, this.construtor.getSexo());
      pStatement.setString(5, this.construtor.getInscricao());
      pStatement.setInt(6, (int) this.construtor.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }

  private void excluirParticipante(int id) {
    System.out.print("\nDIGITE O ID DO PARTICIPANTE: ");
    this.construtor.setId(scan.nextInt());
    scan.nextLine();

    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.construtor.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }
}
