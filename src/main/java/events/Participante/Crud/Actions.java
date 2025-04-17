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
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\banco";
  }

  @Override
  public void Inserir() {
    String nome;
    do {
      System.out.print("\nCadastre participante [Nome]: ");
      nome = scanner.nextLine();
      if (nome.trim().isEmpty()) {
        System.err.print("\nNome não pode ser vazio! Tente novamente.\n");
      }
    } while (nome.trim().isEmpty());
    this.construtor.setNome(nome);

    String cpf;
    do {
      System.out.print("\nCadastre participante [Cpf]: ");
      cpf = scanner.nextLine();
      if (cpf.trim().isEmpty()) {
        System.err.print("\nCPF não pode ser vazio! Tente novamente.\n");
      }
    } while (cpf.trim().isEmpty());
    this.construtor.setCpf(cpf);

    String dt_nascimento;
    do {
      System.out.print("\nCadastre participante [Data nascimento]: ");
      dt_nascimento = scanner.nextLine();
      if (dt_nascimento.trim().isEmpty()) {
        System.err.print("\nData de nascimento não pode ser vazia! Tente novamente.\n");
      }
    } while (dt_nascimento.trim().isEmpty());
    this.construtor.setDt_nascimento(dt_nascimento);

    String sexo;
    do {
      System.out.print("\nCadastre participante [Sexo]: ");
      sexo = scanner.nextLine();
      if (sexo.trim().isEmpty()) {
        System.err.print("\nSexo não pode ser vazio! Tente novamente.\n");
      }
    } while (sexo.trim().isEmpty());
    this.construtor.setSexo(sexo);

    String inscricao;
    do {
      System.out.print("\nCadastre participante [Id evento]: ");
      inscricao = scanner.nextLine();
      if (inscricao.trim().isEmpty()) {
        System.err.print("\nID do evento não pode ser vazio! Tente novamente.\n");
      }
    } while (inscricao.trim().isEmpty());
    this.construtor.setInscricao(inscricao);

    try (Connection conn = DriverManager.getConnection(this.conexao)) {
      conn.setAutoCommit(false);
      String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlInsert);) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getCpf());
        pStatement.setString(3, this.construtor.getDt_nascimento());
        pStatement.setString(4, this.construtor.getSexo());
        pStatement.setString(5, this.construtor.getInscricao());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nCadastrado!");
    } catch (Exception e) {
      System.err.print("\nErro ao cadastrar! " + e.getMessage());
    }
  }

  @Override
  public void Atualizar() {
    int id;
    while (true) {
      System.out.print("\nInforme o id do participante: ");
      try {
        if (scanner.hasNextInt()) {
          id = scanner.nextInt();
          scanner.nextLine();
          break;
        } else {
          System.err.print("\nEntrada inválida! Insira um número.\n");
          scanner.next();
        }
      } catch (Exception e) {
        System.err.print("\nErro ao ler entrada! Tente novamente.\n");
        scanner.nextLine();
      }
    }
    this.construtor.setId(id);

    String nome;
    do {
      System.out.print("\nAtualize participante [Nome]: ");
      nome = scanner.nextLine();
      if (nome.trim().isEmpty()) {
        System.err.print("\nNome não pode ser vazio! Tente novamente.\n");
      }
    } while (nome.trim().isEmpty());
    this.construtor.setNome(nome);

    String cpf;
    do {
      System.out.print("\nAtualize participante [Cpf]: ");
      cpf = scanner.nextLine();
      if (cpf.trim().isEmpty()) {
        System.err.print("\nCPF não pode ser vazio! Tente novamente.\n");
      }
    } while (cpf.trim().isEmpty());
    this.construtor.setCpf(cpf);

    String dt_nascimento;
    do {
      System.out.print("\nAtualize participante [Data nascimento]: ");
      dt_nascimento = scanner.nextLine();
      if (dt_nascimento.trim().isEmpty()) {
        System.err.print("\nData de nascimento não pode ser vazia! Tente novamente.\n");
      }
    } while (dt_nascimento.trim().isEmpty());
    this.construtor.setDt_nascimento(dt_nascimento);

    String sexo;
    do {
      System.out.print("\nAtualize participante [Sexo]: ");
      sexo = scanner.nextLine();
      if (sexo.trim().isEmpty()) {
        System.err.print("\nSexo não pode ser vazio! Tente novamente.\n");
      }
    } while (sexo.trim().isEmpty());
    this.construtor.setSexo(sexo);

    String inscricao;
    do {
      System.out.print("\nAtualize participante [Id evento]: ");
      inscricao = scanner.nextLine();
      if (inscricao.trim().isEmpty()) {
        System.err.print("\nID do evento não pode ser vazio! Tente novamente.\n");
      }
    } while (inscricao.trim().isEmpty());
    this.construtor.setInscricao(inscricao);

    try (Connection conn = DriverManager.getConnection(this.conexao)) {
      conn.setAutoCommit(false);
      String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);) {
        pStatement.setString(1, this.construtor.getNome());
        pStatement.setString(2, this.construtor.getCpf());
        pStatement.setString(3, this.construtor.getDt_nascimento());
        pStatement.setString(4, this.construtor.getSexo());
        pStatement.setString(5, this.construtor.getInscricao());
        pStatement.setInt(6, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nAtualizado!");
    } catch (Exception e) {
      System.out.println("Erro ao atualizar!" + e.getMessage());
    }
  }

  @Override
  public void Excluir() {
    int id;
    while (true) {
      System.out.print("\nApagar participante [Id]: ");
      try {
        if (scanner.hasNextInt()) {
          id = scanner.nextInt();
          scanner.nextLine();
          break;
        } else {
          System.err.print("\nEntrada inválida! Insira um número.\n");
          scanner.next();
        }
      } catch (Exception e) {
        System.err.print("\nErro ao ler entrada! Tente novamente.\n");
        scanner.nextLine();
      }
    }
    this.construtor.setId(id);

    try (Connection conn = DriverManager.getConnection(this.conexao);) {
      conn.setAutoCommit(false);
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlDelete);) {
        pStatement.setInt(1, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.print("\nExcluido!");
    } catch (Exception e) {
      System.out.println("Erro ao excluir!" + e.getMessage());
    }
  }

  @Override
  public void Leitura() {
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement stmt = conn.createStatement();
      String sqlSelect = "SELECT * FROM participante";
      ResultSet rs = stmt.executeQuery(sqlSelect);
      System.out.print("\nLista de participantes");
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("ID"));
        System.out.println("Nome: " + rs.getString("NOME"));
        System.out.println("CPF: " + rs.getString("CPF"));
        System.out.println("Data de Nascimento: " + rs.getString("DT_NASCIMENTO"));
        System.out.println("Sexo: " + rs.getString("SEXO"));
        System.out.println("Inscrição: " + rs.getString("INSCRICAO"));
        System.out.println("\n");
      }
    } catch (Exception e) {
      System.err.print("\nErro ao listar! " + e.getMessage());
    }
  }
}