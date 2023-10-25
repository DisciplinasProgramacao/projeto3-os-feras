import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UsoDeVagaTest {

    private UsoDeVaga uso; //atributo para armazenar uma instância de UsoDeVaga

    //método executado antes de cada teste
    @Before
    public void setUp() throws Exception {
        //criar uma vaga livre
        Vaga vaga = new Vaga(1, false);
        //criar um uso de vaga sem serviço
        uso = new UsoDeVaga(vaga);
    }

    //método executado após cada teste
    @After
    public void tearDown() throws Exception {
        //liberar o uso de vaga
        uso = null;
    }

    //método para testar se o valor pago é zero antes da saída
    @Test
    public void testValorPagoAntesDaSaida() {
        //verificar se o valor pago é zero
        Assert.assertEquals(0.0, uso.valorPago(), 0.01);
    }

    //método para testar se o valor pago é correto após a saída
    @Test
    public void testValorPagoAposASaida() throws Exception {
        //simular uma saída após 2 horas e meia de uso da vaga
        Thread.sleep(9000000); //esperar 2 horas e meia em milissegundos
        uso.sair(); //registrar a saída
        //verificar se o valor pago é igual a 10 reais (2 frações de 4 reais cada)
        Assert.assertEquals(10.0, uso.valorPago(), 0.01);
    }

    //método para testar se o valor pago é correto após a saída com um serviço de lavagem
    @Test
    public void testValorPagoAposASaidaComLavagem() throws Exception {
        //criar uma nova vaga livre
        Vaga vaga = new Vaga(2, false);
        //criar um novo uso de vaga com serviço de lavagem
        uso = new UsoDeVaga(vaga, UsoDeVaga.Servico.LAVAGEM);
        //simular uma saída após 3 horas de uso da vaga
        Thread.sleep(10800000); //esperar 3 horas em milissegundos
        uso.sair(); //registrar a saída
        //verificar se o valor pago é igual a 28 reais (20 reais da lavagem + 2 frações de 4 reais cada)
        Assert.assertEquals(28.0, uso.valorPago(), 0.01);
    }

    //método para testar se o valor pago é limitado ao valor máximo após a saída
    @Test
    public void testValorPagoAposASaidaComValorMaximo() throws Exception {
        //simular uma saída após 24 horas de uso da vaga
        Thread.sleep(86400000); //esperar 24 horas em milissegundos
        uso.sair(); //registrar a saída
        //verificar se o valor pago é igual a 50 reais (valor máximo)
        Assert.assertEquals(50.0, uso.valorPago(), 0.01);
    }

    //método para testar se uma exceção é lançada ao sair de uma vaga cujo uso já foi finalizado
    @Test(expected = UsoDeVagaException.class)
    public void testSairDeUmaVagaCujoUsoJaFoiFinalizado() throws Exception {
        //simular uma saída após 1 hora de uso da vaga
        Thread.sleep(3600000); //esperar 1 hora em milissegundos
        uso.sair(); //registrar a primeira saída
        uso.sair(); //tentar registrar a segunda saída
        //esperar que uma UsoDeVagaException seja lançada
    }

    //método para testar se uma exceção é lançada ao estacionar em uma vaga sem haver finalizado o uso anterior
    @Test(expected = UsoDeVagaException.class)
    public void testEstacionarEmUmaVagaSemHaverFinalizadoOUsoAnterior() throws Exception {
        //criar uma vaga ocupada
        Vaga vaga = new Vaga(3, true);
        //tentar criar um uso de vaga com essa vaga
        uso = new UsoDeVaga(vaga);
        //esperar que uma UsoDeVagaException seja lançada
    }

}
