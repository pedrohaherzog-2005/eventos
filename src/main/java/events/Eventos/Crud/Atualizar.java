package events.Eventos.Crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;

public class Atualizar extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    System.out.println("Caso não queira atualizar o dado, escreva o mesmo valor que já está cadastrado.");
    System.out.print("\nInforme o id do evento que será atualizado: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
    System.out.print("Informe o novo nome do evento: ");
    this.construtor.setNome(scanner.next());
    scanner.nextLine();
    System.out.print("Informe a nova descrição do evento: ");
    this.construtor.setDescricao(scanner.next());
    scanner.nextLine();
    System.out.print("Informe a nova data do evento: ");
    this.construtor.setData(scanner.next());
    scanner.nextLine();
    System.out.print("Informe a nova localidade do evento: ");
    this.construtor.setLocal(scanner.next());
    scanner.nextLine();
    System.out.print("Informe a nova capacidade do evento: ");
    this.construtor.setCapacidade(scanner.nextInt());
    scanner.nextLine();
    System.out.print("Informe o novo id do palestrante associado ao evento: ");
    this.construtor.setPalestrante(scanner.nextInt());
    scanner.nextLine();
    try (Connection conn = DriverManager.getConnection(conexao)) {
      conn.setAutoCommit(false);
      String update = "UPDATE evento SET nome = ?, descricao = ?, data = ?, local = ?, capacidade = ?, palestrante = ? WHERE id = ?";
      try (
        PreparedStatement pStatement = conn.prepareStatement(update)) {
          pStatement.setString(1, this.construtor.getNome());
          pStatement.setString(2, this.construtor.getDescricao());
          pStatement.setString(3, this.construtor.getData());
          pStatement.setString(4, this.construtor.getLocal());
          pStatement.setInt(5, (int) this.construtor.getCapacidade());
          pStatement.setInt(6, (int) this.construtor.getPalestrante());
          pStatement.setInt(7, (int) this.construtor.getId());
          pStatement.executeUpdate();
      }
      conn.commit();
      System.out.println("Atualizado!");
    } catch (Exception e) {
      System.out.println("Erro ao atualizar!" + e.getMessage());
    }
  }
}
