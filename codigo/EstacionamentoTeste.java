import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoTeste {
    private Estacionamento estacionamento;

    @BeforeEach
    public void setUp() {
        estacionamento = criarEstacionamentoDeExemplo();
    }

    @Test
    public void testAddCliente() {
        int tamanhoInicial = estacionamento.getClientes().size();
        estacionamento.addCliente("Novo Cliente", "ID3");
        int tamanhoFinal = estacionamento.getClientes().size();
        assertEquals(tamanhoInicial + 1, tamanhoFinal);
    }

    @Test
    public void testAddVagas() {
        int tamanhoInicial = estacionamento.getVagas().size();
        estacionamento.addVagas("Vaga3", true);
        int tamanhoFinal = estacionamento.getVagas().size();
        assertEquals(tamanhoInicial + 1, tamanhoFinal);
    }

    @Test
    public void testEstacionar() {
        Estacionamento status = estacionamento.estacionar("ID1");
        assertEquals(Estacionamento.EstacionamentoStatus.SUCCESS, status);

        // Teste de estacionar em vaga ocupada
        estacionamento.estacionar("ID2"); // Estaciona em outra vaga
        status = estacionamento.estacionar("ID1"); // Tenta estacionar novamente em ID1
        assertEquals(Estacionamento.EstacionamentoStatus.VAGA_OCUPADA, status);

        // Teste de estacionar veículo não encontrado
        status = estacionamento.estacionar("ID3");
        assertEquals(Estacionamento.EstacionamentoStatus.VEICULO_NAO_ENCONTRADO, status);

        // Teste de estacionar sem vagas disponíveis
        estacionamento.getVagas().forEach(vaga -> vaga.setDisponivel(false));
        status = estacionamento.estacionar("ID1");
        assertEquals(Estacionamento.EstacionamentoStatus.SEM_VAGAS_DISPONIVEIS, status);
    }

    @Test
    public void testSair() {
        double valorPago = estacionamento.sair("ID1");
        assertTrue(valorPago >= 0);

        // Teste de sair veículo não encontrado
        valorPago = estacionamento.sair("ID3");
        assertEquals(-1.0, valorPago);
    }

    @Test
    public void testTotalArrecadado() {
        double totalArrecadado = estacionamento.totalArrecadado();
        assertTrue(totalArrecadado >= 0);
    }

    @Test
    public void testArrecadacaoNoMes() {
        double arrecadacaoNoMes = estacionamento.arrecadacaoNoMes(10);
        assertTrue(arrecadacaoNoMes >= 0);
    }

    @Test
    public void testValorMedioPorUso() {
        double valorMedio = estacionamento.valorMedioPorUso();
        assertTrue(valorMedio >= -1.0); // Pode ser -1.0 se não houver usos
    }

    @Test
    public void testTop5Clientes() {
        String top5Clientes = estacionamento.top5Clientes(10);
        assertNotNull(top5Clientes);
    }

    private Estacionamento criarEstacionamentoDeExemplo() {
        Cliente[] clientes = new Cliente[2];
        clientes[0] = new Cliente("Cliente1", "ID1");
        clientes[1] = new Cliente("Cliente2", "ID2");

        List<Vaga> vagas = new ArrayList<>();
        Vaga vaga1 = new Vaga("Vaga1", true);
        Vaga vaga2 = new Vaga("Vaga2", true);
        vagas.add(vaga1);
        vagas.add(vaga2);

        return new Estacionamento("Meu Estacionamento", clientes, vagas, 2, 2);
    }
}
