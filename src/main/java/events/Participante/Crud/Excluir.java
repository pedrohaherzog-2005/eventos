package events.Participante.Crud;

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

    System.out.print("\nDIGITE O ID DO PARTICIPANTE: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();

    try {
      Connection conn = DriverManager.getConnection(conexao);
      
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.construtor.getId());
      pStatement.executeUpdate();
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nPARTICIPANTE EXCLUIDO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }
}
