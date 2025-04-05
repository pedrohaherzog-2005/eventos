package events.propriedades;

import java.util.Scanner;

import events.Eventos.Evento;
import events.Palestrante.Palestrante;
import events.Participante.Participante;

public class Escolha {
  Scanner scanner = new Scanner(System.in);
  int inicio = 0;

  public void run() {
    System.out.print("\nSeja bem-vindo ao sistema de eventos!\n");
    System.out.println("[1] - Eventos \n[2] - Palestrante \n[3] - Participante \n[0] - Sair");
    System.out.print("\nDigite a opção desejada: ");
    inicio = scanner.nextInt();
    switch (inicio) {
      case 1:
        new Evento().run();
        break;
      case 2:
        new Palestrante().run();
        break;
      case 3:
        new Participante().run();
        break;  
      case 0:
        System.out.println("Saindo do sistema...");
        break;
      default:
        System.err.print("\nEssa operação não existe! tente novamente\n");
        break;
    }
  }
}
