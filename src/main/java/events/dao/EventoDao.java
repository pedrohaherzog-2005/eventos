package events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextArea;
import events.dao.contrutores.EventoConstrutor;

public class EventoDao implements Crud {
  private String conexao;
  public EventoConstrutor construtor;

  public EventoDao(EventoConstrutor construtor) {
    this.construtor = construtor;
  }

  @Override
  public void Conexao() {
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\banco";
  }

  @Override
  public void Inserir() {
    try {
      String sqlInsert = "INSERT INTO evento (nome, descricao, data, local, capacidade, palestrante) VALUES (?, ?, ?, ?, ?, ?)";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      pStatement.setString(1, this.construtor.getNome().getText());
      pStatement.setString(2, this.construtor.getDescricao().getText());
      pStatement.setString(3, this.construtor.getData().getText());
      pStatement.setString(4, this.construtor.getLocal().getText());
      pStatement.setInt(5, Integer.parseInt(this.construtor.getCapacidade().getText()));
      pStatement.setInt(6, Integer.parseInt(this.construtor.getPalestrante().getText()));
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
    try {
      String idStr = this.construtor.getId().getText();
      if (idStr == null || idStr.trim().isEmpty()) {
        throw new IllegalArgumentException("O campo ID não pode estar vazio!");
      }
      int id = Integer.parseInt(idStr);

      String sqlSelect = "SELECT * FROM evento WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement selectStmt = conn.prepareStatement(sqlSelect);
      selectStmt.setInt(1, id);
      ResultSet rs = selectStmt.executeQuery();

      if (!rs.next()) {
        throw new IllegalArgumentException("Evento não encontrado para o ID informado!");
      }

      String nome = this.construtor.getNome().getText().trim();
      if (nome.isEmpty())
        nome = rs.getString("nome");

      String descricao = this.construtor.getDescricao().getText().trim();
      if (descricao.isEmpty())
        descricao = rs.getString("descricao");

      String data = this.construtor.getData().getText().trim();
      if (data.isEmpty())
        data = rs.getString("data");

      String local = this.construtor.getLocal().getText().trim();
      if (local.isEmpty())
        local = rs.getString("local");

      String capacidadeStr = this.construtor.getCapacidade().getText().trim();
      int capacidade = capacidadeStr.isEmpty() ? rs.getInt("capacidade") : Integer.parseInt(capacidadeStr);
      String palestranteStr = this.construtor.getPalestrante().getText().trim();
      int palestrante = palestranteStr.isEmpty() ? rs.getInt("palestrante") : Integer.parseInt(palestranteStr);

      rs.close();
      selectStmt.close();

      String update = "UPDATE evento SET nome = ?, descricao = ?, data = ?, local = ?, capacidade = ?, palestrante = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(update);
      pStatement.setString(1, nome);
      pStatement.setString(2, descricao);
      pStatement.setString(3, data);
      pStatement.setString(4, local);
      pStatement.setInt(5, capacidade);
      pStatement.setInt(6, palestrante);
      pStatement.setInt(7, id);

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
    try {
      String sqlDelete = "DELETE FROM evento WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, Integer.parseInt(this.construtor.getId().getText()));
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
    throw new UnsupportedOperationException(
        "Leitura() without parameters is not supported. Use Leitura(JTextArea textArea) instead.");
  }

  public void Leitura(JTextArea textArea) {
    StringBuilder sb = new StringBuilder();
    try {
      String sqlSelect = "select e.id, e.nome, e.descricao, e.data, e.local, e.capacidade, e.palestrante from evento e";
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        sb.append("Id: ").append(rs.getInt("id")).append("\n");
        sb.append("Nome: ").append(rs.getString("nome")).append("\n");
        sb.append("Descrição: ").append(rs.getString("descricao")).append("\n");
        sb.append("Data: ").append(rs.getString("data")).append("\n");
        sb.append("Local: ").append(rs.getString("local")).append("\n");
        sb.append("Capacidade: ").append(rs.getInt("capacidade")).append("\n");
        sb.append("Palestrante: ").append(rs.getInt("palestrante")).append("\n\n");
      }
      textArea.setText(sb.toString());
      rs.close();
      statement.close();
      conn.close();
    } catch (Exception e) {
      textArea.setText("Erro ao listar eventos! " + e.getMessage());
      e.printStackTrace();
    }
  }
}