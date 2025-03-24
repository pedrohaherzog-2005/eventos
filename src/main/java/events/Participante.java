package events;

import java.sql.Statement;
import java.util.Scanner;

import events.propriedades.Escolha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Participante extends Thread {
  Scanner scan = new Scanner(System.in);

  private String nome;
  private String cpf;
  private String dt_nascimento;
  private String sexo;
  private String inscricao;
  private int id;

  public void ctParticipante(String nome, String cpf, String dt_nascimento, String sexo, String inscricao, int id) {
    this.nome = nome;
    this.cpf = cpf;
    this.dt_nascimento = dt_nascimento;
    this.sexo = sexo;
    this.inscricao = inscricao;
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getDt_nascimento() {
    return dt_nascimento;
  }

  public void setDt_nascimento(String dt_nascimento) {
    this.dt_nascimento = dt_nascimento;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getInscricao() {
    return inscricao;
  }

  public void setInscricao(String inscricao) {
    this.inscricao = inscricao;
  }

  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Participante() {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao);
        Statement statement = conn.createStatement()) {
      System.out.println("Conexão estabelecida com sucesso!");

      int escolha;

      do {
        System.out.println("+--------------------------------------------------+");
        System.out.println("\nÁREA DO PARTICIPANTE\n");
        System.out.println("[1] - ADICIONAR PARTICIPANTE");
        System.out.println("[2] - EDITAR PARTICIPANTE");
        System.out.println("[3] - EXCLUIR PARTICIPANTE");
        System.out.println("[4] - LISTAR PARTICIPANTES");
        System.out.println("[0] - VOLTAR");
        System.out.println("+                      -||-                        +");
        System.out.println("\nINFORME A OPERAÇÃO DESEJADA: \n");
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

    } catch (Exception e) {
      System.out.println("Erro de conexão: " + e.getMessage());
    }
  }

  private void listarParticipantes() {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao);
        Statement stmt = conn.createStatement();) {
      String sqlSelect = "SELECT * FROM participante";
      ResultSet rs = stmt.executeQuery(sqlSelect);

      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nLISTA DE PARTICIPANTES\n\n");
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("id"));
        System.out.println("Nome: " + rs.getString("nome"));
        System.out.println("CPF: " + rs.getString("cpf"));
        System.out.println("Data de Nascimento: " + rs.getString("dt_nascimento"));
        System.out.println("Sexo: " + rs.getString("sexo"));
        System.out.println("Inscrição: " + rs.getString("inscricao"));
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
    this.setNome(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O CPF DO PARTICIPANTE: ");
    this.setCpf(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE A DATA DE NASCIMENTO DO PARTICIPANTE: ");
    this.setDt_nascimento(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O SEXO DO PARTICIPANTE: ");
    this.setSexo(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O NOMERO DE INCRIÇÃO DO PARTICIPANTE: ");
    this.setInscricao(scan.next());
    scan.nextLine();

    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      pStatement.setString(1, this.getNome());
      pStatement.setString(2, this.getCpf());
      pStatement.setString(3, this.getDt_nascimento());
      pStatement.setString(4, this.getSexo());
      pStatement.setString(5, this.getInscricao());
      pStatement.setInt(6, (int) this.getId());
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
    this.setId(scan.nextInt());
    scan.nextLine();
    System.out.print("\nDIGITE O NOME DO PARTICIPANTE: ");
    this.setNome(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O CPF DO PARTICIPANTE: ");
    this.setCpf(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE A DATA DE NASCIMENTO DO PARTICIPANTE: ");
    this.setDt_nascimento(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O SEXO DO PARTICIPANTE: ");
    this.setSexo(scan.next());
    scan.nextLine();
    System.out.print("\nDIGITE O NOMERO DE INCRIÇÃO DO PARTICIPANTE: ");
    this.setInscricao(scan.next());
    scan.nextLine();

    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.getNome());
      pStatement.setString(2, this.getCpf());
      pStatement.setString(3, this.getDt_nascimento());
      pStatement.setString(4, this.getSexo());
      pStatement.setString(5, this.getInscricao());
      pStatement.setInt(6, (int) this.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }

  private void excluirParticipante(int id) {
    System.out.print("\nDIGITE O ID DO PARTICIPANTE: ");
    this.setId(scan.nextInt());
    scan.nextLine();

    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try(Connection conn = DriverManager.getConnection(conexao)) {
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }
}
