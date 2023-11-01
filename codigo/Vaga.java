public class Vaga{

private String id;
private boolean disponivel;

public Vaga(int fila, int numero) {
	// Construtor que gera um id a partir da fila e do número.
	this.id = "F" + fila + "N" + numero;
	this.disponivel = true; 
}

public Vaga(String string) {
}

public Vaga() {
}

public boolean estacionar() {
	// Método que tenta estacionar um veículo na vaga.
	if (this.disponivel) { 
		this.disponivel = false; 
		return true; 
	} else { 
		return false; 
	}
}

public boolean sair() {
	// Método que tenta sair de uma vaga ocupada.
	if (!this.disponivel) { 
		this.disponivel = true; 
		return true; 
	} else { 
		return false; 
	}
}

public boolean disponivel() {
	return disponivel; // Retorna o estado atual da vaga.
}

public boolean isOcupada() {
    return true;
}

public void setOcupada(boolean b) {
}


// Método que altera o estado da vaga de acordo com o parâmetro.
public void setDisponivel(boolean disponivel) {
	this.disponivel = disponivel; // Atribui o valor do parâmetro ao atributo disponivel.
}

}
