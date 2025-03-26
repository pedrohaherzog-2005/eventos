package events.Participante.Crud;
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
      String sqlSelect = "SELECT * FROM participante";
      ResultSet rs = stmt.executeQuery(sqlSelect);

      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nLISTA DE PARTICIPANTES\n\n");
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("ID"));
        System.out.println("Nome: " + rs.getString("NOME"));
        System.out.println("CPF: " + rs.getString("CPF"));
        System.out.println("Data de Nascimento: " + rs.getString("DT_NASCIMENTO"));
        System.out.println("Sexo: " + rs.getString("SEXO"));
        System.out.println("Inscrição: " + rs.getString("INSCRICAO"));
        System.out.println("\n\n");
      }
      System.out.println("+--------------------------------------------------+");

    } catch (Exception e) {
      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n--------ERRO AO LISTAR PARTICIPANTES--------\n\n");
      System.err.println("+--------------------------------------------------+");
    }
  }
}
