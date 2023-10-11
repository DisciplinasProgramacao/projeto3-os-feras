import org.junit.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class UsoDeVagaTeste {

    @Test
    public void testSair() {
        Vaga vaga = new Vaga(); // Crie uma instância de Vaga
        UsoDeVaga uso = new UsoDeVaga(vaga);
        
        // Certifique-se de que a vaga está ocupada após a entrada
        assertTrue(vaga.isOcupada());

        // Simule uma pausa curta antes de sair
        try {
            Thread.sleep(1000); // Aguarde 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalDateTime saida = LocalDateTime.now();
        Duration tempoEstacionado = Duration.between(uso.getEntrada(), saida);
        double valorEsperado = tempoEstacionado.getSeconds() * UsoDeVaga.VALOR_FRACAO;

        double valorPago = uso.sair();

        // Certifica se a vaga foi liberada
        assertFalse(vaga.isOcupada());

        // Verifique se o valor pago corresponde ao cálculo esperado
        assertEquals(valorEsperado, valorPago, 0.01); // Tolerância de 0.01 para lidar com arredondamento de números de ponto flutuante
    }
    
    @Test
    public void testValorPago() {
        Vaga vaga = new Vaga(); // Crie uma instância de Vaga
        UsoDeVaga uso = new UsoDeVaga(vaga);
        
        // Certifique-se de que o valor pago inicial é 0.0
        assertEquals(0.0, uso.valorPago(), 0.01); // Tolerância de 0.01

        // Simule uma pausa curta antes de sair
        try {
            Thread.sleep(1000); // Aguarde 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double valorPago = uso.sair();

        // Verifique se o valor pago é o mesmo que o valor retornado pelo método valorPago()
        assertEquals(valorPago, uso.valorPago(), 0.01); // Tolerância de 0.01
    }
}
