package events.dao.contrutores;

import javax.swing.JTextField;

public class ParticipanteConstrutor {
  private JTextField nome;
  private JTextField cpf;
  private JTextField dt_nascimento;
  private JTextField sexo;
  private JTextField inscricao;
  private JTextField id;

  public void ctParticipante(JTextField nome, JTextField cpf, JTextField dt_nascimento, JTextField sexo, JTextField inscricao, JTextField id) {
    this.nome = nome;
    this.cpf = cpf;
    this.dt_nascimento = dt_nascimento;
    this.sexo = sexo;
    this.inscricao = inscricao;
  }

  public JTextField getNome() {
    return nome;
  }

  public void setNome(JTextField nome) {
    this.nome = nome;
  }

  public JTextField getCpf() {
    return cpf;
  }

  public void setCpf(JTextField cpf) {
    this.cpf = cpf;
  }

  public JTextField getDt_nascimento() {
    return dt_nascimento;
  }

  public void setDt_nascimento(JTextField dt_nascimento) {
    this.dt_nascimento = dt_nascimento;
  }

  public JTextField getSexo() {
    return sexo;
  }

  public void setSexo(JTextField sexo) {
    this.sexo = sexo;
  }

  public JTextField getInscricao() {
    return inscricao;
  }

  public void setInscricao(JTextField inscricao) {
    this.inscricao = inscricao;
  }

  public JTextField getId() {
    return id;
  }

  public void setId(JTextField id) {
    this.id = id;
  }
}