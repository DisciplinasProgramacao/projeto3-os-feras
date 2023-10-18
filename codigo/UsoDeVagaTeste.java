import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsoDeVagaTeste {

    @Test
    public void testValorPagoSemServicoAdicional() {
        Vaga vaga = new Vaga(); // Supondo que temos uma classe Vaga
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, new ArrayList<>());
        usoDeVaga.setEntrada(LocalDateTime.now().minusHours(1)); // Supondo que o cliente entrou há 1 hora
        usoDeVaga.setSaida(LocalDateTime.now()); // O cliente está saindo agora
        double valorPago = usoDeVaga.sair();
        assertEquals(0 * UsoDeVaga.getFracaoUso() * UsoDeVaga.getValorFracao(), valorPago);
    }

    @Test
    public void testValorPagoComServicoAdicional() {
        Vaga vaga = new Vaga(); // Supondo que temos uma classe Vaga
        ArrayList<ServicoAdicional> servicos = new ArrayList<>();
        servicos.add(new Manobrista());
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, servicos);
        usoDeVaga.setEntrada(LocalDateTime.now().minusHours(1)); // Supondo que o cliente entrou há 1 hora
        usoDeVaga.setSaida(LocalDateTime.now()); // O cliente está saindo agora
        double valorPago = usoDeVaga.sair();
        assertEquals(0 * UsoDeVaga.getFracaoUso() * UsoDeVaga.getValorFracao() + 5.0, valorPago);
    }    

    @Test
    public void testAdicionarRemoverServico() {
        Vaga vaga = new Vaga(); // Supondo que temos uma classe Vaga
        ArrayList<ServicoAdicional> servicos = new ArrayList<>();
        ServicoAdicional manobrista = new Manobrista();
        servicos.add(manobrista);
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga, servicos);
        
        // Testar adicionar serviço
        ServicoAdicional lavagem = new Lavagem();
        usoDeVaga.adicionarServico(lavagem);
        assertEquals(2, usoDeVaga.getServicosAdicionais().size());

        // Testar remover serviço
        usoDeVaga.removerServico(manobrista);
        assertEquals(1, usoDeVaga.getServicosAdicionais().size());
    }
}
