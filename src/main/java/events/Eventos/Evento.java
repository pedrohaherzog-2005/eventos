package events.Eventos;
import java.util.Scanner;
import events.Eventos.Crud.Actions;
import events.Interface.Crud;
import events.propriedades.Escolha;

public class Evento {
  Scanner scanner = new Scanner(System.in);
  int eventoEscolha = 0;
  Crud actions = new Actions();

  public void run() {
    actions.Conexao();
    do {
      System.out.print("\nÁrea de Evento\n");
      System.out.print("\n[1] - Adicionar Evento");
      System.out.print("\n[2] - Editar Evento");
      System.out.print("\n[3] - Excluir Evento");
      System.out.print("\n[4] - Listar Eventos");
      System.out.print("\n[0] - Voltar");
      System.out.print("\nInforme a operação desejada\n");
      eventoEscolha = scanner.nextInt();
      switch (eventoEscolha) {
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
          System.err.print("\nEssa operação não existe! tente novamente\n");
          break;
      }
    } while (eventoEscolha != 0);
  }
}
