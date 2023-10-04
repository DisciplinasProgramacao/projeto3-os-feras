public class Veiculo {
    private String placa;
    private UsodeVaga[] usos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsodeVaga[100];
    }

    public void estacionar(Vaga vaga) {
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] == null) {
                usos[i] = new UsodeVaga(vaga);
                break;
            }
        }
    }

    public double sair() {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null) {
                total += usos[i].getVaga().getPreco();
                usos[i] = null;
            }
        }
        return total;
    }

    public double totalArrecadado() {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null) {
                total += usos[i].getVaga().getPreco();
            }
        }
        return total;
    }

    public double arrecadadoNoMes(int mes) {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null && usos[i].getVaga().getMes() == mes) {
                total += usos[i].getVaga().getPreco();
            }
        }
        return total;
    }

    public int totalDeUsos() {
        int total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null) {
                total++;
            }
        }
        return total;
    }
}
