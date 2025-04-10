package events.Eventos;

import events.Interface.Crud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

public class EventoTest {

    private Crud mockCrud;

    @BeforeEach
    public void setUp() {
        mockCrud = mock(Crud.class);
    }

    @Test
    public void testInserirEvento() {
        String input = "1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Evento evento = new Evento() {
            {
                this.actions = mockCrud;
            }
        };

        evento.run();

        verify(mockCrud).Conexao();
        verify(mockCrud).Inserir();
        verifyNoMoreInteractions(mockCrud);
    }

    @Test
    public void testAtualizarEvento() {
        String input = "2\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Evento evento = new Evento() {
            {
                this.actions = mockCrud;
            }
        };

        evento.run();

        verify(mockCrud).Conexao();
        verify(mockCrud).Atualizar();
        verifyNoMoreInteractions(mockCrud);
    }

    @Test
    public void testExcluirEvento() {
        String input = "3\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Evento evento = new Evento() {
            {
                this.actions = mockCrud;
            }
        };

        evento.run();

        verify(mockCrud).Conexao();
        verify(mockCrud).Excluir();
        verifyNoMoreInteractions(mockCrud);
    }

    @Test
    public void testLeituraEvento() {
        String input = "4\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Evento evento = new Evento() {
            {
                this.actions = mockCrud;
            }
        };

        evento.run();

        verify(mockCrud).Conexao();
        verify(mockCrud).Leitura();
        verifyNoMoreInteractions(mockCrud);
    }
}
