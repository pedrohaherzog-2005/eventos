package events.Eventos.Crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;

public class Leitura extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    try {
      Connection conn = DriverManager.getConnection(conexao);
      Statement statement = conn.createStatement();

      String sqlSelect = "select e.id, e.nome, e.descricao, e.data, e.local, e.capacidade, e.palestrante from evento e";
      System.out.println("+--------------------------------------------------+");
      System.out.println("\nLISTA DE EVENTOS\n");
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("ID"));
        System.out.println("NOME: " + rs.getString("NOME"));
        System.out.println("DESCRIÇÃO: " + rs.getString("DESCRICAO"));
        System.out.println("DATA: " + rs.getString("DATA"));
        System.out.println("LOCAL: " + rs.getString("LOCAL"));
        System.out.println("CAPACIDADE: " + rs.getInt("CAPACIDADE"));
        System.out.println("PALESTRANTE: " + rs.getInt("PALESTRANTE"));
        System.out.println("\n\n");
      }
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.out.println("ERRO AO LISTAR EVENTOS" + e.getMessage());
    }
  }
}
