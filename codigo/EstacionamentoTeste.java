import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class EstacionamentoTeste {
    private Estacionamento estacionamento; 
    @Before
    public void setUp(){
        Estacionamento estacionamento = new Estacionamento("Meu Estacionamento",5,5);
    }

    @Test
    public void testarConstrutor(){
        assertEquals("Meu Estacionamento", estacionamento.getNome());
        assertEquals(5, estacionamento.getQuantFileiras());
        assertEquals(5, estacionamento.getVagasPorFileira());
    }

    @Test
    public void testAddVeiculo() {
        Cliente cliente = new Cliente("Cliente1", "ID1"); // Crie um cliente
        Veiculo veiculo = new Veiculo("ABC123"); // Crie um veículo

        try {
            estacionamento.addCliente(cliente); // Adicione o cliente ao estacionamento
            estacionamento.addVeiculo("ABC123", "ID1"); // Adicione o veículo ao cliente no estacionamento
            assertTrue(cliente.possuiVeiculo("ABC123")); // Verifique se o veículo foi adicionado corretamente ao cliente
        } catch (ExcecaoVeiculoJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testAddCliente() {
        Cliente cliente = new Cliente("Cliente1", "ID1"); // Crie um cliente

        try {
            estacionamento.addCliente(cliente); // Adicione o cliente ao estacionamento
            assertTrue(estacionamento.getId().contains(cliente)); // Verifique se o cliente foi adicionado corretamente ao estacionamento
        } catch (ExcecaoClienteJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testGerarVagas() {
        Vaga[] vagas = estacionamento.getVagas();
        int expectedTotalVagas = 5 * 15; // 5 fileiras com 5 vagas por fileira

        assertEquals(expectedTotalVagas, vagas.length); // Verifica se o número total de vagas está correto

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

        @Test
    public void testValorMedioPorUso() {
        Cliente cliente1 = new Cliente("Cliente1", "ID1");
        Cliente cliente2 = new Cliente("Cliente2", "ID2");

        // Adicione alguns usos para os clientes
        cliente1.addVeiculo(new Veiculo("ABC123"));
        cliente1.addVeiculo(new Veiculo("XYZ789"));
        cliente2.addVeiculo(new Veiculo("DEF456"));

        // Simule valores arrecadados pelos clientes
        cliente1.getVeiculo("ABC123").sair("ABC123");
        cliente1.getVeiculo("XYZ789").sair("XYZ789");
        cliente2.getVeiculo("DEF456").sair("DEF456");

        try {
            estacionamento.addCliente(cliente1);
            estacionamento.addCliente(cliente2);

            double valorMedioEsperado = (cliente1.arrecadadoTotal() + cliente2.arrecadadoTotal()) / (cliente1.totalDeUsos() + cliente2.totalDeUsos());
            assertEquals(valorMedioEsperado, estacionamento.valorMedioPorUso(), 0.01); // Use delta para lidar com arredondamento
        } catch (ExcecaoClienteJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }
    
    @Test
    public void testTop5Clientes() {
        Cliente cliente1 = new Cliente("Cliente1", "ID1");
        Cliente cliente2 = new Cliente("Cliente2", "ID2");
        Cliente cliente3 = new Cliente("Cliente3", "ID3");
        Cliente cliente4 = new Cliente("Cliente4", "ID4");
        Cliente cliente5 = new Cliente("Cliente5", "ID5");

        // Adicione alguns usos para os clientes em diferentes meses
        cliente1.addVeiculo(new Veiculo("ABC123"));
        cliente2.addVeiculo(new Veiculo("XYZ789"));
        cliente3.addVeiculo(new Veiculo("DEF456"));
        cliente4.addVeiculo(new Veiculo("GHI789"));
        cliente5.addVeiculo(new Veiculo("JKL012"));

        // Simule valores arrecadados pelos clientes em diferentes meses
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

            // Verifique se o método top5Clientes retorna os nomes esperados com base na arrecadação no mês 3
            assertEquals("[Cliente3, Cliente2, Cliente1, Cliente4, Cliente5]", estacionamento.top5Clientes(3));
        } catch (ExcecaoClienteJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testEstacionar() {
        Cliente cliente = new Cliente("Cliente1", "ID1");
        Veiculo veiculo = new Veiculo("ABC123");

        try {
            estacionamento.addCliente(cliente);
            cliente.addVeiculo(veiculo);

            // Simule a disponibilidade de uma vaga
            estacionamento.getVagas()[0].setDisponivel(true);

            // Estacione o veículo
            estacionamento.estacionar("ABC123");

            // Verifique se a vaga foi ocupada corretamente
            assertFalse(estacionamento.getVagas()[0].disponivel());
        } catch (ExcecaoClienteJaCadastrado | ExcecaoVeiculoJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testSair() {
        Cliente cliente = new Cliente("Cliente1", "ID1");
        Veiculo veiculo = new Veiculo("ABC123");

        try {
            estacionamento.addCliente(cliente);
            cliente.addVeiculo(veiculo);

            // Simule a ocupação de uma vaga
            estacionamento.getVagas()[0].setDisponivel(false);

            // Simule a saída do veículo
            double valorSaida = estacionamento.sair("ABC123");

            // Verifique se a vaga está disponível
            assertTrue(estacionamento.getVagas()[0].disponivel());

            // Verifique se o valor de saída é maior que zero (indicando que houve cobrança)
            assertTrue(valorSaida > 0);
        } catch (ExcecaoClienteJaCadastrado | ExcecaoVeiculoJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testTotalArrecadado() {
        Cliente cliente1 = new Cliente("Cliente1", "ID1");
        Cliente cliente2 = new Cliente("Cliente2", "ID2");
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("XYZ789");

        try {
            estacionamento.addCliente(cliente1);
            estacionamento.addCliente(cliente2);
            cliente1.addVeiculo(veiculo1);
            cliente2.addVeiculo(veiculo2);

            // Simule a entrada e saída de veículos e cobrança
            estacionamento.estacionar("ABC123");
            estacionamento.estacionar("XYZ789");
            estacionamento.sair("ABC123");
            estacionamento.sair("XYZ789");

            // Verifique se o total arrecadado corresponde às cobranças
            assertEquals(veiculo1.totalArrecadado() + veiculo2.totalArrecadado(), estacionamento.totalArrecadado(), 0.01);
        } catch (ExcecaoClienteJaCadastrado | ExcecaoVeiculoJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testArrecadacaoNoMes() {
        Cliente cliente1 = new Cliente("Cliente1", "ID1");
        Cliente cliente2 = new Cliente("Cliente2", "ID2");
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("XYZ789");

        try {
            estacionamento.addCliente(cliente1);
            estacionamento.addCliente(cliente2);
            cliente1.addVeiculo(veiculo1);
            cliente2.addVeiculo(veiculo2);

            // Simule a entrada e saída de veículos e cobrança
            estacionamento.estacionar("ABC123");
            estacionamento.estacionar("XYZ789");
            estacionamento.sair("ABC123");
            estacionamento.sair("XYZ789");

            // Defina o mês de referência para a arrecadação
            int mes = 10;

            // Verifique se a arrecadação no mês corresponde às cobranças
            assertEquals(veiculo1.arrecadadoNoMes(mes) + veiculo2.arrecadadoNoMes(mes), estacionamento.arrecadacaoNoMes(mes), 0.01);
        } catch (ExcecaoClienteJaCadastrado | ExcecaoVeiculoJaCadastrado e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }
}
