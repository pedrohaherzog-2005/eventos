package events.Palestrante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import events.Interface.Crud;
import events.Palestrante.Componentes.Construtor;

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
    System.out.print("\nDIGITE O NOME DO PALESTRANTE: ");
    this.construtor.setNome(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE O CURRICULO DO PALESTRANTE: ");
    this.construtor.setCurriculo(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE A ÁREA DE ATUAÇÃO DO PALESTRANTE: ");
    this.construtor.setAtuacao(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE O ID DO EVENTO QUE O PALESTRANTE IRÁ PALESTRAR: ");
    this.construtor.setEvento(scanner.nextInt());
    scanner.nextLine();
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlCheckStmt = "SELECT COUNT(*) AS total FROM evento WHERE id = ?";
      PreparedStatement checkStatement = conn.prepareStatement(sqlCheckStmt);
      checkStatement.setString(1, this.construtor.getEvento().toString());
      ResultSet rs = checkStatement.executeQuery();
      if (rs.next() && rs.getInt("total") == 0) {
        System.err.println("+--------------------------------------------------+");
        System.err.println("\n\nEVENTO NÃO ENCONTRADO. O PROCESSO FOI INTERROMPIDO.\n\n");
        System.err.println("+--------------------------------------------------+");
        return;
      }
      String sqlInsert = "INSERT INTO palestrante (nome, curriculo, atuacao, evento) VALUES (?, ?, ?, ?)";
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCurriculo());
      pStatement.setString(3, this.construtor.getAtuacao());
      pStatement.setInt(4, this.construtor.getEvento());
      pStatement.executeUpdate();
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nPALESTRANTE ADICIONADO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }

  @Override
  public void Atualizar() {
    System.out.print("\nDIGITE O ID DO PALESTRANTE: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
    System.out.print("\nDIGITE O NOME DO PALESTRANTE: ");
    this.construtor.setNome(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE O CURRICULO DO PALESTRANTE: ");
    this.construtor.setCurriculo(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE A ÁREA DE ATUAÇÃO DO PALESTRANTE: ");
    this.construtor.setAtuacao(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE O ID DO EVENTO QUE O PALESTRANTE IRÁ PALESTRAR: ");
    this.construtor.setEvento(scanner.nextInt());
    scanner.nextLine();
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlUpdate = "UPDATE palestrante SET nome = ?, curriculo = ?, atuacao = ?, evento = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCurriculo());
      pStatement.setString(3, this.construtor.getAtuacao());
      pStatement.setInt(4, (int) this.construtor.getEvento());
      pStatement.setInt(5, (int) this.construtor.getId());
      pStatement.executeUpdate();
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nPALESTRANTE ATUALIZADO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.err.println("+------------------------------------------------------+");
      System.err.println("\n\nNÃO FOI POSSIVEL EDITAR OS DADOS DO PARTICIPANTE\n\n");
      System.err.println("+------------------------------------------------------+");
    }
  }

  @Override
  public void Excluir() {
    System.out.print("\nDIGITE O ID DO PALESTRANTE: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlDelete = "DELETE FROM palestrante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.construtor.getId());
      pStatement.executeUpdate();
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nPALESTRANTE EXCLUIDO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n---NÃO FOI POSSIVEL EXCLUIR O PALESTRANTE---\n\n");
      System.err.println("+--------------------------------------------------+");
    }
  }

  @Override
  public void Leitura() {
    System.out.print("\nDIGITE O ID DO PALESTRANTE: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlDelete = "DELETE FROM palestrante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.construtor.getId());
      pStatement.executeUpdate();
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nPALESTRANTE EXCLUIDO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n---NÃO FOI POSSIVEL EXCLUIR O PALESTRANTE---\n\n");
      System.err.println("+--------------------------------------------------+");
    }
  }
}