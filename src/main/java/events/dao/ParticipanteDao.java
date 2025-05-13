package events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import events.dao.Contrutores.ParticipanteConstrutor;
import events.dao.Interface.Crud;

public class ParticipanteDao implements Crud {
  Scanner scanner = new Scanner(System.in);
  ParticipanteConstrutor construtor = new ParticipanteConstrutor();
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

    try {
      String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCpf());
      pStatement.setString(3, this.construtor.getDt_nascimento());
      pStatement.setString(4, this.construtor.getSexo());
      pStatement.setString(5, this.construtor.getInscricao());
      System.out.println("Resposta: " + pStatement.executeUpdate());
      pStatement.close();
      conn.close();
      System.out.println("Cadastrado!");
    } catch (Exception e) {
      System.out.println("Erro ao cadastrar! " + e.getMessage());
      e.getStackTrace();
      System.out.println("Erro");
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

    try {
      String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCpf());
      pStatement.setString(3, this.construtor.getDt_nascimento());
      pStatement.setString(4, this.construtor.getSexo());
      pStatement.setString(5, this.construtor.getInscricao());
      pStatement.setInt(6, (int) this.construtor.getId());
      System.out.println("Resposta: " + pStatement.executeUpdate());
      pStatement.close();
      conn.close();
      System.out.println("Atualizado!");
    } catch (Exception e) {
      System.out.println("Erro ao atualizar!" + e.getMessage());
      e.printStackTrace();
      System.out.println("Erro");
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

    try {
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.construtor.getId());
      System.out.println("Resposta: " + pStatement.executeUpdate());
      pStatement.close();
      conn.close();
      System.out.println("Excluido!");
    } catch (Exception e) {
      System.out.println("Erro ao excluir! " + e.getMessage());
      e.printStackTrace();
      System.out.println("Erro");
    }
  }

  @Override
  public void Leitura() {
    try {
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement stmt = conn.createStatement();
      String sqlSelect = "SELECT ppp.id, ppp.nome, ppp.cpf, ppp.dt_nascimento, ppp.sexo, ppp.inscricao FROM participante ppp";
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
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      System.out.println("Erro ao listar participantes! " + e.getMessage());
      e.printStackTrace();
      System.out.println("Erro");
    }
  }
}