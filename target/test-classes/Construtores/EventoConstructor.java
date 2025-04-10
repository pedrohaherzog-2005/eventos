package events.Eventos.Componentes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventoConstructor {

  @Test
  public void testCtEvento() {
    Construtor evento = new Construtor();

    String nome = "Java Conference";
    String descricao = "Evento sobre Java e boas práticas";
    String data = "2025-05-01";
    String local = "São Paulo";
    int capacidade = 500;
    int palestrante = 42;
    int id = 101;

    evento.ctEvento(nome, descricao, data, local, capacidade, palestrante, id);

    assertEquals(nome, evento.getNome());
    assertEquals(descricao, evento.getDescricao());
    assertEquals(data, evento.getData());
    assertEquals(local, evento.getLocal());
    assertEquals(capacidade, evento.getCapacidade());
    assertEquals(palestrante, evento.getPalestrante());
    assertEquals(id, evento.getId());
  }
}
