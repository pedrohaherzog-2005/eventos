package events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import events.dao.Contrutores.PalestranteConstrutor;

public class PalestranteDao implements Crud {

  Scanner scanner = new Scanner(System.in);
  PalestranteConstrutor construtor = new PalestranteConstrutor();
  private String conexao;

  public PalestranteDao() {}

  public PalestranteDao(PalestranteConstrutor palestranteConstrutor) {
    this.construtor = palestranteConstrutor;
  }

  @Override
  public void Conexao() {
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\banco";
  }

  @Override
  public void Inserir() {
    String nome;
    do {
      System.out.print("\nCadastre palestrante [Nome]: ");
      nome = scanner.nextLine();
      if (nome.trim().isEmpty()) {
        System.err.print("\nNome não pode ser vazio! Tente novamente.\n");
      }
    } while (nome.trim().isEmpty());
    this.construtor.setNome(nome);

    String curriculo;
    do {
      System.out.print("\nCadastre palestrante [Curriculo]: ");
      curriculo = scanner.nextLine();
      if (curriculo.trim().isEmpty()) {
        System.err.print("\nCurrículo não pode ser vazio! Tente novamente.\n");
      }
    } while (curriculo.trim().isEmpty());
    this.construtor.setCurriculo(curriculo);

    String atuacao;
    do {
      System.out.print("\nCadastre palestrante [Área atuação]: ");
      atuacao = scanner.nextLine();
      if (atuacao.trim().isEmpty()) {
        System.err.print("\nÁrea de atuação não pode ser vazia! Tente novamente.\n");
      }
    } while (atuacao.trim().isEmpty());
    this.construtor.setAtuacao(atuacao);

    int evento;
    while (true) {
      System.out.print("\nCadastre palestrante [Id evento]: ");
      try {
        if (scanner.hasNextInt()) {
          evento = scanner.nextInt();
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
    this.construtor.setEvento(evento);

    try {
      String sqlInsert = "INSERT INTO palestrante (nome, curriculo, atuacao, evento) VALUES (?, ?, ?, ?)";
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlCheckStmt = "SELECT COUNT(*) AS total FROM evento WHERE id = ?";
      PreparedStatement checkStatement = conn.prepareStatement(sqlCheckStmt);
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      checkStatement.setString(1, this.construtor.getEvento().toString());
      ResultSet rs = checkStatement.executeQuery();
      if (rs.next() && rs.getInt("total") == 0) {
        System.err.print("\nEvento não encontrado!");
        return;
      }
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCurriculo());
      pStatement.setString(3, this.construtor.getAtuacao());
      pStatement.setInt(4, this.construtor.getEvento());
      System.out.println("Resposta: " + pStatement.executeUpdate());
      pStatement.close();
      conn.close();
      System.out.print("\nCadastrado!");
    } catch (Exception e) {
      System.out.println("Erro ao cadastrar!" + e.getMessage());
      e.printStackTrace();
      System.out.println("Erro");
    }
  }

  @Override
  public void Atualizar() {
    int id;
    while (true) {
      System.out.print("\nInforme o id do palestrante: ");
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
      System.out.print("\nAtualize palestrante [Nome]: ");
      nome = scanner.nextLine();
      if (nome.trim().isEmpty()) {
        System.err.print("\nNome não pode ser vazio! Tente novamente.\n");
      }
    } while (nome.trim().isEmpty());
    this.construtor.setNome(nome);

    String curriculo;
    do {
      System.out.print("\nAtualize palestrante [Curriculo]: ");
      curriculo = scanner.nextLine();
      if (curriculo.trim().isEmpty()) {
        System.err.print("\nCurrículo não pode ser vazio! Tente novamente.\n");
      }
    } while (curriculo.trim().isEmpty());
    this.construtor.setCurriculo(curriculo);

    String atuacao;
    do {
      System.out.print("\nAtualize palestrante [Área atuação]: ");
      atuacao = scanner.nextLine();
      if (atuacao.trim().isEmpty()) {
        System.err.print("\nÁrea de atuação não pode ser vazia! Tente novamente.\n");
      }
    } while (atuacao.trim().isEmpty());
    this.construtor.setAtuacao(atuacao);

    int evento;
    while (true) {
      System.out.print("\nAtualize palestrante [Id evento]: ");
      try {
        if (scanner.hasNextInt()) {
          evento = scanner.nextInt();
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
    this.construtor.setEvento(evento);

    try {
      String sqlUpdate = "UPDATE palestrante SET nome = ?, curriculo = ?, atuacao = ?, evento = ? WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.construtor.getNome());
      pStatement.setString(2, this.construtor.getCurriculo());
      pStatement.setString(3, this.construtor.getAtuacao());
      pStatement.setInt(4, this.construtor.getEvento());
      pStatement.setInt(5, (int) this.construtor.getId());
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
      System.out.print("\nApagar palestrante [Id]: ");
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
      String sqlDelete = "DELETE FROM palestrante WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, (int) this.construtor.getId());
      System.out.println("Resposta: " + pStatement.executeUpdate());
      pStatement.close();
      conn.close();
      System.out.println("Excluido!");
    } catch (Exception e) {
      System.out.println("Erro ao excluir!" + e.getMessage());
      e.printStackTrace();
      System.out.println("Erro");
    }
  }

  @Override
  public void Leitura() {
    try {
      String sqlSelect = "select p.id, p.nome, p.curriculo, p.atuacao, p.evento from palestrante p";
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement statement = conn.createStatement();
      System.out.print("\nLista de palestrantes\n");
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        System.out.println("Id: " + rs.getInt("id"));
        System.out.println("Nome: " + rs.getString("nome"));
        System.out.println("Curriculo: " + rs.getString("curriculo"));
        System.out.println("Atuacao: " + rs.getString("atuacao"));
        System.out.println("Evento: " + rs.getString("evento"));
        System.out.println("\n");
      }
      rs.close();
      statement.close();
      conn.close();
    } catch (Exception e) {
      System.out.println("Erro ao listar palestrantes! " + e.getMessage());
      e.printStackTrace();
      System.out.println("Erro");
    }
  }
}