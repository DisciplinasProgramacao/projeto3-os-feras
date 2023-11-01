//enum para representar os serviços disponíveis
	public enum Servico {
		MANOBRISTA(5.0), //sem tempo determinado
		LAVAGEM(20.0), //1 hora
		POLIMENTO(45.0); //2 horas

		private double precoPorHora; //preço por hora do serviço

		//construtor do enum
		private Servico(double precoPorHora) {
			this.precoPorHora = precoPorHora;
		}

		//método para obter o preço por hora do serviço
		public double getPrecoPorHora() {
			return this.precoPorHora;
		}
	}
