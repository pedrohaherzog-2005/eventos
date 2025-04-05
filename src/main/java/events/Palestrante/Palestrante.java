package events.Palestrante;
import java.util.Scanner;
import events.propriedades.Escolha;
import events.Interface.Crud;
import events.Palestrante.Crud.Actions;

public class Palestrante  {
  Scanner scan = new Scanner(System.in);
  int palestranteEscolha = 0;
  Crud actions = new Actions();
  public void run() {
    actions.Conexao();
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
          actions.Inserir();
          break;
        case 2:
          actions.Atualizar();
          break;
        case 3:
          actions.Excluir();
          break;
        case 4:
          actions.Leitura();
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
