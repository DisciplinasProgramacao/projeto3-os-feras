public class Veiculo {
    private String placa;
    private UsoDeVaga[] usos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[500];
    }

    public void estacionar(Vaga vaga) {
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] == null) {
                usos[i] = new UsoDeVaga(vaga);
                break;
            }
        }
    }

    public double sair() {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null) {
                total += usos[i].getVaga().getValorPago();
                usos[i] = null;
            }
        }
        return total;
    }

    public double totalArrecadado() {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null) {
                total += usos[i].getVaga().getValorPago();
            }
        }
        return total;
    }

    public double arrecadadoNoMes(int mes) {
        double total = 0;
        for (int i = 0; i < usos.length; i++) {
            if (usos[i] != null && usos[i].getVaga().getMes() == mes) {
                total += usos[i].getVaga().getValorPago();
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
