package events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextArea;
import events.dao.contrutores.PalestranteConstrutor;

public class PalestranteDao implements Crud {
  private String conexao;
  private PalestranteConstrutor construtor;

  public PalestranteDao(PalestranteConstrutor palestranteConstrutor) {
    this.construtor = palestranteConstrutor;
  }

  @Override
  public void Conexao() {
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\banco";
  }

  @Override
  public void Inserir() {
    try {
      String sqlInsert = "INSERT INTO palestrante (nome, curriculo, atuacao, evento) VALUES (?, ?, ?, ?)";
      Connection conn = DriverManager.getConnection(this.conexao);
      String sqlCheckStmt = "SELECT COUNT(*) AS total FROM evento WHERE id = ?";
      PreparedStatement checkStatement = conn.prepareStatement(sqlCheckStmt);
      PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
      checkStatement.setInt(1, Integer.parseInt(this.construtor.getEvento().getText()));
      ResultSet rs = checkStatement.executeQuery();
      if (rs.next() && rs.getInt("total") == 0) {
        System.err.print("\nEvento não encontrado!");
        return;
      }
      pStatement.setString(1, this.construtor.getNome().getText());
      pStatement.setString(2, this.construtor.getCurriculo().getText());
      pStatement.setString(3, this.construtor.getAtuacao().getText());
      pStatement.setInt(4, Integer.parseInt(this.construtor.getEvento().getText()));
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
    try {
      String idStr = this.construtor.getId().getText();
      if (idStr == null || idStr.trim().isEmpty()) {
        throw new IllegalArgumentException("O campo ID não pode estar vazio!");
      }
      int id = Integer.parseInt(idStr);

      String sqlSelect = "SELECT * FROM palestrante WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement selectStmt = conn.prepareStatement(sqlSelect);
      selectStmt.setInt(1, id);
      ResultSet rs = selectStmt.executeQuery();

      if (!rs.next()) {
        throw new IllegalArgumentException("Palestrante não encontrado para o ID informado!");
      }

      String nome = this.construtor.getNome().getText().trim();
      if (nome.isEmpty())
        nome = rs.getString("nome");

      String curriculo = this.construtor.getCurriculo().getText().trim();
      if (curriculo.isEmpty())
        curriculo = rs.getString("curriculo");

      String atuacao = this.construtor.getAtuacao().getText().trim();
      if (atuacao.isEmpty())
        atuacao = rs.getString("atuacao");

      String eventoStr = this.construtor.getEvento().getText().trim();
      int evento = eventoStr.isEmpty() ? rs.getInt("evento") : Integer.parseInt(eventoStr);

      rs.close();
      selectStmt.close();

      String sqlUpdate = "UPDATE palestrante SET nome = ?, curriculo = ?, atuacao = ?, evento = ? WHERE id = ?";
      PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
      pStatement.setString(1, nome);
      pStatement.setString(2, curriculo);
      pStatement.setString(3, atuacao);
      pStatement.setInt(4, evento);
      pStatement.setInt(5, id);

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
      String sqlDelete = "DELETE FROM palestrante WHERE id = ?";
      Connection conn = DriverManager.getConnection(this.conexao);
      PreparedStatement pStatement = conn.prepareStatement(sqlDelete);
      pStatement.setInt(1, Integer.parseInt(this.construtor.getId().getText()));
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

  public void Leitura(JTextArea textArea) {
    StringBuilder sb = new StringBuilder();
    try {
      String sqlSelect = "SELECT p.id, p.nome, p.curriculo, p.atuacao, p.evento FROM palestrante p";
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        sb.append("Id: ").append(rs.getInt("id")).append("\n");
        sb.append("Nome: ").append(rs.getString("nome")).append("\n");
        sb.append("Currículo: ").append(rs.getString("curriculo")).append("\n");
        sb.append("Atuação: ").append(rs.getString("atuacao")).append("\n");
        sb.append("Evento: ").append(rs.getInt("evento")).append("\n\n");
      }
      textArea.setText(sb.toString());
      rs.close();
      statement.close();
      conn.close();
    } catch (Exception e) {
      textArea.setText("Erro ao listar palestrantes! " + e.getMessage() + "\n");
      e.printStackTrace();
    }
  }

  @Override
  public void Leitura() {
    throw new UnsupportedOperationException("Unimplemented method 'Leitura'");
  }
}