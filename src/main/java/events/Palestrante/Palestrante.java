package events.Palestrante;

import java.util.Scanner;
import events.propriedades.Escolha;
import events.Interface.Crud;
import events.Palestrante.Crud.Actions;

public class Palestrante {
  Scanner scan = new Scanner(System.in);
  int palestranteEscolha;
  Crud actions = new Actions();

  public void run() {
    actions.Conexao();
    do {
      System.out.print("\nÁrea do Palestrante\n");
      System.out.print("\n[1] - Adicionar Palestrante");
      System.out.print("\n[2] - Editar Palestrante");
      System.out.print("\n[3] - Excluir Palestrante");
      System.out.print("\n[4] - Listar Palestrantes");
      System.out.print("\n[0] - Voltar");
      System.out.print("\nInforme a operação desejada: ");
      try {
        if (scan.hasNextInt()) {
          palestranteEscolha = scan.nextInt();
          scan.nextLine();
        } else {
          System.err.print("\nEntrada inválida! Insira um número.\n");
          scan.next();
          continue;
        }
      } catch (Exception e) {
        System.err.print("\nErro ao ler entrada! Tente novamente.\n");
        scan.nextLine();
        continue;
      }
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
          System.err.print("\nEssa operação não existe! Encerrando...\n");
          break;
      }
    } while (palestranteEscolha != 5);
  }
}