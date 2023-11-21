import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A classe EstacionamentoTeste contém testes unitários para a classe Estacionamento.
 */
public class EstacionamentoTeste {
    private Estacionamento estacionamento; 

    /**
     * Configuração inicial antes de cada teste.
     */
    @Before
    public void setUp(){
        estacionamento = new Estacionamento("Meu Estacionamento", 5, 5);
    }

    /**
     * Testa se o construtor inicializa corretamente o estacionamento.
     */
    @Test
    public void testarConstrutor(){
        assertEquals("Meu Estacionamento", estacionamento.getNome());
        assertEquals(5, estacionamento.getQuantFileiras());
        assertEquals(5, estacionamento.getVagasPorFileira());
    }

    /**
     * Testa a adição de um veículo a um cliente no estacionamento.
     */
    @Test
    public void testAddVeiculo() {
        Cliente cliente = new Cliente("Cliente1", "ID1");
        Veiculo veiculo = new Veiculo("ABC123");

        try {
            estacionamento.addCliente(cliente);
            estacionamento.addVeiculo("ABC123", "ID1");
            assertTrue(cliente.possuiVeiculo("ABC123"));
        } catch (ExcecaoVeiculoJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    /**
     * Testa a adição de um cliente ao estacionamento.
     */
    @Test
    public void testAddCliente() {
        Cliente cliente = new Cliente("Cliente1", "ID1");

        try {
            estacionamento.addCliente(cliente);
            assertTrue(estacionamento.getId().contains(cliente));
        } catch (ExcecaoClienteJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    /**
     * Testa se as vagas são geradas corretamente.
     */
    @Test
    public void testGerarVagas() {
        Vaga[] vagas = estacionamento.getVagas();
        int expectedTotalVagas = 5 * 15; // 5 fileiras com 5 vagas por fileira

        assertEquals(expectedTotalVagas, vagas.length);

        // Verifica se as vagas foram criadas com os valores esperados
        int fila = 0;
        int numero = 1;
        for (Vaga vaga : vagas) {
            char filaEsperada = (char) ('A' + fila);
            int numeroEsperado = numero;

            assertEquals(filaEsperada, vaga.getFila());
            assertEquals(numeroEsperado, vaga.getNumero());

            // Atualiza os valores esperados para a próxima vaga
            if (numero < 5) {
                numero++;
            } else {
                fila++;
                numero = 1;
            }
        }
    }

    /**
     * Testa o cálculo do valor médio por uso no estacionamento.
     */
    @Test
    public void testValorMedioPorUso() {
        Cliente cliente1 = new Cliente("Cliente1", "ID1");
        Cliente cliente2 = new Cliente("Cliente2", "ID2");

        cliente1.addVeiculo(new Veiculo("ABC123"));
        cliente1.addVeiculo(new Veiculo("XYZ789"));
        cliente2.addVeiculo(new Veiculo("DEF456"));

        cliente1.getVeiculo("ABC123").sair("ABC123");
        cliente1.getVeiculo("XYZ789").sair("XYZ789");
        cliente2.getVeiculo("DEF456").sair("DEF456");

        try {
            estacionamento.addCliente(cliente1);
            estacionamento.addCliente(cliente2);

            double valorMedioEsperado = (cliente1.arrecadadoTotal() + cliente2.arrecadadoTotal()) / (cliente1.totalDeUsos() + cliente2.totalDeUsos());
            assertEquals(valorMedioEsperado, estacionamento.valorMedioPorUso(), 0.01);
        } catch (ExcecaoClienteJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    /**
     * Testa se o método top5Clientes retorna os nomes esperados com base na arrecadação.
     */
    @Test
    public void testTop5Clientes() {
        Cliente cliente1 = new Cliente("Cliente1", "ID1");
        Cliente cliente2 = new Cliente("Cliente2", "ID2");
        Cliente cliente3 = new Cliente("Cliente3", "ID3");
        Cliente cliente4 = new Cliente("Cliente4", "ID4");
        Cliente cliente5 = new Cliente("Cliente5", "ID5");

        cliente1.addVeiculo(new Veiculo("ABC123"));
        cliente2.addVeiculo(new Veiculo("XYZ789"));
        cliente3.addVeiculo(new Veiculo("DEF456"));
        cliente4.addVeiculo(new Veiculo("GHI789"));
        cliente5.addVeiculo(new Veiculo("JKL012"));

        cliente1.getVeiculo("ABC123").sair("ABC123"); // Mês 1
        cliente2.getVeiculo("XYZ789").sair("XYZ789"); // Mês 2
        cliente3.getVeiculo("DEF456").sair("DEF456"); // Mês 3
        cliente4.getVeiculo("GHI789").sair("GHI789"); // Mês 4
        cliente5.getVeiculo("JKL012").sair("JKL012"); // Mês 5

        try {
            estacionamento.addCliente(cliente1);
            estacionamento.addCliente(cliente2);
            estacionamento.addCliente(cliente3);
            estacionamento.addCliente(cliente4);
            estacionamento.addCliente(cliente5);

            assertEquals("[Cliente3, Cliente2, Cliente1, Cliente4, Cliente5]", estacionamento.top5Clientes(3));
        } catch (ExcecaoClienteJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    /**
     * Testa se é possível estacionar um veículo em uma vaga disponível.
     */
    @Test
    public void testEstacionar() {
        Cliente cliente = new Cliente("Cliente1", "ID1");
        Veiculo veiculo = new Veiculo("ABC123");

        try {
            estacionamento.addCliente(cliente);
            estacionamento.estacionar("ABC123", cliente);
        } catch (ExcecaoClienteJaCadastrado | ExcecaoVeiculoJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

  
}
