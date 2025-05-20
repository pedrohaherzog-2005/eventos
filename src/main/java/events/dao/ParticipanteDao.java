package events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextArea;

import events.dao.Contrutores.ParticipanteConstrutor;

public class ParticipanteDao implements Crud {
  private String conexao;
  public ParticipanteConstrutor construtor;

  public ParticipanteDao(ParticipanteConstrutor construtor) {
    this.construtor = construtor;
  }

  @Override
  public void Conexao() {
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\banco";
  }

  @Override
  public void Inserir() {

    try {
      String sqlInsert = "INSERT INTO participante (nome, cpf, dt_nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      pStatement.setString(1, this.construtor.getNome().getText());
      pStatement.setString(2, this.construtor.getCpf().getText());
      pStatement.setString(3, this.construtor.getDt_nascimento().getText());
      pStatement.setString(4, this.construtor.getSexo().getText());
      pStatement.setString(5, this.construtor.getInscricao().getText());
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
      String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, dt_nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, this.construtor.getNome().getText());
      pStatement.setString(2, this.construtor.getCpf().getText());
      pStatement.setString(3, this.construtor.getDt_nascimento().getText());
      pStatement.setString(4, this.construtor.getSexo().getText());
      pStatement.setString(5, this.construtor.getInscricao().getText());
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
      String sqlDelete = "DELETE FROM participante WHERE id = ?";
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

  public void Leitura(JTextArea textArea) {
    StringBuilder sb = new StringBuilder();
    try {
      String sqlSelect = "SELECT ppp.id, ppp.nome, ppp.cpf, ppp.dt_nascimento, ppp.sexo, ppp.inscricao FROM participante ppp";
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        sb.append("Id: ").append(rs.getInt("id")).append("\n");
        sb.append("Nome: ").append(rs.getString("nome")).append("\n");
        sb.append("CPF: ").append(rs.getString("cpf")).append("\n");
        sb.append("Data de Nascimento: ").append(rs.getString("dt_nascimento")).append("\n");
        sb.append("Sexo: ").append(rs.getString("sexo")).append("\n");
        sb.append("Inscrição: ").append(rs.getString("inscricao")).append("\n\n");
      }
      textArea.setText(sb.toString());
      rs.close();
      statement.close();
      conn.close();
    } catch (Exception e) {
      textArea.setText("Erro ao listar participantes! " + e.getMessage());
      e.printStackTrace();
    }
  }
}