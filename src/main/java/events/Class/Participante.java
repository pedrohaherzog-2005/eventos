package events.Class;
import java.util.Scanner;

import events.dao.ParticipanteDao;
import events.dao.Interface.Crud;
import events.dao.propriedades.Escolha;

public class Participante {
  Scanner scan = new Scanner(System.in);
  int escolha;
  Crud actions = new ParticipanteDao();
  public void run() {
    actions.Conexao();
    do {
      System.out.print("\nÁrea de participante\n");
      System.out.print("\n[1] - Adicionar participante");
      System.out.print("\n[2] - Editar participante");
      System.out.print("\n[3] - Excluir participante");
      System.out.print("\n[4] - Listar participantes");
      System.out.print("\n[0] - Voltar");
      System.out.print("\nInforme a operação desejada: ");
      escolha = scan.nextInt();
      switch (escolha) {
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
          new Escolha();
          break;
        default:
          System.err.print("\nEssa operação não existe! tente novamente\n");
          break;
      }
    } while (escolha != 0);
  }
}
