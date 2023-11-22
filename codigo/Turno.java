import java.time.LocalDateTime;
import java.time.LocalTime;

public enum Turno {
    MANHA("08:00", "12:00", 200.0),
    TARDE("12:01", "18:00", 200.0),
    NOITE("18:01", "23:59", 200.0);

    private LocalTime inicio;
    private LocalTime fim;

    Turno(String inicio, String fim, double valorTurno) {
        this.inicio = LocalTime.parse(inicio);
        this.fim = LocalTime.parse(fim);
    }


    public boolean isInTurno(LocalDateTime entrada, LocalDateTime saida) {
        LocalTime entradaTime = entrada.toLocalTime();
        LocalTime saidaTime = saida.toLocalTime();
        return !entradaTime.isBefore(this.inicio) && !saidaTime.isAfter(this.fim);
    }
}
