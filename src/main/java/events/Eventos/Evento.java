package events.Eventos;
import java.util.Scanner;
import events.Eventos.Componentes.Construtor;
import events.Eventos.Crud.Atualizar;
import events.Eventos.Crud.Excluir;
import events.Eventos.Crud.Inserir;
import events.Eventos.Crud.Leitura;
import events.propriedades.Escolha;

public class Evento extends Thread {
  Construtor construtor = new Construtor();
  Scanner scanner = new Scanner(System.in);
  int eventoEscolha = 0;
  String conexao;
  
  public Evento() {
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
          new Inserir().run();
          break;
        case 2:
          new Atualizar().run();
          break;
        case 3:
          new Excluir().run();
          break;
        case 4:
          new Leitura().run();
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
