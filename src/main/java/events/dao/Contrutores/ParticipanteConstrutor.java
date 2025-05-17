package events.dao.contrutores;

public class ParticipanteConstrutor {
  private String nome;
  private String cpf;
  private String dt_nascimento;
  private String sexo;
  private String inscricao;
  private int id;

  public void ctParticipante(String nome, String cpf, String dt_nascimento, String sexo, String inscricao, int id) {
    this.nome = nome;
    this.cpf = cpf;
    this.dt_nascimento = dt_nascimento;
    this.sexo = sexo;
    this.inscricao = inscricao;
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getDt_nascimento() {
    return dt_nascimento;
  }

  public void setDt_nascimento(String dt_nascimento) {
    this.dt_nascimento = dt_nascimento;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getInscricao() {
    return inscricao;
  }

  public void setInscricao(String inscricao) {
    this.inscricao = inscricao;
  }

  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
