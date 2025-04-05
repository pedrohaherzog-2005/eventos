package events.Eventos.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import events.Eventos.Componentes.Construtor;
import events.Interface.Crud;

public class Actions implements Crud {
  Scanner scanner = new Scanner(System.in);
  Construtor construtor = new Construtor();
  private String conexao;

  @Override
  public void Conexao() {
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
  }

  @Override
  public void Inserir() {
    System.out.print("\nDigite o nome do evento: ");
    this.construtor.setNome(scanner.nextLine());
    System.out.print("\nDigite a descrição do evento: ");
    this.construtor.setDescricao(scanner.nextLine());
    System.out.print("\nDigite a data do evento em formato yyyy/mm/dd: ");
    this.construtor.setData(scanner.nextLine());
    System.out.print("\nDigite a localização do evento: ");
    this.construtor.setLocal(scanner.next());
    System.out.print("\nDigite a capacidade do evento: ");
    this.construtor.setCapacidade(scanner.nextInt());
    System.out.print("\nDigite o id do palestrante associado ao evento: ");
    this.construtor.setPalestrante(scanner.nextInt());
    try (Connection conn = DriverManager.getConnection(this.conexao)) {
      conn.setAutoCommit(false);
      String sqlInsert = "INSERT INTO evento (nome, descricao, data, local, capacidade, palestrante) VALUES (?, ?, ?, ?, ?, ?)";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlInsert);) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getDescricao());
        pStatement.setString(3, this.construtor.getData());
        pStatement.setString(4, this.construtor.getLocal());
        pStatement.setInt(5, (int) this.construtor.getCapacidade());
        pStatement.setInt(6, (int) this.construtor.getPalestrante());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.println("Cadastrado!");
    } catch (Exception e) {
      System.out.println("Erro ao cadastrar! " + e.getMessage());
    }
  }

  @Override
  public void Atualizar() {
    System.out.println("Caso não queira atualizar o dado, escreva o mesmo valor que já está cadastrado.");
    System.out.print("\nInforme o id do evento que será atualizado: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
    System.out.print("\nInforme o novo nome do evento: ");
    this.construtor.setNome(scanner.next());
    scanner.nextLine();
    System.out.print("\nInforme a nova descrição do evento: ");
    this.construtor.setDescricao(scanner.next());
    scanner.nextLine();
    System.out.print("\nInforme a nova data do evento: ");
    this.construtor.setData(scanner.next());
    scanner.nextLine();
    System.out.print("\nInforme a nova localidade do evento: ");
    this.construtor.setLocal(scanner.next());
    scanner.nextLine();
    System.out.print("\nInforme a nova capacidade do evento: ");
    this.construtor.setCapacidade(scanner.nextInt());
    scanner.nextLine();
    System.out.print("\nInforme o novo id do palestrante associado ao evento: ");
    this.construtor.setPalestrante(scanner.nextInt());
    scanner.nextLine();
    try (Connection conn = DriverManager.getConnection(this.conexao)) {
      conn.setAutoCommit(false);
      String update = "UPDATE evento SET nome = ?, descricao = ?, data = ?, local = ?, capacidade = ?, palestrante = ? WHERE id = ?";
      try (
          PreparedStatement pStatement = conn.prepareStatement(update)) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getDescricao());
        pStatement.setString(3, this.construtor.getData());
        pStatement.setString(4, this.construtor.getLocal());
        pStatement.setInt(5, (int) this.construtor.getCapacidade());
        pStatement.setInt(6, (int) this.construtor.getPalestrante());
        pStatement.setInt(7, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.println("Atualizado!");
    } catch (Exception e) {
      System.out.println("Erro ao atualizar!" + e.getMessage());
    }
  }

  @Override
  public void Excluir() {
    System.out.print("\nInforme o id do evento que será excluido: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
    try (Connection conn = DriverManager.getConnection(this.conexao)) {
      conn.setAutoCommit(false);
      String sqlDelete = "DELETE FROM evento WHERE id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlDelete);) {
        pStatement.setInt(1, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.println("Excluido!");
    } catch (Exception e) {
      System.out.println("Erro ao excluir! " + e.getMessage());
    }
  }

  @Override
  public void Leitura() {
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement statement = conn.createStatement();
      String sqlSelect = "select e.id, e.nome, e.descricao, e.data, e.local, e.capacidade, e.palestrante from evento e";
      System.out.println("+--------------------------------------------------+");
      System.out.println("\nLISTA DE EVENTOS\n");
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        System.out.println("Id: " + rs.getInt("ID"));
        System.out.println("Nome: " + rs.getString("NOME"));
        System.out.println("Descrição: " + rs.getString("DESCRICAO"));
        System.out.println("Data: " + rs.getString("DATA"));
        System.out.println("Local: " + rs.getString("LOCAL"));
        System.out.println("Capacidade: " + rs.getInt("CAPACIDADE"));
        System.out.println("Palestrante: " + rs.getInt("PALESTRANTE"));
        System.out.println("\n\n");
      }
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.out.println("Erro ao listar eventos! " + e.getMessage());
    }
  }
}
