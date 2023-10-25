import org.junit.Test;
import static org.junit.Assert.*;

public class TesteVaga {

    @Test
    public void testEstacionar() {
        // Cria uma vaga com id F1N1
        Vaga v = new Vaga(1, 1);
        // Verifica se a vaga está disponível
        assertTrue(v.disponivel());
        // Estaciona um veículo na vaga
        assertTrue(v.estacionar());
        // Verifica se a vaga está ocupada
        assertFalse(v.disponivel());
    }

    @Test
    public void testSair() {
        // Cria uma vaga com id F2N3
        Vaga v = new Vaga(2, 3);
        // Estaciona um veículo na vaga
        v.estacionar();
        // Verifica se a vaga está ocupada
        assertFalse(v.disponivel());
        // Sai da vaga
        assertTrue(v.sair());
        // Verifica se a vaga está disponível
        assertTrue(v.disponivel());
    }
}
