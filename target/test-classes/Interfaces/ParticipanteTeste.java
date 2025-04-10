package events.Participante;

import events.Interface.Crud;
import events.Participante.Crud.Actions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.Mockito.*;

class ParticipanteTeste {

  Crud mockActions;
  Scanner mockScanner;
  Participante participante;

  @BeforeEach
  void setUp() {
    mockActions = mock(Crud.class);
    mockScanner = mock(Scanner.class);

    participante = new Participante() {
      {
        this.scan = mockScanner;
        this.actions = mockActions;
      }
    };
  }

  @Test
  void testRunOption1_Insert() {
    when(mockScanner.nextInt())
        .thenReturn(1, 0);

    participante.run();

    verify(mockActions).Conexao();
    verify(mockActions).Inserir();
  }

  @Test
  void testRunOption2_Update() {
    when(mockScanner.nextInt())
        .thenReturn(2, 0);

    participante.run();

    verify(mockActions).Conexao();
    verify(mockActions).Atualizar();
  }

  @Test
  void testRunOption3_Delete() {
    when(mockScanner.nextInt())
        .thenReturn(3, 0);

    participante.run();

    verify(mockActions).Conexao();
    verify(mockActions).Excluir();
  }

  @Test
  void testRunOption4_Read() {
    when(mockScanner.nextInt())
        .thenReturn(4, 0);

    participante.run();

    verify(mockActions).Conexao();
    verify(mockActions).Leitura();
  }

  @Test
  void testRunInvalidOption() {
    when(mockScanner.nextInt())
        .thenReturn(9, 0);

    participante.run();

    verify(mockActions).Conexao();
    verifyNoMoreInteractions(mockActions);
  }
}
