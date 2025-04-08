package events.Participante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import events.Interface.Crud;
import events.Participante.Componentes.Construtor;

public class Actions implements Crud {
  Scanner scanner = new Scanner(System.in);
  Construtor construtor = new Construtor();
  private String conexao;

  @Override
  public void Conexao() {
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\banco";
  }

  @Override
  public void Inserir() {
    System.out.print("\nCadastre participante [Nome]: ");
    this.construtor.setNome(scanner.nextLine());
    System.out.print("\nCadastre participante [Cpf]: ");
    this.construtor.setCpf(scanner.nextLine());
    System.out.print("\nCadastre participante [Data nascimento]: ");
    this.construtor.setDt_nascimento(scanner.nextLine());
    System.out.print("\nCadastre participante [Sexo]: ");
    this.construtor.setSexo(scanner.nextLine());
    System.out.print("\nCadastre participante [Id evento]: ");
    this.construtor.setInscricao(scanner.nextLine());
    try (Connection conn = DriverManager.getConnection(this.conexao)) {
      conn.setAutoCommit(false);
      String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlInsert);) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getCpf());
        pStatement.setString(3, this.construtor.getDt_nascimento());
        pStatement.setString(4, this.construtor.getSexo());
        pStatement.setString(5, this.construtor.getInscricao());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nCadastrado!");
    } catch (Exception e) {
      System.err.print("\nErro ao cadastrar! " + e.getMessage());
    }
  }

  @Override
  public void Atualizar() {
    System.out.print("\nInforme o id do participante: ");
    this.construtor.setId(scanner.nextInt());
    System.out.print("\nAtualize participante [Nome]: ");
    this.construtor.setNome(scanner.nextLine());
    System.out.print("\nAtualize participante [Cpf]: ");
    this.construtor.setCpf(scanner.nextLine());
    System.out.print("\nAtualize participante [Data nascimento]: ");
    this.construtor.setDt_nascimento(scanner.nextLine());
    System.out.print("\nAtualize participante [Sexo]: ");
    this.construtor.setSexo(scanner.nextLine());
    System.out.print("\nAtualize participante [Id evento]: ");
    this.construtor.setInscricao(scanner.nextLine());
    try (Connection conn = DriverManager.getConnection(this.conexao)) {
      conn.setAutoCommit(false);
      String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getCpf());
        pStatement.setString(3, this.construtor.getDt_nascimento());
        pStatement.setString(4, this.construtor.getSexo());
        pStatement.setString(5, this.construtor.getInscricao());
        pStatement.setInt(6, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nAtualizado!");
    } catch (Exception e) {
      System.out.println("Erro ao atualizar!" + e.getMessage());
    }
  }

  @Override
  public void Excluir() {
    System.out.print("\nApagar participante [Id]: ");
    this.construtor.setId(scanner.nextInt());
    try (Connection conn = DriverManager.getConnection(this.conexao);) {
      conn.setAutoCommit(false); 
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlDelete);) {
        pStatement.setInt(1, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nExcluido!");
    } catch (Exception e) {
      System.out.println("Erro ao excluir!" + e.getMessage());
    }
  }

  @Override
  public void Leitura() {
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement stmt = conn.createStatement();
      String sqlSelect = "SELECT * FROM participante";
      ResultSet rs = stmt.executeQuery(sqlSelect);
      System.out.print("\nLista de participantes");
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("ID"));
        System.out.println("Nome: " + rs.getString("NOME"));
        System.out.println("CPF: " + rs.getString("CPF"));
        System.out.println("Data de Nascimento: " + rs.getString("DT_NASCIMENTO"));
        System.out.println("Sexo: " + rs.getString("SEXO"));
        System.out.println("Inscrição: " + rs.getString("INSCRICAO"));
        System.out.println("\n\n");
      }
    } catch (Exception e) {
      System.err.print("\nErro ao listar! " + e.getMessage());
    }
  }
}
