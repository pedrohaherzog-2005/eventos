package events.Participante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import events.Eventos.Componentes.Construtor;

public class Excluir extends Thread {
  private Construtor construtor = new Construtor();
  private Scanner scanner = new Scanner(System.in);
  private String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    System.out.print("\nInforme o id do participante que será excluído: ");
    int idParticipante = scanner.nextInt();
    scanner.nextLine();

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String checkId = "SELECT COUNT(*) FROM participante WHERE id = ?";
      try (PreparedStatement checkStatement = conn.prepareStatement(checkId)) {
        checkStatement.setInt(1, idParticipante);
        ResultSet rs = checkStatement.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
          conn.setAutoCommit(false);
          String sqlDelete = "DELETE FROM participante WHERE id = ?";
          try (PreparedStatement pStatement = conn.prepareStatement(sqlDelete)) {
            pStatement.setInt(1, idParticipante);
            pStatement.executeUpdate();
          }
          conn.commit();
          System.out.println("participante excluído com sucesso!");
        } else {
          System.out.println("O participante com ID " + idParticipante + " não foi encontrado!");
        }
      }
    } catch (Exception e) {
      System.out.println("Erro ao excluir! " + e.getMessage());
    }
  }
}