package events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
  private int id;

  Scanner scanner = new Scanner(System.in);

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

  public Evento() {
    String conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";
    int eventoEscolha = 0;
    try (Connection conn = DriverManager.getConnection(conexao);
        Statement statement = conn.createStatement()) {
      do {
        System.out.println("+--------------------------------------------------+");
        System.out.println("\n\nÁREA DE EVENTO\n\n");
        System.out.println("[1] - ADICIONAR EVENTO");
        System.out.println("[2] - EDITAR EVENTO");
        System.out.println("[3] - EXCLUIR EVENTO");
        System.out.println("[4] - LISTAR EVENTOS");
        System.out.println("[0] - VOLTAR");
        System.out.println("\n\nINFORME A OPERAÇÃO DESEJADA: \n\n");
        eventoEscolha = scanner.nextInt();
        System.out.println("+--------------------------------------------------+");

        switch (eventoEscolha) {
          case 1:
            System.out.print("\nDIGITE O NOME DO EVENTO: ");
            this.setNome(scanner.next());
            scanner.nextLine();
            System.out.print("\nDIGITE A DESCRIÇÃO DO EVENTO: ");
            this.setDescricao(scanner.next());
            scanner.nextLine();
            System.out.print("\nDIGITE A DATA DO EVENTO: ");
            this.setData(scanner.next());
            scanner.nextLine();
            System.out.print("\nDIGITE O LOCAL DO EVENTO: ");
            this.setLocal(scanner.next());
            scanner.nextLine();
            System.out.print("\nDIGITE A CAPACIDADE DO EVENTO: ");
            this.setCapacidade(scanner.nextInt());
            scanner.nextLine();
            System.out.print("\nDIGITE O PALESTRANTE DO EVENTO: ");
            this.setPalestrante(scanner.next());
            scanner.nextLine();

            String insert = "INSERT INTO evento (nome, descricao, data, local, capacidade, palestrante) VALUES ('"
                + getNome() + "', '" + this.getDescricao() + "', '" + this.getData() + "', '" + this.getLocal() + "', '"
                + this.getCapacidade() + "', '" + this.getPalestrante() + "')";
            statement.execute(insert);
            System.out.println("+--------------------------------------------------+");
            System.out.println("\n\nEVENTO ADICIONADO COM SUCESSO\n\n");
            System.out.println("+--------------------------------------------------+");
            break;
          case 2:
            System.out.print("\nDIGITE O ID DO EVENTO: ");
            this.setId(scanner.nextInt());
            scanner.nextLine();
            System.out.print("\nDIGITE O NOME DO EVENTO: ");
            this.setNome(scanner.next());
            scanner.nextLine();
            System.out.print("\nDIGITE A DESCRIÇÃO DO EVENTO: ");
            this.setDescricao(scanner.next());
            scanner.nextLine();
            System.out.print("\nDIGITE A DATA DO EVENTO: ");
            this.setData(scanner.next());
            scanner.nextLine();
            System.out.print("\nDIGITE O LOCAL DO EVENTO: ");
            this.setLocal(scanner.next());
            scanner.nextLine();
            System.out.print("\nDIGITE A CAPACIDADE DO EVENTO: ");
            this.setCapacidade(scanner.nextInt());
            scanner.nextLine();
            System.out.print("\nDIGITE O PALESTRANTE DO EVENTO: ");
            this.setPalestrante(scanner.next());
            scanner.nextLine();

            String update = "UPDATE evento SET nome = '" + this.getNome() + "', descricao = '" + this.getDescricao()
                + "', data = '" + this.getData() + "', local = '" + this.getLocal() + "', capacidade = '"
                + this.getCapacidade()
                + "', palestrante = '" + this.getPalestrante() + "' WHERE id = '" + this.getId() + "'";
            statement.execute(update);
            System.out.println("+--------------------------------------------------+");
            System.out.println("\n\nEVENTO ATUALIZADO COM SUCESSO\n\n");
            System.out.println("+--------------------------------------------------+");
            break;
          case 3:
            scanner.nextLine();
            System.out.print("\nDIGITE O ID DO EVENTO: ");
            this.setId(scanner.nextInt());
            scanner.nextLine();

            String delete = "DELETE FROM evento WHERE id = '" + this.getId() + "'";
            statement.execute(delete);
            System.out.println("+--------------------------------------------------+");
            System.out.println("\n\nEVENTO DELETADO COM SUCESSO\n\n");
            System.out.println("+--------------------------------------------------+");
            break;
          case 4:
            String sqlSelect = "select e.id, e.nome, e.descricao, e.data, e.local, e.capacidade, e.palestrante from evento e";
            System.out.println("+--------------------------------------------------+");
            System.out.println("\nLISTA DE EVENTOS\n");
            ResultSet rs = statement.executeQuery(sqlSelect);
            while (rs.next()) {
              System.out.println("ID: " + rs.getInt("ID"));
              System.out.println("NOME: " + rs.getString("NOME"));
              System.out.println("DESCRIÇÃO: " + rs.getString("DESCRICAO"));
              System.out.println("DATA: " + rs.getString("DATA"));
              System.out.println("LOCAL: " + rs.getString("LOCAL"));
              System.out.println("CAPACIDADE: " + rs.getInt("CAPACIDADE"));
              System.out.println("PALESTRANTE: " + rs.getString("PALESTRANTE"));
              System.out.println("\n\n");
            }
            System.out.println("+--------------------------------------------------+");
            break;
          case 0:
            new Escolha().start();
            break;
          default:
            System.err.println("+--------------------------------------------------+");
            System.err.println("\n\nESTA OPÇÃO NÃO EXISTE. FAVOR TENTE NOVAMENTE\n\n");
            System.err.println("+--------------------------------------------------+");
            break;
        }
      } while (eventoEscolha != 0);

    } catch (Exception e) {
      System.out.println("ERRO DE CONEXÃO: " + e.getMessage());
    }
  }
}
