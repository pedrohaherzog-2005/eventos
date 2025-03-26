package events.Palestrante;
import java.util.Scanner;
import events.Palestrante.Crud.Atualizar;
import events.Palestrante.Crud.Inserir;
import events.Palestrante.Crud.Leitura;
import events.Palestrante.Crud.Excluir;
import events.propriedades.Escolha;

public class Palestrante extends Thread {
  Scanner scan = new Scanner(System.in);
  int palestranteEscolha = 0;
  
  public Palestrante() {
    do {
      System.out.println("+--------------------------------------------------+");
      System.out.println("\n\nÁREA DO PALESTRANTE\n\n");
      System.out.println("[1] - ADICIONAR PALESTRANTE");
      System.out.println("[2] - EDITAR PALESTRANTE");
      System.out.println("[3] - EXCLUIR PALESTRANTE");
      System.out.println("[4] - LISTAR PALESTRANTES");
      System.out.println("[0] - VOLTAR");
      System.out.println("\n\nINFORME A OPERAÇÃO DESEJADA: \n\n");
      palestranteEscolha = scan.nextInt();
      System.out.println("+--------------------------------------------------+");

      switch (palestranteEscolha) {
        case 1:
          new Inserir().run();;
          break;
        case 2:
          new Atualizar().run();;
          break;
        case 3:
          new Excluir().run();
          break;
        case 4:
          new Leitura().run();
          break;
        case 0:
          new Escolha().run();
          break;
        default:
          System.err.println("+--------------------------------------------------+");
          System.err.println("\n\nESTA OPÇÃO NÃO EXISTE. FAVOR TENTE NOVAMENTE\n\n");
          System.err.println("+--------------------------------------------------+");
          break;
      }

    } while (palestranteEscolha != 5);
  }
}
