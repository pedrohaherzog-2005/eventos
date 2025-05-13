package events.dao.Contrutores;

public class PalestranteConstrutor {
  private String nome;
  private String curriculo;
  private String atuacao;
  private Integer evento;
  private int id;

  public void ctPalestrante(String nome, String curriculo, String atuacao, Integer evento, int id) {
    this.nome = nome;
    this.curriculo = curriculo;
    this.atuacao = atuacao;
    this.evento = evento;
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCurriculo() {
    return curriculo;
  }

  public void setCurriculo(String curriculo) {
    this.curriculo = curriculo;
  }

  public String getAtuacao() {
    return atuacao;
  }

  public void setAtuacao(String atuacao) {
    this.atuacao = atuacao;
  }

  public Integer getEvento() {
    return evento;
  }

  public void setEvento(Integer evento) {
    this.evento = evento;
  }
}
