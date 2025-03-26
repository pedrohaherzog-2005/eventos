package events.Palestrante.Crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Leitura extends Thread {
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    try {
      Connection conn = DriverManager.getConnection(conexao);
      Statement stmt = conn.createStatement();

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

}
