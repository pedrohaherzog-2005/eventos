package events.Participante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import events.Participante.Componentes.Construtor;

public class Atualizar extends Thread {
  private Construtor construtor = new Construtor();
  private Scanner scanner = new Scanner(System.in);
  private String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    System.out.println("\nCaso não queira atualizar o dado, escreva o mesmo valor que já está cadastrado.\n");

    System.out.print("\nDIGITE O ID DO PARTICIPANTE QUE SERÁ ATUALIZADO: ");
    int idParticipante = scanner.nextInt();
    scanner.nextLine();

    try (Connection conn = DriverManager.getConnection(conexao)) {
      String checkId = "SELECT COUNT(*) FROM participante WHERE id = ?";
      try (PreparedStatement checkStatement = conn.prepareStatement(checkId)) {
        checkStatement.setInt(1, idParticipante);
        ResultSet rs = checkStatement.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
          System.out.print("\nDIGITE O NOME DO PARTICIPANTE: ");
          construtor.setNome(scanner.nextLine());

          System.out.print("\nDIGITE O CPF DO PARTICIPANTE: ");
          construtor.setCpf(scanner.nextLine());

          System.out.print("\nDIGITE A DATA DE NASCIMENTO DO PARTICIPANTE: ");
          construtor.setDt_nascimento(scanner.nextLine());

          System.out.print("\nDIGITE O SEXO DO PARTICIPANTE: ");
          construtor.setSexo(scanner.nextLine());

          System.out.print("\nDIGITE O NÚMERO DE INSCRIÇÃO DO PARTICIPANTE: ");
          construtor.setInscricao(scanner.nextLine());

          conn.setAutoCommit(false);

          String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
          try (PreparedStatement pStatement = conn.prepareStatement(sqlUpdate)) {
            pStatement.setString(1, construtor.getNome());
            pStatement.setString(2, construtor.getCpf());
            pStatement.setString(3, construtor.getDt_nascimento());
            pStatement.setString(4, construtor.getSexo());
            pStatement.setString(5, construtor.getInscricao());
            pStatement.setInt(6, idParticipante);
            pStatement.executeUpdate();
          }
          conn.commit();
          System.out.println("\nPARTICIPANTE ATUALIZADO COM SUCESSO\n");
        } else {
          System.out.println("O participante cujo id: " + idParticipante + " não foi encontrado!");
        }
      }
    } catch (Exception e) {
      System.err.println("+------------------------------------------------------+\n");
      System.err.println("NÃO FOI POSSÍVEL EDITAR OS DADOS DO PARTICIPANTE\n");
      System.err.println("+------------------------------------------------------+\n");
    }
  }
}
