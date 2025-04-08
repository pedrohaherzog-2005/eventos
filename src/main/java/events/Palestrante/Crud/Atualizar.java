package events.Palestrante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import events.Palestrante.Componentes.Construtor;

public class Atualizar extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    System.out.println("\nCaso não queira atualizar o dado, escreva o mesmo valor que já está cadastrado.\n");
    System.out.print("\nDIGITE O ID DO PALESTRANTE QUE SERÁ ATUALIZADO: ");
    int idPalestrante = scanner.nextInt();
    scanner.nextLine();

    try (Connection conn = DriverManager.getConnection(conexao)) {

      String checkId = "SELECT COUNT(*) FROM palestrante WHERE id = ?";

      try (PreparedStatement checkStatement = conn.prepareStatement(checkId)) {
        checkStatement.setInt(1, idPalestrante);
        ResultSet rs = checkStatement.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
          System.out.print("\nDIGITE O NOME DO PALESTRANTE: ");
          this.construtor.setNome(scanner.next());
          scanner.nextLine();

          System.out.print("\nDIGITE O CURRICULO DO PALESTRANTE: ");
          this.construtor.setCurriculo(scanner.next());
          scanner.nextLine();

          System.out.print("\nDIGITE A ÁREA DE ATUAÇÃO DO PALESTRANTE: ");
          this.construtor.setAtuacao(scanner.next());
          scanner.nextLine();

          System.out.print("\nDIGITE O ID DO EVENTO QUE O PALESTRANTE IRÁ PALESTRAR: ");
          this.construtor.setEvento(scanner.nextInt());
          scanner.nextLine();

          conn.setAutoCommit(false);

          String sqlUpdate = "UPDATE palestrante SET nome = ?, curriculo = ?, atuacao = ?, evento = ? WHERE id = ?";

          try (PreparedStatement pStatement = conn.prepareStatement(sqlUpdate)) {
            pStatement.setString(1, this.construtor.getNome());
            pStatement.setString(2, this.construtor.getCurriculo());
            pStatement.setString(3, this.construtor.getAtuacao());
            pStatement.setInt(4, (int) this.construtor.getEvento());
            pStatement.setInt(5, idPalestrante);
            pStatement.executeUpdate();
          }
          conn.commit();
          System.out.println("\nPALESTRANTE ATUALIZADO COM SUCESSO\n");
        } else {
          System.out.println("O palestrante cujo id: " + idPalestrante + " não foi encontrado!");
        }
      }
    } catch (Exception e) {
      System.err.println("+------------------------------------------------------+");
      System.err.println("\nNÃO FOI POSSIVEL EDITAR OS DADOS DO PARTICIPANTE\n");
      System.out.println(e.getMessage());
      System.err.println("+------------------------------------------------------+");
    }
  }
}
