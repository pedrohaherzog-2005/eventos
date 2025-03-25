package events.Eventos.Crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;

public class Excluir extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    try {
      Connection conn = DriverManager.getConnection(conexao);
      Statement statement = conn.createStatement();
      
      System.out.print("\nDIGITE O ID DO EVENTO: ");
      this.construtor.setId(scanner.nextInt());
      scanner.nextLine();

      String delete = "DELETE FROM evento WHERE id = '" + this.construtor.getId() + "'";
      statement.execute(delete);
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nEVENTO DELETADO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.out.println("ERRO AO EXLUIR EVENTOS" + e.getMessage());
    }
  }
}
