package events.Eventos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;

import events.propriedades.Escolha;

public class Evento extends Thread {
  Construtor construtor = new Construtor();

  Scanner scanner = new Scanner(System.in);

  public Evento() {
    int eventoEscolha = 0;
    String conexao;
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
          conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

          try {
            Connection conn = DriverManager.getConnection(conexao);
            Statement statement = conn.createStatement();

            System.out.print("\nDIGITE O NOME DO EVENTO: ");
            this.construtor.setNome(scanner.next());
            scanner.nextLine();

            System.out.print("\nDIGITE A DESCRIÇÃO DO EVENTO: ");
            this.construtor.setDescricao(scanner.next());
            scanner.nextLine();

            System.out.print("\nDIGITE A DATA DO EVENTO: ");
            this.construtor.setData(scanner.next());
            scanner.nextLine();

            System.out.print("\nDIGITE O LOCAL DO EVENTO: ");
            this.construtor.setLocal(scanner.next());
            scanner.nextLine();

            System.out.print("\nDIGITE A CAPACIDADE DO EVENTO: ");
            this.construtor.setCapacidade(scanner.nextInt());
            scanner.nextLine();

            System.out.print("\nDIGITE O PALESTRANTE DO EVENTO: ");
            this.construtor.setPalestrante(scanner.next());
            scanner.nextLine();

            String insert = "INSERT INTO evento (nome, descricao, data, local, capacidade, palestrante) VALUES ('"
                + this.construtor.getNome() + "', '" + this.construtor.getDescricao() + "', '"
                + this.construtor.getData() + "', '" + this.construtor.getLocal() + "', '"
                + this.construtor.getCapacidade() + "', '" + this.construtor.getPalestrante() + "')";

            statement.execute(insert);
            System.out.println("+--------------------------------------------------+");
            System.out.println("\n\nEVENTO ADICIONADO COM SUCESSO\n\n");
            System.out.println("+--------------------------------------------------+");

          } catch (Exception e) {
            System.out.println("Erro ao fazer conexão" + e.getMessage());
          }
          break;

        case 2:
          conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

          try {
            Connection conn = DriverManager.getConnection(conexao);
            Statement statement = conn.createStatement();

            System.out.print("\nDIGITE O ID DO EVENTO: ");
            this.construtor.setId(scanner.nextInt());
            scanner.nextLine();

            System.out.print("\nDIGITE O NOME DO EVENTO: ");
            this.construtor.setNome(scanner.next());
            scanner.nextLine();

            System.out.print("\nDIGITE A DESCRIÇÃO DO EVENTO: ");
            this.construtor.setDescricao(scanner.next());
            scanner.nextLine();

            System.out.print("\nDIGITE A DATA DO EVENTO: ");
            this.construtor.setData(scanner.next());
            scanner.nextLine();

            System.out.print("\nDIGITE O LOCAL DO EVENTO: ");
            this.construtor.setLocal(scanner.next());
            scanner.nextLine();

            System.out.print("\nDIGITE A CAPACIDADE DO EVENTO: ");
            this.construtor.setCapacidade(scanner.nextInt());
            scanner.nextLine();

            System.out.print("\nDIGITE O PALESTRANTE DO EVENTO: ");
            this.construtor.setPalestrante(scanner.next());
            scanner.nextLine();

            String update = "UPDATE evento SET nome = '" + this.construtor.getNome() + "', descricao = '"
                + this.construtor.getDescricao()
                + "', data = '" + this.construtor.getData() + "', local = '" + this.construtor.getLocal()
                + "', capacidade = '"
                + this.construtor.getCapacidade()
                + "', palestrante = '" + this.construtor.getPalestrante() + "' WHERE id = '" + this.construtor.getId()
                + "'";

            statement.execute(update);
            System.out.println("+--------------------------------------------------+");
            System.out.println("\n\nEVENTO ATUALIZADO COM SUCESSO\n\n");
            System.out.println("+--------------------------------------------------+");
          } catch (Exception e) {
            System.out.println("Erro ao fazer conexão" + e.getMessage());
          }
          break;

        case 3:
          conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

          try {
            Connection conn = DriverManager.getConnection(conexao);
            Statement statement = conn.createStatement();
            scanner.nextLine();
            System.out.print("\nDIGITE O ID DO EVENTO: ");
            this.construtor.setId(scanner.nextInt());
            scanner.nextLine();

            String delete = "DELETE FROM evento WHERE id = '" + this.construtor.getId() + "'";
            statement.execute(delete);
            System.out.println("+--------------------------------------------------+");
            System.out.println("\n\nEVENTO DELETADO COM SUCESSO\n\n");
            System.out.println("+--------------------------------------------------+");
          } catch (Exception e) {
            System.out.println("Erro ao fazer conexão" + e.getMessage());
          }
          break;

        case 4:
          conexao = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\bd";

          try {
            Connection conn = DriverManager.getConnection(conexao);
            Statement statement = conn.createStatement();

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
          } catch (Exception e) {
            System.out.println("Erro ao fazer conexão" + e.getMessage());
          }
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
  }
}
