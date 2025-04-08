package events.Palestrante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import events.Palestrante.Componentes.Construtor;

public class Excluir extends Thread {
  private Construtor construtor = new Construtor();
  private Scanner scanner = new Scanner(System.in);
  private String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    System.out.print("\nInforme o id do palestrante que será excluído: ");
    int idPalestrante = scanner.nextInt();
    scanner.nextLine();

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String checkId = "SELECT COUNT(*) FROM palestrante WHERE id = ?";
      try (PreparedStatement checkStatement = conn.prepareStatement(checkId)) {
        checkStatement.setInt(1, idPalestrante);
        ResultSet rs = checkStatement.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
          conn.setAutoCommit(false);
          String sqlDelete = "DELETE FROM palestrante WHERE id = ?";
          try (PreparedStatement pStatement = conn.prepareStatement(sqlDelete)) {
            pStatement.setInt(1, idPalestrante);
            pStatement.executeUpdate();
          }
          conn.commit();
          System.out.println("palestrante excluído com sucesso!");
        } else {
          System.out.println("O palestrante com ID " + idPalestrante + " não foi encontrado!");
        }
      }
    } catch (Exception e) {
      System.out.println("Erro ao excluir! " + e.getMessage());
    }
  }
}
