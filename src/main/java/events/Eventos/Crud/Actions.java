package events.Eventos.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import events.Eventos.Componentes.Construtor;
import events.Interface.Crud;

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
      System.out.print("\nCadastre evento [Nome]: ");
      nome = scanner.nextLine();
      if (nome == null || nome.trim().isEmpty()) {
        System.err.print("\nNome não pode ser vazio! Tente novamente.\n");
      }
    } while (nome == null || nome.trim().isEmpty());
    this.construtor.setNome(nome);

    String descricao;
    do {
      System.out.print("\nCadastre evento [Descrição]: ");
      descricao = scanner.nextLine();
      if (descricao == null || descricao.trim().isEmpty()) {
        System.err.print("\nDescrição não pode ser vazia! Tente novamente.\n");
      }
    } while (descricao == null || descricao.trim().isEmpty());
    this.construtor.setDescricao(descricao);

    String data;
    do {
      System.out.print("\nCadastre evento [Data]: ");
      data = scanner.nextLine();
      if (data == null || data.trim().isEmpty()) {
        System.err.print("\nData não pode ser vazia! Tente novamente.\n");
      }
    } while (data == null || data.trim().isEmpty());
    this.construtor.setData(data);

    String local;
    do {
      System.out.print("\nCadastre evento [Local]: ");
      local = scanner.nextLine();
      if (local == null || local.trim().isEmpty()) {
        System.err.print("\nLocal não pode ser vazio! Tente novamente.\n");
      }
    } while (local == null || local.trim().isEmpty());
    this.construtor.setLocal(local);

    int capacidade;
    while (true) {
      System.out.print("\nCadastre evento [Capacidade]: ");
      try {
        if (scanner.hasNextInt()) {
          capacidade = scanner.nextInt();
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
    this.construtor.setCapacidade(capacidade);

    int palestrante;
    while (true) {
      System.out.print("\nCadastre evento [Id palestrante]: ");
      try {
        if (scanner.hasNextInt()) {
          palestrante = scanner.nextInt();
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
    this.construtor.setPalestrante(palestrante);

    try (Connection conn = DriverManager.getConnection(this.conexao)) {
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

  @Override
  public void Atualizar() {
    int id;
    while (true) {
      System.out.print("\nInforme o id do evento: ");
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
      System.out.print("\nAtualize evento [Nome]: ");
      nome = scanner.nextLine();
      if (nome == null || nome.trim().isEmpty()) {
        System.err.print("\nNome não pode ser vazio! Tente novamente.\n");
      }
    } while (nome == null || nome.trim().isEmpty());
    this.construtor.setNome(nome);

    String descricao;
    do {
      System.out.print("\nAtualize evento [Descrição]: ");
      descricao = scanner.nextLine();
      if (descricao == null || descricao.trim().isEmpty()) {
        System.err.print("\nDescrição não pode ser vazia! Tente novamente.\n");
      }
    } while (descricao == null || descricao.trim().isEmpty());
    this.construtor.setDescricao(descricao);

    String data;
    do {
      System.out.print("\nAtualize evento [Data]: ");
      data = scanner.nextLine();
      if (data == null || data.trim().isEmpty()) {
        System.err.print("\nData não pode ser vazia! Tente novamente.\n");
      }
    } while (data == null || data.trim().isEmpty());
    this.construtor.setData(data);

    String local;
    do {
      System.out.print("\nAtualize evento [Local]: ");
      local = scanner.nextLine();
      if (local == null || local.trim().isEmpty()) {
        System.err.print("\nLocal não pode ser vazio! Tente novamente.\n");
      }
    } while (local == null || local.trim().isEmpty());
    this.construtor.setLocal(local);

    int capacidade;
    while (true) {
      System.out.print("\nAtualize evento [Capacidade]: ");
      try {
        if (scanner.hasNextInt()) {
          capacidade = scanner.nextInt();
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
    this.construtor.setCapacidade(capacidade);

    int palestrante;
    while (true) {
      System.out.print("\nAtualize evento [Id palestrante]: ");
      try {
        if (scanner.hasNextInt()) {
          palestrante = scanner.nextInt();
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
    this.construtor.setPalestrante(palestrante);

    try (Connection conn = DriverManager.getConnection(this.conexao)) {
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

  @Override
  public void Excluir() {
    int id;
    while (true) {
      System.out.print("\nApagar evento [Id]: ");
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

    try (Connection conn = DriverManager.getConnection(this.conexao)) {
      conn.setAutoCommit(false);
      String sqlDelete = "DELETE FROM evento WHERE id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(sqlDelete);) {
        pStatement.setInt(1, (int) this.construtor.getId());
        pStatement.executeUpdate();
      }
      conn.commit();
      System.out.println("Excluido!");
    } catch (Exception e) {
      System.out.println("Erro ao excluir! " + e.getMessage());
    }
  }

  @Override
  public void Leitura() {
    try (Connection conn = DriverManager.getConnection(this.conexao);) {
      Statement statement = conn.createStatement();
      String sqlSelect = "select e.id, e.nome, e.descricao, e.data, e.local, e.capacidade, e.palestrante from evento e";
      System.out.print("\nLista de eventos\n");
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        System.out.println("Id: " + rs.getInt("id"));
        System.out.println("Nome: " + rs.getString("nome"));
        System.out.println("Descrição: " + rs.getString("descricao"));
        System.out.println("Data: " + rs.getString("data"));
        System.out.println("Local: " + rs.getString("local"));
        System.out.println("Capacidade: " + rs.getInt("capacidade"));
        System.out.println("Palestrante: " + rs.getInt("palestrante"));
        System.out.println("\n");
      }
    } catch (Exception e) {
      System.out.println("Erro ao listar eventos! " + e.getMessage());
    }
  }
}