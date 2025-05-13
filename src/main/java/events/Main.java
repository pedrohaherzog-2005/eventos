package events;

import events.propriedades.Escolha;

public class Main {
	public static void main(String[] args) {
		int start = 0;
		do {
			Escolha escolha = new Escolha();
			escolha.run();
		} while (start != 0);
	}
}