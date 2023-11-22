import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private Servico servico; //novo atributo
	private Cliente cliente; //novo atributo

	//construtor original
	public UsoDeVaga(Vaga vaga, Cliente cliente) throws UsoDeVagaException {
        //verificar se a vaga está ocupada
        if (vaga.isOcupada()) {
            //lançar uma exceção com a mensagem adequada
            throw new UsoDeVagaException("Estacionar em uma vaga sem haver finalizado o uso anterior");
        }
		this.vaga=vaga;
		this.entrada=LocalDateTime.now();//atribuir data e hora atual como entrada do cliente na vaga
		this.saida=null;
		this.valorPago=0.0;
		this.servico=null; //sem serviço
		this.cliente=cliente; //atribuir o cliente

	}

	//construtor sobrecarregado que recebe um serviço
	public UsoDeVaga(Vaga vaga, Cliente cliente, Servico servico) throws UsoDeVagaException {
		this(vaga, cliente); //chamar o construtor original
		this.servico=servico; //atribuir o serviço
	}

//registrar saida e retornar ao valor pago 
	public double sair() throws UsoDeVagaException {
        //verificar se a saída já foi registrada
        if (this.saida != null) {
            //lançar uma exceção com a mensagem adequada
            throw new UsoDeVagaException("Sair de uma vaga cujo uso já foi finalizado");
        }
		this.saida=LocalDateTime.now();//atribuir data e hora atual como entrada do cliente na vaga
		this.valorPago=valorPago();//calcular valor pagopelo cliente
		vaga.setOcupada(false);//liberar vaga
		return this.valorPago;
	}

	//retornar valor pago pelo uso da vaga
	public double valorPago() {
		
        //calcular o tempo de uso da vaga em horas
        long tempo = ChronoUnit.HOURS.between(entrada, saida);

        //se houver um serviço escolhido, adicionar o seu preço ao valor pago
        if (servico != null) {
            this.valorPago += servico.getPrecoPorHora();
        }

        //se o tempo de uso for menor que uma fração, cobrar apenas a fração
        if (tempo < FRACAO_USO) {
            this.valorPago += VALOR_FRACAO;
        }
        //se o tempo de uso for maior que uma fração, cobrar por cada fração usada até o valor máximo
        else {
            this.valorPago += Math.min((tempo / FRACAO_USO) * VALOR_FRACAO, VALOR_MAXIMO);
        }

        //se o cliente for de turno e estiver no seu turno, não cobrar nada
        if (cliente.getTurno().equals("turno") && cliente.getTurno().isInTurno(entrada, saida)) {
            this.valorPago = 0.0;
        }
        //se o cliente for mensalista, não cobrar nada
        else if (cliente.getTurno().equals("mensalista")) {
            this.valorPago = 0.0;
        }

        return this.valorPago;

    }

}
