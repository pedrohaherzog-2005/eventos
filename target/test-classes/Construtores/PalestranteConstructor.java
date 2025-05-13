package events.Palestrante.Componentes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalestranteConstructor {

    private Construtor palestrante;

    @BeforeEach
    void setUp() {
        palestrante = new Construtor();
    }

    @Test
    void testSetAndGetId() {
        palestrante.setId(1);
        assertEquals(1L, palestrante.getId());
    }

    @Test
    void testSetAndGetNome() {
        palestrante.setNome("Ana Souza");
        assertEquals("Ana Souza", palestrante.getNome());
    }

    @Test
    void testSetAndGetCurriculo() {
        palestrante.setCurriculo("Doutora em IA");
        assertEquals("Doutora em IA", palestrante.getCurriculo());
    }

    @Test
    void testSetAndGetAtuacao() {
        palestrante.setAtuacao("Pesquisa Científica");
        assertEquals("Pesquisa Científica", palestrante.getAtuacao());
    }

    @Test
    void testSetAndGetEvento() {
        palestrante.setEvento(42);
        assertEquals(42, palestrante.getEvento());
    }

    @Test
    void testCtPalestranteMethod() {
        palestrante.ctPalestrante("Carlos Silva", "Mestre em TI", "Desenvolvimento", 5, 99);

        assertEquals("Carlos Silva", palestrante.getNome());
        assertEquals("Mestre em TI", palestrante.getCurriculo());
        assertEquals("Desenvolvimento", palestrante.getAtuacao());
        assertEquals(5, palestrante.getEvento());
        assertEquals(99L, palestrante.getId());
    }
}
