    //classe para representar as exceções do uso de vaga
    public class UsoDeVagaException extends Exception {

        //construtor da classe
        public UsoDeVagaException(String message) {
            super(message); //repassar a mensagem para o construtor da superclasse
        }
    }
