package events.Eventos.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;

public class Excluir extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    System.out.print("\nInforme o id do evento que será excluido: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
    try (Connection conn = DriverManager.getConnection(conexao)) {
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
}
