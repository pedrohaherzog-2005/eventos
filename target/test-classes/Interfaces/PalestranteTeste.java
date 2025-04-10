package events.Palestrante;

import events.Interface.Crud;
import events.Palestrante.Crud.Actions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class PalestranteTeste {

    private Crud mockActions;

    @BeforeEach
    void setup() {
        mockActions = Mockito.mock(Crud.class);
    }

    private void preparePalestranteForTest(Palestrante p, String input) throws Exception {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Field scanField = Palestrante.class.getDeclaredField("scan");
        scanField.setAccessible(true);
        scanField.set(p, scanner);

        Field actionsField = Palestrante.class.getDeclaredField("actions");
        actionsField.setAccessible(true);
        actionsField.set(p, mockActions);
    }

    @Test
    void testInserirOption1() throws Exception {

        String input = "1\n5\n";
        Palestrante palestrante = new Palestrante();
        preparePalestranteForTest(palestrante, input);

        palestrante.run();

        verify(mockActions).Conexao();
        verify(mockActions).Inserir();
    }

    @Test
    void testAtualizarOption2() throws Exception {
        String input = "2\n5\n";
        Palestrante palestrante = new Palestrante();
        preparePalestranteForTest(palestrante, input);

        palestrante.run();

        verify(mockActions).Atualizar();
    }

    @Test
    void testExcluirOption3() throws Exception {
        String input = "3\n5\n";
        Palestrante palestrante = new Palestrante();
        preparePalestranteForTest(palestrante, input);

        palestrante.run();

        verify(mockActions).Excluir();
    }

    @Test
    void testLeituraOption4() throws Exception {
        String input = "4\n5\n";
        Palestrante palestrante = new Palestrante();
        preparePalestranteForTest(palestrante, input);

        palestrante.run();

        verify(mockActions).Leitura();
    }
}
