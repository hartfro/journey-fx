package journey.core.dia;

import java.time.LocalDate;

import journey.core.alimentacion.InfoAlimentacion;
import journey.core.ejercicio.InfoEjercicio;
import journey.core.paciente.Paciente;

public class InfoDia {
    private Paciente paciente;
    private LocalDate fecha;
    private Emocion emocion;
    private InfoEjercicio infoEjercicio;
    private InfoAlimentacion infoAlimentacion;

    public InfoDia(Paciente paciente, LocalDate fecha, Emocion emocion, InfoEjercicio infoEjercicio, InfoAlimentacion infoAlimentacion) {
        this.paciente = paciente;
        this.fecha = fecha;
        this.emocion = emocion;
        this.infoEjercicio = infoEjercicio;
        this.infoAlimentacion = infoAlimentacion;
    }

    // Getters and setters
    public Paciente getPaciente() {
        return this.paciente;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public Emocion getEmocion() {
        return this.emocion;
    }

    public InfoEjercicio getInfoEjercicio() {
        return this.infoEjercicio;
    }

    public InfoAlimentacion getInfoAlimentacion() {
        return this.infoAlimentacion;
    }
}
