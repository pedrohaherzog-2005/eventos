package events.Eventos.Crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;

public class Inserir extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    try {
      Connection conn = DriverManager.getConnection(conexao);
      Statement statement = conn.createStatement();

      System.out.print("\nDIGITE O NOME DO EVENTO: ");
      this.construtor.setNome(scanner.next());
      scanner.nextLine();

      System.out.print("\nDIGITE A DESCRIÇÃO DO EVENTO: ");
      this.construtor.setDescricao(scanner.next());
      scanner.nextLine();

      System.out.print("\nDIGITE A DATA DO EVENTO: ");
      this.construtor.setData(scanner.next());
      scanner.nextLine();

      System.out.print("\nDIGITE O LOCAL DO EVENTO: ");
      this.construtor.setLocal(scanner.next());
      scanner.nextLine();

      System.out.print("\nDIGITE A CAPACIDADE DO EVENTO: ");
      this.construtor.setCapacidade(scanner.nextInt());
      scanner.nextLine();

      System.out.print("\nDIGITE O PALESTRANTE DO EVENTO: ");
      this.construtor.setPalestrante(scanner.next());
      scanner.nextLine();

      String insert = "INSERT INTO evento (nome, descricao, data, local, capacidade, palestrante) VALUES ('"
          + this.construtor.getNome() + "', '" + this.construtor.getDescricao() + "', '"
          + this.construtor.getData() + "', '" + this.construtor.getLocal() + "', '"
          + this.construtor.getCapacidade() + "', '" + this.construtor.getPalestrante() + "')";

      statement.execute(insert);
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nEVENTO ADICIONADO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");

    } catch (Exception e) {
      System.out.println("ERRO AO CADASTRAR EVENTOS" + e.getMessage());
    }
  }
}
