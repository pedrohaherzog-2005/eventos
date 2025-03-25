package events.Palestrante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import events.Palestrante.Componentes.Construtor;

import events.propriedades.Escolha;

public class Palestrante extends Thread {
  Scanner scan = new Scanner(System.in);
  Construtor construtor = new Construtor();
  String conexao;

  public Palestrante() {
    int palestranteEscolha = 0;

    do {

      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nÁREA DO PALESTRANTE\n\n");
      System.out.println("[1] - ADICIONAR PALESTRANTE");
      System.out.println("[2] - EDITAR PALESTRANTE");
      System.out.println("[3] - EXCLUIR PALESTRANTE");
      System.out.println("[4] - LISTAR PALESTRANTES");
      System.out.println("[0] - VOLTAR");
      System.out.println("\n\nINFORME A OPERAÇÃO DESEJADA: \n\n");
      palestranteEscolha = scan.nextInt();
      System.out.println("+--------------------------------------------------+");

      switch (palestranteEscolha) {
        case 1:
          adicionarPalestrante(scan);
          break;
        case 2:
          editarPalestrante(scan.nextInt());
          break;
        case 3:
          excluirPalestrante(scan.nextInt());
          break;
        case 4:
          listarPalestrantes();
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

    } while (palestranteEscolha != 5);
  }

  private void listarPalestrantes() {
  conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (
        Connection conn = DriverManager.getConnection(conexao);
        Statement stmt = conn.createStatement();) {

      String sqlSelect = "SELECT p.id, p.nome, p.curriculo, p.atuacao, p.evento FROM palestrante p INNER JOIN evento e ON p.evento = e.id";
      ResultSet rs = stmt.executeQuery(sqlSelect);

      System.out.println("+--------------------------------------------------+");
      System.out.println("\nLISTA DE PALESTRANTES\n");
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("ID"));
        System.out.println("NOME: " + rs.getString("NOME"));
        System.out.println("CURRÍCULO: " + rs.getString("CURRICULO"));
        System.out.println("ÁREA DE ATUAÇÃO: " + rs.getString("ATUACAO"));
        System.out.println("ID DO EVENTO PALESTRADO: " + rs.getInt("EVENTO"));
        System.out.println("\n\n");
      }
      System.out.println("+--------------------------------------------------+");

    } catch (Exception e) {
      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n-------ERRO AO LISTAR OS PALESTRANTES-------\n\n");
      System.out.println(e.getMessage());
      System.err.println("+--------------------------------------------------+");
    }
  }

  private void adicionarPalestrante(Scanner scan) {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {

      System.out.print("\nDIGITE O NOME DO PALESTRANTE: ");
      this.construtor.setNome(scan.next());
      scan.nextLine();

      System.out.print("\nDIGITE O CURRICULO DO PALESTRANTE: ");
      this.construtor.setCurriculo(scan.next());
      scan.nextLine();

      System.out.print("\nDIGITE A ÁREA DE ATUAÇÃO DO PALESTRANTE: ");
      this.construtor.setAtuacao(scan.next());
      scan.nextLine();

      System.out.print("\nDIGITE O ID DO EVENTO QUE O PALESTRANTE IRÁ PALESTRAR: ");
      this.construtor.setEvento(scan.nextInt());
      scan.nextLine();

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

  private void editarPalestrante(int id) {
    System.out.print("\nDIGITE O ID DO PALESTRANTE: ");
    this.construtor.setId(scan.nextInt());
    scan.nextLine();

    System.out.print("\nDIGITE O NOME DO PALESTRANTE: ");
    this.construtor.setNome(scan.next());
    scan.nextLine();

    System.out.print("\nDIGITE O CURRICULO DO PALESTRANTE: ");
    this.construtor.setCurriculo(scan.next());
    scan.nextLine();

    System.out.print("\nDIGITE A ÁREA DE ATUAÇÃO DO PALESTRANTE: ");
    this.construtor.setAtuacao(scan.next());
    scan.nextLine();

    System.out.print("\nDIGITE O EVENTO QUE O PALESTRANTE IRÁ PALESTRAR: ");
    this.construtor.setEvento(scan.nextInt());
    scan.nextLine();

    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {

      String sqlUpdate = "UPDATE palestrante SET nome = ?, curriculo = ?, atuacao = ?, evento = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCurriculo());
      pStatement.setString(3, this.construtor.getAtuacao());
      pStatement.setInt(4, (int) this.construtor.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {

      System.err.println("+------------------------------------------------------+");
      System.err.println("\n\nNÃO FOI POSSIVEL EDITAR OS DADOS DO PARTICIPANTE\n\n");
      System.err.println("+------------------------------------------------------+");

    }
  }

  private void excluirPalestrante(int id) {
    System.out.print("\nDIGITE O ID DO PALESTRANTE: ");
    this.construtor.setId(scan.nextInt());
    scan.nextLine();

    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {

      String sqlDelete = "DELETE FROM palestrante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, id);
      pStatement.executeUpdate();

    } catch (Exception e) {

      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n---NÃO FOI POSSIVEL EXCLUIR O PALESTRANTE---\n\n");
      System.err.println("+--------------------------------------------------+");

    }
  }
}
