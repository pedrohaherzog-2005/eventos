package events.Eventos.Crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;

public class Inserir extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    System.out.print("\nInforme o nome do evento: ");
    this.construtor.setNome(scanner.next());
    scanner.nextLine();
    System.out.print("Informe a descrição do evento: ");
    this.construtor.setDescricao(scanner.next());
    scanner.nextLine();
    System.out.print("Informe a data[yyyy/mm/dd] do evento: ");
    this.construtor.setData(scanner.next());
    scanner.nextLine();
    System.out.print("Informe a localização do evento: ");
    this.construtor.setLocal(scanner.next());
    scanner.nextLine();
    System.out.print("Informe a capacidade de pessoas no evento: ");
    this.construtor.setCapacidade(scanner.nextInt());
    scanner.nextLine();
    System.out.print("Informe o id do palestrante associado ao evento: ");
    this.construtor.setPalestrante(scanner.nextInt());
    scanner.nextLine();
    try (Connection conn = DriverManager.getConnection(conexao)) {
      conn.setAutoCommit(false);
      String sqlInsert = "INSERT INTO evento (nome, descricao, data, local, capacidade, palestrante) VALUES (?, ?, ?, ?, ?, ?)";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlInsert);) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getDescricao());
        pStatement.setString(3, this.construtor.getData());
        pStatement.setString(4, this.construtor.getLocal());
        pStatement.setInt(5, (int) this.construtor.getCapacidade());
        pStatement.setInt(6, (int) this.construtor.getPalestrante());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.println("Cadastrado!");
    } catch (Exception e) {
      System.out.println("Erro ao cadastrar! " + e.getMessage());
    }
  }
}
