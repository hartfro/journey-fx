package journey.core;

import java.time.LocalDate;
/**
 *
 * @author admin
 */
public class InfoDia {
    private LocalDate fecha;
    private Emocion emocion;
    private float peso;
    private int altura;
    private InfoEjercicio infoEjercicio;
    private InfoAlimentacion infoAlimentacion;

    public InfoDia(LocalDate fecha, Emocion emocion, InfoEjercicio infoEjercicio, InfoAlimentacion infoAlimentacion) {
        this.fecha = fecha;
        this.emocion = emocion;
        this.infoEjercicio = infoEjercicio;
        this.infoAlimentacion = infoAlimentacion;
    }
    
    public float calcularImc() {
        float alturaM = (float) this.getAltura() / 100f;

        return (this.getPeso()) / (alturaM * alturaM);
    }
    
    public String diagnosticoIMC() {
        if (calcularImc() < 16)
            return "Peso insuficiente. Muy grave.";
        
        if (calcularImc()< 17)
            return "Peso bajo severo.";

        if (calcularImc() < 18.5)
            return "Peso bajo.";

        if (calcularImc() < 25)
            return "Peso normal.";

        if (calcularImc() < 30)
            return "Sobrepeso.";

        if (calcularImc() < 35)
            return "Obesidad grado I.";

        if (calcularImc() < 40)
            return "Obesidad grado II.";

        return "Obesidad grado III.";
    }
    
    public String diagnosticoEjercicio() {
        return this.infoEjercicio.diagnostico();
    }
    
    public String diagnosticoAlimentacion(float minimoRecomendado, float maximoRecomendado) {
        return this.infoAlimentacion.diagnostico(minimoRecomendado, maximoRecomendado);
    }

    // Getters and setters

    public LocalDate getFecha() {
        return fecha;
    }

    public Emocion getEmocion() {
        return emocion;
    }

    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public InfoEjercicio getInfoEjercicio() {
        return infoEjercicio;
    }

    public void setInfoEjercicio(InfoEjercicio infoEjercicio) {
        this.infoEjercicio = infoEjercicio;
    }

    public InfoAlimentacion getInfoAlimentacion() {
        return infoAlimentacion;
    }

    public void setInfoAlimentacion(InfoAlimentacion infoAlimentacion) {
        this.infoAlimentacion = infoAlimentacion;
    }
    
}
