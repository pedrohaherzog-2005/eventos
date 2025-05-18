package events.dao.contrutores;

import javax.swing.JTextField;

public class PalestranteConstrutor {
  private JTextField nome;
  private JTextField curriculo;
  private JTextField atuacao;
  private JTextField evento;
  private JTextField id;

  public void ctPalestrante(JTextField nome, JTextField curriculo, JTextField atuacao, JTextField evento, JTextField id) {
    this.nome = nome;
    this.curriculo = curriculo;
    this.atuacao = atuacao;
    this.evento = evento;
    this.id = id;
  }

  public JTextField getId() {
    return id;
  }

  public void setId(JTextField id) {
    this.id = id;
  }

  public JTextField getNome() {
    return nome;
  }

  public void setNome(JTextField nome) {
    this.nome = nome;
  }

  public JTextField getCurriculo() {
    return curriculo;
  }

  public void setCurriculo(JTextField curriculo) {
    this.curriculo = curriculo;
  }

  public JTextField getAtuacao() {
    return atuacao;
  }

  public void setAtuacao(JTextField atuacao) {
    this.atuacao = atuacao;
  }

  public JTextField getEvento() {
    return evento;
  }

  public void setEvento(JTextField evento) {
    this.evento = evento;
  }
}
