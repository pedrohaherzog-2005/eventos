package events;

import java.sql.Statement;
import java.util.Scanner;

import events.propriedades.Escolha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Participante extends Thread {

  private String nome;
  private String cpf;
  private String dt_nascimento;
  private String sexo;
  private String inscricao;

  public void ctParticipante(String nome, String cpf, String dt_nascimento, String sexo, String inscricao) {
    this.nome = nome;
    this.cpf = cpf;
    this.dt_nascimento = dt_nascimento;
    this.sexo = sexo;
    this.inscricao = inscricao;
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

  public Participante() {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao);
        Statement statement = conn.createStatement();
        Scanner scan = new Scanner(System.in)) {
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
            Escolha e = new Escolha();
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
      System.err.println("+          +           -||-          +             +");
      System.err.println("\n\n--------ERRO AO LISTAR PARTICIPANTES--------\n\n");
      System.err.println("+          +           -||-          +             +");
      System.err.println("+--------------------------------------------------+");
    }
  }

  private void adicionarParticipante(Scanner scan) {
    System.out.println("Informe o nome do participante: ");
    this.setNome(scan.next());
    scan.nextLine();
    System.out.println("Informe o CPF do participante: ");
    this.setCpf(scan.next());
    scan.nextLine();
    System.out.println("Informe a data de nascimento do participante: ");
    this.setDt_nascimento(scan.next());
    scan.nextLine();
    System.out.println("Informe o sexo do participante: ");
    this.setSexo(scan.next());
    scan.nextLine();
    System.out.println("Informe o número de inscrição do participante: ");
    this.setInscricao(scan.next());
    scan.nextLine();

    if (!this.getNome().isEmpty() || !this.getCpf().isEmpty() || !this.getDt_nascimento().isEmpty()
        || !this.getSexo().isEmpty() || !this.getInscricao().isEmpty()) {

      String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\banco.bd";

      try (Connection conn = DriverManager.getConnection(conexao)) {

        String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
        pStatement.setString(1, this.getNome());
        pStatement.setString(2, this.getCpf());
        pStatement.setString(3, this.getDt_nascimento());
        pStatement.setString(4, this.getSexo());
        pStatement.setString(5, this.getInscricao());
        pStatement.executeUpdate();

      } catch (Exception e) {

        System.err.println("+--------------------------------------------------+");
        System.err.println("+          +           -||-          +             +");
        System.err.println("\n\n-----ERRO AO ADICIONAR UM PARTICIPANTE------\n\n");
        System.err.println("+          +           -||-          +             +");
        System.err.println("+--------------------------------------------------+");
      }
    } else {

      System.err.println("+--------------------------------------------------+");
      System.err.println("+          +           -||-          +             +");
      System.err.println("\n\nINFORMAÇÕES INVÁLIDAS. FAVOR TENTE NOVAMENTE\n\n");
      System.err.println("+          +           -||-          +             +");
      System.err.println("+--------------------------------------------------+");
    }
  }

  private void editarParticipante(int id) {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {

      String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.getNome());
      pStatement.setString(2, this.getCpf());
      pStatement.setString(3, this.getDt_nascimento());
      pStatement.setString(4, this.getSexo());
      pStatement.setString(5, this.getInscricao());
      pStatement.setInt(6, id);
      pStatement.executeUpdate();

    } catch (Exception e) {

      System.err.println("+--------------------------------------------------+");
      System.err.println("+          +           -||-          +             +");
      System.err.println("\n\n-ERRO AO EDITAR OS DADOS DO PARTICIPANTE----\n\n");
      System.err.println("+          +           -||-          +             +");
      System.err.println("+--------------------------------------------------+");
    }
  }

  private void excluirParticipante(int id) {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try(Connection conn = DriverManager.getConnection(conexao)) {
      
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, id);
      pStatement.executeUpdate();

    } catch (Exception e) {

      System.err.println("+--------------------------------------------------+");
      System.err.println("+          +           -||-          +             +");
      System.err.println("\n\n-----ERRO AO EXCLUIR O PARTICIPANTE---------\n\n");
      System.err.println("+          +           -||-          +             +");
      System.err.println("+--------------------------------------------------+");
    }
  }
}
