package events.Participante.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import events.Interface.Crud;
import events.Participante.Componentes.Construtor;

public class Actions implements Crud {
  Scanner scanner = new Scanner(System.in);
  Construtor construtor = new Construtor();
  private String conexao;

  @Override
  public void Conexao() {
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
  }

  @Override
  public void Inserir() {
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
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCpf());
      pStatement.setString(3, this.construtor.getDt_nascimento());
      pStatement.setString(4, this.construtor.getSexo());
      pStatement.setString(5, this.construtor.getInscricao());
      pStatement.setInt(6, (int) this.construtor.getId());
      pStatement.executeUpdate();
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nPARTICIPANTE ADICIONADO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.err.println("+--------------------------------------------------+");
      System.err.println("\n\n------ERRO AO ADICIONAR UM PARTICIPANTE------\n\n");
      System.out.println(e.getMessage());
      System.err.println("+--------------------------------------------------+");
    }
  }

  @Override
  public void Atualizar() {
    System.out.print("\nDIGITE O ID DO PARTICIPANTE: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
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
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCpf());
      pStatement.setString(3, this.construtor.getDt_nascimento());
      pStatement.setString(4, this.construtor.getSexo());
      pStatement.setString(5, this.construtor.getInscricao());
      pStatement.setInt(6, (int) this.construtor.getId());
      pStatement.executeUpdate();
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nPARTICIPANTE ATUALIZADO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }

  @Override
  public void Excluir() {
    System.out.print("\nDIGITE O ID DO PARTICIPANTE: ");
    this.construtor.setId(scanner.nextInt());
    scanner.nextLine();
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.construtor.getId());
      pStatement.executeUpdate();
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nPARTICIPANTE EXCLUIDO COM SUCESSO\n\n");
      System.out.println("+--------------------------------------------------+");
    } catch (Exception e) {
      System.out.println("Erro ao fazer conexão" + e.getMessage());
    }
  }

  @Override
  public void Leitura() {
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
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
