import org.junit.Test;
import static org.junit.Assert.*;

public class TesteVeiculo {

    @Test
    public void testEstacionarVeiculoUmaVez() {
        // Teste para verificar o estacionamento de um veículo uma vez.
        Veiculo v = new Veiculo("ABC-1234");
        Vaga vg = new Vaga(10, 1);
        v.estacionar(vg);

        // Verifica se o número total de usos é 1 e o total arrecadado é R$10.0.
        assertEquals(1, v.totalDeUsos());
        assertEquals(10.0, v.totalArrecadado(), 0.01);
    }

    @Test
    public void testEstacionarVeiculoDuasVezesSair() {
        // Teste para verificar o estacionamento de um veículo duas vezes e, em seguida, sair.
        Veiculo v = new Veiculo("XYZ-5678");
        Vaga vg1 = new Vaga(5, 2);
        Vaga vg2 = new Vaga(7, 2);
        v.estacionar(vg1);
        v.estacionar(vg2);

        // Verifica se o número total de usos é 2 e o total arrecadado é R$12.0.
        assertEquals(2, v.totalDeUsos());
        assertEquals(12.0, v.totalArrecadado(), 0.01);

        // Após o veículo sair, verifica se o total arrecadado é reduzido para R$0.0 e o número total de usos retorna a 0.
        assertEquals(12.0, v.sair(), 0.01);
        assertEquals(0, v.totalDeUsos());
    }

    @Test
    public void testEstacionarQuandoEstacionamentoEstiverCheio() {
        // Teste para verificar o comportamento quando se tenta estacionar um veículo quando o estacionamento está cheio.
        Veiculo v = new Veiculo("LMN-4321");
        for (int i = 0; i < 500; i++) {
            Vaga vg = new Vaga(5, 3);
            v.estacionar(vg);
        }

        // Tenta estacionar o veículo novamente e verifica se o número total de usos permanece em 500.
        Vaga vg = new Vaga(8, 4);
        v.estacionar(vg);
        assertEquals(500, v.totalDeUsos());
    }
}
