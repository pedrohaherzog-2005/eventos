package events.Participante;
import java.util.Scanner;
import events.propriedades.Escolha;
import events.Participante.Crud.Inserir;
import events.Participante.Crud.Atualizar;
import events.Participante.Crud.Excluir;
import events.Participante.Crud.Leitura;

public class Participante extends Thread {
  Scanner scan = new Scanner(System.in);
  int escolha;

  public Participante() {
    do {
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nÁREA DE PARTICIPANTE\n\n");
      System.out.println("[1] - ADICIONAR PARTICIPANTE");
      System.out.println("[2] - EDITAR PARTICIPANTE");
      System.out.println("[3] - EXCLUIR PARTICIPANTE");
      System.out.println("[4] - LISTAR PARTICIPANTE");
      System.out.println("[0] - VOLTAR");
      System.out.println("\n\nINFORME A OPERAÇÃO DESEJADA: \n\n");
      escolha = scan.nextInt();
      System.out.println("+--------------------------------------------------+");

      switch (escolha) {
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
    } while (escolha != 5);
  }
}
