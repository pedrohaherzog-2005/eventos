package events.Palestrante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import events.Palestrante.Componentes.Construtor;

public class Excluir extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    System.out.print("\nDIGITE O ID DO PALESTRANTE: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();

    try (Connection conn = DriverManager.getConnection(conexao)) {

      String sqlDelete = "DELETE FROM palestrante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.construtor.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {

      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n---NÃO FOI POSSIVEL EXCLUIR O PALESTRANTE---\n\n");
      System.err.println("+--------------------------------------------------+");

    }
  }
}
