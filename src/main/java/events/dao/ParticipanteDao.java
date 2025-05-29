package events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextArea;
import events.dao.contrutores.ParticipanteConstrutor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ParticipanteDao implements Crud {
  private String conexao;
  public ParticipanteConstrutor construtor;

  public ParticipanteDao(ParticipanteConstrutor construtor) {
    this.construtor = construtor;
  }

  @Override
  public void Conexao() {
    this.conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\banco";
  }

  private String hashCpf(String cpf) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(cpf.getBytes());
    StringBuilder hexString = new StringBuilder();
    for (byte b : hash) {
      String hex = Integer;toHexString(b & 0xFF);
      if (hex.length() == 1) hexString.append("0");
      hexString.append(hex);
    }
    return hexString.toString();
  }

  @Override
public void Inserir() {
    try {
        String sqlInsert = "INSERT INTO participante (nome, cpf, nascimento, sexo, inscricao) VALUES (?, ?, ?, ?, ?)";
        Connection conn = DriverManager.getConnection(this.conexao);
        PreparedStatement pStatement = conn.prepareStatement(sqlInsert);
        pStatement.setString(1, this.construtor.getNome().getText());
        String cpfHash = hashCpf(this.construtor.getCpf().getText());
        pStatement.setString(2, cpfHash);
        pStatement.setString(3, this.construtor.getDt_nascimento().getText());
        pStatement.setString(4, this.construtor.getSexo().getText());

        String inscricaoStr = this.construtor.getInscricao().getText().trim();
        int inscricao = inscricaoStr.isEmpty() ? 0 : Integer.parseInt(inscricaoStr);
        pStatement.setInt(5, inscricao);

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

        String sqlSelect = "SELECT * FROM participante WHERE id = ?";
        Connection conn = DriverManager.getConnection(this.conexao);
        PreparedStatement selectStmt = conn.prepareStatement(sqlSelect);
        selectStmt.setInt(1, id);
        ResultSet rs = selectStmt.executeQuery();

        if (!rs.next()) {
            throw new IllegalArgumentException("Participante não encontrado para o ID informado!");
        }

        String nome = this.construtor.getNome().getText().trim();
        if (nome.isEmpty()) nome = rs.getString("nome");

        String cpf = this.construtor.getCpf().getText().trim();
        if (cpf.isEmpty()) cpf = rs.getString("cpf");
        else cpf = hashCpf(cpf);

        String dtNascimento = this.construtor.getDt_nascimento().getText().trim();
        if (dtNascimento.isEmpty()) dtNascimento = rs.getString("nascimento");

        String sexo = this.construtor.getSexo().getText().trim();
        if (sexo.isEmpty()) sexo = rs.getString("sexo");

        String inscricaoStr = this.construtor.getInscricao().getText().trim();
        int inscricao = inscricaoStr.isEmpty() ? rs.getInt("inscricao") : Integer.parseInt(inscricaoStr);

        rs.close();
        selectStmt.close();

        String sqlUpdate = "UPDATE participante SET nome = ?, cpf = ?, nascimento = ?, sexo = ?, inscricao = ? WHERE id = ?";
        PreparedStatement pStatement = conn.prepareStatement(sqlUpdate);
        pStatement.setString(1, nome);
        pStatement.setString(2, cpf);
        pStatement.setString(3, dtNascimento);
        pStatement.setString(4, sexo);
        pStatement.setInt(5, inscricao);
        pStatement.setInt(6, id);

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
    throw new UnsupportedOperationException(
        "Leitura() without parameters is not supported. Use Leitura(JTextArea textArea) instead.");
  }

  public void Leitura(JTextArea textArea) {
    StringBuilder sb = new StringBuilder();
    try {
      String sqlSelect = "SELECT ppp.id, ppp.nome, ppp.cpf, ppp.nascimento, ppp.sexo, ppp.inscricao FROM participante ppp";
      Connection conn = DriverManager.getConnection(this.conexao);
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(sqlSelect);
      while (rs.next()) {
        sb.append("Id: ").append(rs.getInt("id")).append("\n");
        sb.append("Nome: ").append(rs.getString("nome")).append("\n");
        sb.append("CPF: ").append(rs.getString("cpf")).append("\n");
        sb.append("Data de Nascimento: ").append(rs.getString("nascimento")).append("\n");
        sb.append("Sexo: ").append(rs.getString("sexo")).append("\n");
        sb.append("Inscrição: ").append(rs.getInt("inscricao")).append("\n\n");
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