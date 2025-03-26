package events.Participante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import events.Participante.Componentes.Construtor;

public class Inserir extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  String conexao;

  @Override
  public void run() {
    conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

    System.out.print("\nDIGITE O NOME DO PARTICIPANTE: ");
    this.construtor.setNome(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE O CPF DO PARTICIPANTE: ");
    this.construtor.setCpf(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE A DATA DE NASCIMENTO DO PARTICIPANTE: ");
    this.construtor.setDt_nascimento(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE O SEXO DO PARTICIPANTE: ");
    this.construtor.setSexo(scanner.next());
    scanner.nextLine();
    System.out.print("\nDIGITE O NOMERO DE INCRIÇÃO DO PARTICIPANTE: ");
    this.construtor.setInscricao(scanner.next());
    scanner.nextLine();

    try {
      Connection conn = DriverManager.getConnection(conexao);
      
      String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCpf());
      pStatement.setString(3, this.construtor.getDt_nascimento());
      pStatement.setString(4, this.construtor.getSexo());
      pStatement.setString(5, this.construtor.getInscricao());
      pStatement.setInt(6, (int) this.construtor.getId());
      pStatement.executeUpdate();

    } catch (Exception e) {
      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n------ERRO AO ADICIONAR UM PARTICIPANTE------\n\n");
      System.out.println(e.getMessage());
      System.err.println("+--------------------------------------------------+");
    }
  }
}
