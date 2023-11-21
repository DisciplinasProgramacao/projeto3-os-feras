import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class TesteVeiculo {

    private Veiculo veiculo;

    @Before
    public void setUp() throws Exception {
        veiculo = new Veiculo("ABC-123");
    }

    @After
    public void tearDown() throws Exception {
        veiculo = null;
    }

    @Test
    public void testEstacionar() {
        Vaga vaga = new Vaga(1, 1);
        veiculo.estacionar(vaga);

        assertEquals(1, veiculo.totalDeUsos());
        assertEquals(0, veiculo.totalArrecadado(), 0.01);
    }

    @Test
    public void testSair() {
        Vaga vaga = new Vaga(2, 2);
        veiculo.estacionar(vaga);

        double valorPago = veiculo.sair();

        assertEquals(0, veiculo.totalDeUsos());
        assertEquals(0.0, valorPago, 0.01);
    }

    @Test
    public void testGerarRelatorioPorData() {
        // Criar um veículo
        Veiculo veiculo = new Veiculo("AAA-111");

        // Adicionar usos de vaga ao histórico
        UsoDeVaga uso1 = new UsoDeVaga(new Vaga(1, 1));
        uso1.setDataEntrada(new Date(1638000000L)); // Data: 01/12/2021
        uso1.setValorPago(15.0);
        veiculo.getHistoricoVagas().add(uso1);

        UsoDeVaga uso2 = new UsoDeVaga(new Vaga(1, 2));
        uso2.setDataEntrada(new Date(1640995200000L)); // Data: 01/01/2022
        uso2.setValorPago(20.0);
        veiculo.getHistoricoVagas().add(uso2);

        // Gerar relatório ordenado por data
        List<UsoDeVaga> relatorio = veiculo.gerarRelatorio(true);

        // Verificar se o relatório está ordenado por data crescente
        assertEquals(uso1, relatorio.get(0));
        assertEquals(uso2, relatorio.get(1));
    }

    @Test
    public void testGerarRelatorioPorValor() {
        // Criar um veículo
        Veiculo veiculo = new Veiculo("BBB-222");

        // Adicionar usos de vaga ao histórico
        UsoDeVaga uso1 = new UsoDeVaga(new Vaga(2, 1));
        uso1.setDataEntrada(new Date(1643673600000L)); // Data: 01/02/2022
        uso1.setValorPago(25.0);
        veiculo.getHistoricoVagas().add(uso1);

        UsoDeVaga uso2 = new UsoDeVaga(new Vaga(2, 2));
        uso2.setDataEntrada(new Date(1646092800000L)); // Data: 01/03/2022
        uso2.setValorPago(18.0);
        veiculo.getHistoricoVagas().add(uso2);

        // Gerar relatório ordenado por valor decrescente
        List<UsoDeVaga> relatorio = veiculo.gerarRelatorio(false);

        // Verificar se o relatório está ordenado por valor decrescente
        assertEquals(uso1, relatorio.get(0));
        assertEquals(uso2, relatorio.get(1));

    }
}
