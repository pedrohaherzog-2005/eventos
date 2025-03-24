package events.Eventos.Componentes;

import java.util.Scanner;

public class Construtor {
  private String nome;
  private String descricao;
  private String data;
  private String local;
  private int capacidade;
  private String palestrante;
  private int id;

  public void ctEvento(String nome, String descricao, String data, String local, int capacidade, String palestrante, int id) {
    this.nome = nome;
    this.descricao = descricao;
    this.data = data;
    this.local = local;
    this.capacidade = capacidade;
    this.palestrante = palestrante;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public String getPalestrante() {
    return palestrante;
  }

  public void setPalestrante(String palestrante) {
    this.palestrante = palestrante;
  }

  public int getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(int capacidade) {
    this.capacidade = capacidade;
  }

  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
