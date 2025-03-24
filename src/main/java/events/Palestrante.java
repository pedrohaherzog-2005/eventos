package events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import events.propriedades.Escolha;

public class Palestrante extends Thread {

  private String nome;
  private String curriculo;
  private String atuacao;
  private Integer evento;

  public void ctPalestrante(String nome, String curriculo, String atuacao, Integer evento) {
    this.nome = nome;
    this.curriculo = curriculo;
    this.atuacao = atuacao;
    this.evento = evento;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCurriculo() {
    return curriculo;
  }

  public void setCurriculo(String curriculo) {
    this.curriculo = curriculo;
  }

  public String getAtuacao() {
    return atuacao;
  }

  public void setAtuacao(String atuacao) {
    this.atuacao = atuacao;
  }

  public Integer getEvento() {
    return evento;
  }

  public void setEvento(Integer evento) {
    this.evento = evento;
  }

  public Palestrante() {

    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (
        Connection conn = DriverManager.getConnection(conexao);
        Statement statement = conn.createStatement();
        Scanner scan = new Scanner(System.in);) {

      int escolha;

      do {

        System.out.println("+--------------------------------------------------+");
        System.out.println("\n\nÁREA DO PALESTRANTE\n\n");
        System.out.println("[1] - ADICIONAR PALESTRANTE");
        System.out.println("[2] - EDITAR PALESTRANTE");
        System.out.println("[3] - EXCLUIR PALESTRANTE");
        System.out.println("[4] - LISTAR PALESTRANTES");
        System.out.println("[0] - VOLTAR");
        System.out.println("\n\nINFORME A OPERAÇÃO DESEJADA: \n\n");
        escolha = scan.nextInt();
        System.out.println("+--------------------------------------------------+");

        switch (escolha) {
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

      } while (escolha != 5);

    } catch (Exception e) {
      System.out.println("Erro de conexão: " + e.getMessage());
    }
  }

  private void listarPalestrantes() {

    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (
        Connection conn = DriverManager.getConnection(conexao);
        Statement stmt = conn.createStatement();) {

      String sqlSelect = "SELECT p.id, p.nome, p.curriculo, p.atuacao, p.evento, e.nome FROM palestrante p INNER JOIN evento e ON p.evento = e.id";
      ResultSet rs = stmt.executeQuery(sqlSelect);

      System.out.println("+--------------------------------------------------+");
      System.out.println("\nLISTA DE PALESTRANTES\n");
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("id"));
        System.out.println("NOME: " + rs.getString("nome"));
        System.out.println("CURRÍCULO: " + rs.getString("curriculo"));
        System.out.println("ÁREA DE ATUAÇÃO: " + rs.getString("atuacao"));
        System.out.println("EVENTO PALESTRADO: " + rs.getString("e.nome"));
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

    scan.nextLine();

    System.out.println("INFORME O NOME DO PALESTRANTE: ");
    this.setNome(scan.nextLine());
    System.out.println("INFORME O CURRICULO DO PALESTRANTE: ");
    this.setCurriculo(scan.nextLine());
    System.out.println("INFORME A ÁREA DE ATUAÇÃO DO PALESTRANTE: ");
    this.setAtuacao(scan.nextLine());
    System.out.println("INFORME O EVENTO QUE O PALESTRANTE IRÁ PALESTRAR: ");
    this.setEvento(scan.nextInt());
    scan.nextLine();

    if (!this.getNome().isEmpty() && !this.getCurriculo().isEmpty() && !this.getAtuacao().isEmpty()
        && !this.getEvento().toString().isEmpty()) {

      String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

      try (Connection conn = DriverManager.getConnection(conexao)) {

        String sqlCheckStmt = "SELECT COUNT(*) AS total FROM evento WHERE id = ?";
        PreparedStatement checkStatement = conn.prepareStatement(sqlCheckStmt);
        checkStatement.setString(1, getEvento().toString());
        ResultSet rs = checkStatement.executeQuery();

        if (rs.next() && rs.getInt("total") == 0) {
          System.err.println("+--------------------------------------------------+");
          System.err.println("\n\nEVENTO NÃO ENCONTRADO. O PROCESSO FOI INTERROMPIDO.\n\n");
          System.err.println("+--------------------------------------------------+");
          return;
        }

        String sqlInsert = "INSERT INTO palestrante (nome, curriculo, atuacao, evento) VALUES (?, ?, ?, ?)";
        PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
        pStatement.setString(1, this.getNome());
        pStatement.setString(2, this.getCurriculo());
        pStatement.setString(3, this.getAtuacao());
        pStatement.setInt(4, this.getEvento());
        pStatement.executeUpdate();

        System.out.println("+--------------------------------------------------+");
        System.out.println("\n\nPALESTRANTE ADICIONADO COM SUCESSO\n\n");
        System.out.println("+--------------------------------------------------+");

      } catch (Exception e) {

        System.err.println("+--------------------------------------------------+");
        System.err.println("\n\n------ERRO AO ADICIONAR UM PALESTRANTE------\n\n");
        System.out.println(e.getMessage());
        System.err.println("+--------------------------------------------------+");

      }
    } else {

      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n-----------DADOS NÃO PREENCHIDAS------------\n\n");
      System.err.println("+--------------------------------------------------+");

    }
  }

  private void editarPalestrante(int id) {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try (Connection conn = DriverManager.getConnection(conexao)) {
      
      String sqlUpdate = "UPDATE palestrante SET nome = ?, curriculo = ?, atuacao = ?, evento = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.getNome());
      pStatement.setString(2, this.getCurriculo());
      pStatement.setString(3, this.getAtuacao());
      pStatement.setInt(4, id);
      pStatement.executeUpdate();

    } catch (Exception e) {

      System.err.println("+------------------------------------------------------+");
      System.err.println("\n\nNÃO FOI POSSIVEL EDITAR OS DADOS DO PARTICIPANTE\n\n");
      System.err.println("+------------------------------------------------------+");

    }
  }

  private void excluirPalestrante(int id) {
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
