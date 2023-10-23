import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteCliente {

    private Cliente cliente;
    private Veiculo veiculo1;
    private Veiculo veiculo2;

    @Before
    public void setUp() {
        cliente = new Cliente("John Doe", "123456");
        veiculo1 = new Veiculo("ABC-1234", "Ford", "Fiesta", 2015, 100.0);
        veiculo2 = new Veiculo("DEF-5678", "Chevrolet", "Onix", 2018, 150.0);
    }

    @Test
    public void testAddVeiculo() {
        cliente.addVeiculo(veiculo1);
        assertEquals(1, cliente.getVeiculos().size());
    }

    @Test
    public void testPossuiVeiculo() {
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(veiculo1, cliente.possuiVeiculo("ABC-1234"));
        assertEquals(veiculo2, cliente.possuiVeiculo("DEF-5678"));
        assertNull(cliente.possuiVeiculo("GHI-9012"));
    }

    @Test
    public void testTotalDeUsos() {
        veiculo1.setNumUsos(5);
        veiculo2.setNumUsos(10);
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(15, cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        veiculo1.setArrecadacao(50.0);
        veiculo2.setArrecadacao(100.0);
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(50.0, cliente.arrecadadoPorVeiculo("ABC-1234"), 0.0);
        assertEquals(100.0, cliente.arrecadadoPorVeiculo("DEF-5678"), 0.0);
        assertEquals(0.0, cliente.arrecadadoPorVeiculo("GHI-9012"), 0.0);
    }

    @Test
    public void testArrecadadoTotal() {
        veiculo1.setArrecadacao(50.0);
        veiculo2.setArrecadacao(100.0);
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(150.0, cliente.arrecadadoTotal(), 0.0);
    }

    @Test
    public void testArrecadadoNoMes() {
        veiculo1.setArrecadacaoNoMes(10, 50.0);
        veiculo2.setArrecadacaoNoMes(10, 100.0);
        veiculo2.setArrecadacaoNoMes(11, 200.0);
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(150.0, cliente.arrecadadoNoMes(10), 0.0);
        assertEquals(200.0, cliente.arrecadadoNoMes(11), 0.0);
        assertEquals(0.0, cliente.arrecadadoNoMes(12), 0.0);
    }
}
