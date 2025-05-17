package events.dao.contrutores;

import javax.swing.JTextField;

public class EventoConstrutor {
  private JTextField nome;
  private JTextField descricao;
  private JTextField data;
  private JTextField local;
  private JTextField capacidade;
  private JTextField palestrante;
  private JTextField id;

  public void ctEvento(JTextField nome, JTextField descricao, JTextField data, JTextField local, JTextField capacidade, JTextField palestrante, JTextField id) {
    this.nome = nome;
    this.descricao = descricao;
    this.data = data;
    this.local = local;
    this.capacidade = capacidade;
    this.palestrante = palestrante;
  }

  public JTextField getNome() {
    return nome;
  }

  public void setNome(JTextField nome) {
    this.nome = nome;
  }

  public JTextField getDescricao() {
    return descricao;
  }

  public void setDescricao(JTextField descricao) {
    this.descricao = descricao;
  }

  public JTextField getData() {
    return data;
  }

  public void setData(JTextField data) {
    this.data = data;
  }

  public JTextField getLocal() {
    return local;
  }

  public void setLocal(JTextField local) {
    this.local = local;
  }

  public JTextField getPalestrante() {
    return palestrante;
  }

  public void setPalestrante(JTextField palestrante) {
    this.palestrante = palestrante;
  }

  public JTextField getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(JTextField capacidade) {
    this.capacidade = capacidade;
  }

  public JTextField getId() {
    return id;
  }

  public void setId(JTextField id) {
    this.id = id;
  }
}
