package events.Palestrante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import events.Interface.Crud;
import events.Palestrante.Componentes.Construtor;

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
    System.out.print("\nCadastre palestrante [Nome]: ");
    this.construtor.setNome(scanner.nextLine());
    System.out.print("\nCadastre palestrante [Curriculo]: ");
    this.construtor.setCurriculo(scanner.nextLine());
    System.out.print("\nCadastre palestrante [Área atuação]: ");
    this.construtor.setAtuacao(scanner.nextLine());
    System.out.print("\nCadastre palestrante [Id evento]: ");
    this.construtor.setEvento(scanner.nextInt());
    try (Connection conn = DriverManager.getConnection(this.conexao);) {
      String sqlCheckStmt = "SELECT COUNT(*) AS total FROM evento WHERE id = ?";
      PreparedStatement checkStatement = conn.prepareStatement(sqlCheckStmt);
      checkStatement.setString(1, this.construtor.getEvento().toString());
      ResultSet rs = checkStatement.executeQuery();
      if (rs.next() && rs.getInt("total") == 0) {
        System.err.print("\nEvento não encontrado!");
        return;
      }
      conn.setAutoCommit(false);
      String sqlInsert = "INSERT INTO palestrante (nome, curriculo, atuacao, evento) VALUES (?, ?, ?, ?)";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlInsert);) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getCurriculo());
        pStatement.setString(3, this.construtor.getAtuacao());
        pStatement.setInt(4, this.construtor.getEvento());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nCadastrado!");
    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }

  @Override
  public void Atualizar() {
    System.out.print("\nInforme o id do palestrante: ");
    this.construtor.setId(scanner.nextInt());
    System.out.print("\nAtualize palestrante [Nome]: ");
    this.construtor.setNome(scanner.nextLine());
    System.out.print("\nAtualize palestrante [Curriculo]: ");
    this.construtor.setCurriculo(scanner.nextLine());
    System.out.print("\nAtualize palestrante [Área atuação]: ");
    this.construtor.setAtuacao(scanner.nextLine());
    System.out.print("\nAtualize palestrante [Id evento]: ");
    this.construtor.setEvento(scanner.nextInt());
    try (Connection conn = DriverManager.getConnection(this.conexao);) {
      conn.setAutoCommit(false);
      String sqlUpdate = "UPDATE palestrante SET nome = ?, curriculo = ?, atuacao = ?, evento = ? WHERE id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getCurriculo());
        pStatement.setString(3, this.construtor.getAtuacao());
        pStatement.setInt(4, this.construtor.getEvento());
        pStatement.setInt(5, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nAtualizado!");
    } catch (Exception e) {
      System.err.print("\nErro ao atualizar!: " + e.getMessage());
    }
  }

  @Override
  public void Excluir() {
    System.out.print("\nApagar palestrante [Id]: ");
    this.construtor.setId(scanner.nextInt());
    try (Connection conn = DriverManager.getConnection(this.conexao);) {
      conn.setAutoCommit(false);
      String sqlDelete = "DELETE FROM palestrante WHERE id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlDelete);) {
        pStatement.setInt(1, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nExcluido!");
    } catch (Exception e) {
      System.err.print("\nErro ao excluir!: " + e.getMessage());
    }
  }

  @Override
  public void Leitura() {
    try (Connection conn = DriverManager.getConnection(this.conexao);) {
      Statement statement = conn.createStatement();
      String sqlSelect = "select p.id, p.nome, p.curriculo, p.atuacao, p.evento from palestrante p";
      System.out.print("\nLista de eventos\n");
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        System.out.println("Id: " + rs.getInt("id"));
        System.out.println("Nome: " + rs.getString("nome"));
        System.out.println("Curriculo: " + rs.getString("curriculo"));
        System.out.println("Atuacao: " + rs.getString("atuacao"));
        System.out.println("Evento: " + rs.getString("evento"));
      }
    } catch (Exception e) {
      System.out.println("Erro ao listar eventos! " + e.getMessage());
    }
  }
}