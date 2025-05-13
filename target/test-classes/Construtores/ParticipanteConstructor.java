package events.Participante.Componentes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParticipanteConstructor {

    private Construtor participante;

    @BeforeEach
    void setUp() {
        participante = new Construtor();
    }

    @Test
    void testSetAndGetNome() {
        participante.setNome("João da Silva");
        assertEquals("João da Silva", participante.getNome());
    }

    @Test
    void testSetAndGetCpf() {
        participante.setCpf("123.456.789-00");
        assertEquals("123.456.789-00", participante.getCpf());
    }

    @Test
    void testSetAndGetDtNascimento() {
        participante.setDt_nascimento("2000-01-01");
        assertEquals("2000-01-01", participante.getDt_nascimento());
    }

    @Test
    void testSetAndGetSexo() {
        participante.setSexo("Masculino");
        assertEquals("Masculino", participante.getSexo());
    }

    @Test
    void testSetAndGetInscricao() {
        participante.setInscricao("INSCR123");
        assertEquals("INSCR123", participante.getInscricao());
    }

    @Test
    void testSetAndGetId() {
        participante.setId(42);
        assertEquals(42L, participante.getId());
    }

    @Test
    void testCtParticipanteMethod() {
        participante.ctParticipante(
            "Maria Oliveira",
            "987.654.321-00",
            "1995-12-31",
            "Feminino",
            "INSCR999",
            101
        );

        assertEquals("Maria Oliveira", participante.getNome());
        assertEquals("987.654.321-00", participante.getCpf());
        assertEquals("1995-12-31", participante.getDt_nascimento());
        assertEquals("Feminino", participante.getSexo());
        assertEquals("INSCR999", participante.getInscricao());
        assertEquals(101L, participante.getId());
    }
}
