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
      pStatement.setString(1, String.valueOf(this.construtor.getNome()));
      pStatement.setString(2, String.valueOf(this.construtor.getDescricao()));
      pStatement.setString(3, String.valueOf(this.construtor.getData()));
      pStatement.setString(4, String.valueOf(this.construtor.getLocal()));
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
      String update = "UPDATE evento SET nome = ?, descricao = ?, data = ?, local = ?, capacidade = ?, palestrante = ? WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(update);
      pStatement.setString(1, String.valueOf(this.construtor.getNome()));
      pStatement.setString(2, String.valueOf(this.construtor.getDescricao()));
      pStatement.setString(3, String.valueOf(this.construtor.getData()));
      pStatement.setString(4, String.valueOf(this.construtor.getLocal()));
      pStatement.setInt(5, Integer.parseInt(this.construtor.getCapacidade().getText()));
      pStatement.setInt(6, Integer.parseInt(this.construtor.getPalestrante().getText()));
      pStatement.setInt(7, Integer.parseInt(this.construtor.getId().getText()));
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
      // Provide a default implementation or throw an exception if not used
      throw new UnsupportedOperationException("Leitura() without parameters is not supported. Use Leitura(JTextArea textArea) instead.");
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