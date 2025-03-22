package events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import events.propriedades.Escolha;

public class Evento extends Thread {
  private String nome;
  private String descricao;
  private String data;
  private String local;
  private int capacidade;
  private String palestrante;

  Scanner scanner = new Scanner(System.in);

  public void ctEvento(String nome, String descricao, String data, String local, int capacidade, String palestrante) {
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

  public Evento() {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    int eventoEscolha = 0;
    try (Connection conn = DriverManager.getConnection(conexao);
        Statement statement = conn.createStatement()) {
      do {
        System.out.println("\nÁREA DE EVENTOS");
        System.out.println(
            "\n[1] - CADASTRAR EVENTOS \n[2] - ATUALIZAR EVENTOS \n[3] - EXCLUIR EVENTOS \n[4] - VISUALIZAR EVENTOS \n[0] - VOLTAR");
        System.out.print("\nDIGITE A OPÇÃO DESEJADA: ");
        eventoEscolha = scanner.nextInt();
        switch (eventoEscolha) {
          case 1:
            scanner.nextLine();
            System.out.print("\nDIGITE O NOME DO EVENTO: ");
            this.setNome(scanner.next());
            System.out.print("DIGITE A DESCRIÇÃO DO EVENTO: ");
            this.setDescricao(scanner.next());
            System.out.print("DIGITE A DATA DO EVENTO: ");
            this.setData(scanner.next());
            System.out.print("DIGITE O LOCAL DO EVENTO: ");
            this.setLocal(scanner.next());
            System.out.print("DIGITE A CAPACIDADE DO EVENTO: ");
            this.setCapacidade(scanner.nextInt());
            System.out.print("DIGITE O PALESTRANTE DO EVENTO: ");
            this.setPalestrante(scanner.next());

            String insert = "INSERT INTO evento (nome, descricao, data, local, capacidade, palestrante) VALUES ('"
                + getNome() + "', '" + this.getDescricao() + "', '" + this.getData() + "', '" + this.getLocal() + "', '"
                + this.getCapacidade() + "', '" + this.getPalestrante() + "')";
            statement.execute(insert);
            System.out.println("\nEVENTO CADASTRADO COM SUCESSO!");
            break;
          case 2:
            scanner.nextLine();
            System.out.print("\nDIGITE O NOME DO EVENTO: ");
            this.setNome(scanner.next());
            System.out.print("DIGITE A DESCRIÇÃO DO EVENTO: ");
            this.setDescricao(scanner.next());
            System.out.print("DIGITE A DATA DO EVENTO: ");
            this.setData(scanner.next());
            System.out.print("DIGITE O LOCAL DO EVENTO: ");
            this.setLocal(scanner.next());
            System.out.print("DIGITE A CAPACIDADE DO EVENTO: ");
            this.setCapacidade(scanner.nextInt());
            System.out.print("DIGITE O PALESTRANTE DO EVENTO: ");
            this.setPalestrante(scanner.next());

            String update = "UPDATE evento SET nome = '" + this.getNome() + "', descricao = '" + this.getDescricao()
                + "', data = '" + this.getData() + "', local = '" + this.getLocal() + "', capacidade = '"
                + this.getCapacidade()
                + "', palestrante = '" + this.getPalestrante() + "' WHERE nome = '" + this.getNome() + "'";
            statement.execute(update);
            System.out.println("EVENTO ATUALIZADO COM SUCESSO!");
            break;
          case 3:
            scanner.nextLine();
            System.out.print("DIGITE O NOME DO EVENTO: ");
            this.setNome(scanner.next());

            String delete = "DELETE FROM evento WHERE nome = '" + this.getNome() + "'";
            statement.execute(delete);
            System.out.println("EVENTO DELETADO COM SUCESSO!");
            break;
          case 4:
            String select = "SELECT * FROM evento";
            statement.execute(select);
            break;
          case 0:
            new Escolha().start();
            break;
          default:
            System.out.println("OPÇÃO INVÁLIDA!");
            break;
        }
      } while (eventoEscolha != 0);

    } catch (Exception e) {
      System.out.println("ERRO DE CONEXÃO: " + e.getMessage());
    }
  }
}
