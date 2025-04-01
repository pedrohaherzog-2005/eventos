package events.Eventos.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;

public class Excluir extends Thread {
  private Construtor construtor = new Construtor();
  private Scanner scanner = new Scanner(System.in);
  private String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    System.out.print("\nInforme o id do evento que será excluído: ");
    int idEvento = scanner.nextInt();
    scanner.nextLine();

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String checkId = "SELECT COUNT(*) FROM evento WHERE id = ?";
      try (PreparedStatement checkStatement = conn.prepareStatement(checkId)) {
        checkStatement.setInt(1, idEvento);
        ResultSet rs = checkStatement.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
          conn.setAutoCommit(false);
          String sqlDelete = "DELETE FROM evento WHERE id = ?";
          try (PreparedStatement pStatement = conn.prepareStatement(sqlDelete)) {
            pStatement.setInt(1, idEvento);
            pStatement.executeUpdate();
          }
          conn.commit();
          System.out.println("Evento excluído com sucesso!");
        } else {
          System.out.println("O evento com ID " + idEvento + " não foi encontrado!");
        }
      }
    } catch (Exception e) {
      System.out.println("Erro ao excluir! " + e.getMessage());
    }
  }
}
