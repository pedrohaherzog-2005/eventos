package events.propriedades;
import java.beans.EventSetDescriptor;
import java.util.Scanner;

import events.Evento;
import events.Palestrante;
import events.Participante;

public class Escolha extends Thread {
  Scanner scanner = new Scanner(System.in);
  int inicio = 0;
  public void run() {
    System.out.println("Seja bem-vindo ao sistema de eventos!");
		System.out.println("[-----------------------------------]");
		System.out.println("[1] Eventos [2] Palestrante [3] Participante [0] Sair");
		inicio = scanner.nextInt();

    switch (inicio) {
      case 1:
        Evento evento = new Evento();
        evento.start();
        break;
      case 2:
        Palestrante palestrante = new Palestrante();
        palestrante.start();
        break;
      case 3:
        Participante participante = new Participante();
        participante.start();
        break;
      case 0:
        System.out.println("Saindo...");
        break;
      default:
        System.out.println("Opção inválida!");
        break;
    }
  }
}
