package events.Palestrante.Crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import events.Palestrante.Componentes.Construtor;

public class Atualizar  extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run () {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    System.out.print("\nDIGITE O ID DO PALESTRANTE: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();

    System.out.print("\nDIGITE O NOME DO PALESTRANTE: ");
    this.construtor.setNome(scanner.next());
    scanner.nextLine();

    System.out.print("\nDIGITE O CURRICULO DO PALESTRANTE: ");
    this.construtor.setCurriculo(scanner.next());
    scanner.nextLine();

    System.out.print("\nDIGITE A ÁREA DE ATUAÇÃO DO PALESTRANTE: ");
    this.construtor.setAtuacao(scanner.next());
    scanner.nextLine();

    System.out.print("\nDIGITE O EVENTO QUE O PALESTRANTE IRÁ PALESTRAR: ");
    this.construtor.setEvento(scanner.nextInt());
    scanner.nextLine();

    try (Connection conn = DriverManager.getConnection(conexao)) {

      String sqlUpdate = "UPDATE palestrante SET nome = ?, curriculo = ?, atuacao = ?, evento = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCurriculo());
      pStatement.setString(3, this.construtor.getAtuacao());
      pStatement.setInt(4, (int) this.construtor.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {

      System.err.println("+------------------------------------------------------+");
      System.err.println("\n\nNÃO FOI POSSIVEL EDITAR OS DADOS DO PARTICIPANTE\n\n");
      System.err.println("+------------------------------------------------------+");

    }
  }
}
