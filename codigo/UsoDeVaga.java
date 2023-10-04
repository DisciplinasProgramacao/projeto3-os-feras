import java.time.LocalDateTime;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;

	//construtor
	public UsoDeVaga(Vaga vaga) {
		this.vaga=vaga;
		this.entrada=LocalDateTime.now();//atribuir data e hora atual como entrada do cliente na vaga
		this.saida=null;
		this.valorPago=0.0;

	}
//registrar saida e retornar ao valor pago 
	public double sair() {
		this.saida=LocalDateTime.now();//atribuir data e hora atual como entrada do cliente na vaga
		this.valorPago=valorPago();//calcular valor pagopelo cliente
		vaga.setOcupada(false);//liberar vaga
		return this.valorPago;
	}

	//retornar valor pago pelo uso da vaga
	public double valorPago() {
		return this.valorPago;
	}

}
