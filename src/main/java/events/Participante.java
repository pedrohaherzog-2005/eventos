package events;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Participante extends Thread {
  public void run() {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\banco.bd";

    try (Connection conn = DriverManager.getConnection(conexao); Statement statement = conn.createStatement()) {
      System.out.println("Conexão estabelecida com sucesso!");

      
    } catch (Exception e) {
      System.out.println("Erro de conexão: " + e.getMessage());
    }
  }
}
