package journey.core;

import java.time.LocalDate;
/**
 * Esta clase contiene la informaci�n y acciones relacionadas al registro de la informaci�n diaria. Se utiliza en Paciente.
 * @author Grupo 23
 * @version 30/01/2022
 */
public class InfoDia {
    //Atributos

    /**
     * Fecha de ingreso de la información.
     */
    private LocalDate fecha;
    /**
     * Emoción predominante del día (FELIZ, TRISTE, ENOJADO, ABURRIDO, CANSADO, TEMEROSO)
     */
    private Emocion emocion;
    /**
     * Peso del día (en kilogramos)
     */
    private float peso;
    /**
     * Altura del día (en centímetros)
     */
    private int altura;
    /**
     * Información sobre el ejercicio realizado en el día
     */
    private InfoEjercicio infoEjercicio;
    /**
     * Información sobre la alimentación del día
     */
    private InfoAlimentacion infoAlimentacion;

    /**
     * Constructor para InfoDia
     * @param peso
     * @param altura
     * @param emocion
     * @param infoEjercicio
     * @param infoAlimentacion
     */
    public InfoDia(float peso, int altura, Emocion emocion, InfoEjercicio infoEjercicio, InfoAlimentacion infoAlimentacion) {
        this.peso = peso;
        this.altura = altura;
        this.fecha = LocalDate.now();
        this.emocion = emocion;
        this.infoEjercicio = infoEjercicio;
        this.infoAlimentacion = infoAlimentacion;
    }
    /**
     * Constructor para InfoDia
     * @param fecha
     * @param peso
     * @param altura
     * @param emocion
     * @param infoEjercicio
     * @param infoAlimentacion
     */

    public InfoDia(LocalDate fecha, float peso, int altura, Emocion emocion, InfoEjercicio infoEjercicio, InfoAlimentacion infoAlimentacion) {
        this.peso = peso;
        this.altura = altura;
        this.fecha = fecha;
        this.emocion = emocion;
        this.infoEjercicio = infoEjercicio;
        this.infoAlimentacion = infoAlimentacion;
    }

    // Métodos públicos

    /**
     * Método para dar formato a la fecha.
     * @return fecha en la forma de "dd-MM-yyyy"
     */
    @Override
    public String toString() {
        return this.fecha.format(Constantes.DATE_FORMATTER);
    }

    /**
     * Método para calcular el índice de masa corporal (IMC) a partir de la altura y el peso
     * @return IMC (peso/altura*altura)
     */
    public float calcularImc() {
        float alturaM = (float) this.getAltura() / 100f;

        return (this.getPeso()) / (alturaM * alturaM);
    }

    /**
     * Método para dar el diagnóstico a partir del resultado de calcular IMC
     * @return cadena de caracteres con el diagnóstico de IMC
     */
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

    /**
     * Método para dar el diagnóstico a partir de la cantidad de ejercicio realizada
     * @return cadena de caracteres con el diagnóstico de ejercicio
     */
    public String diagnosticoEjercicio() {
        return this.infoEjercicio.diagnostico();
    }

    /**
     * Método para dar el diagnóstico a partir de la alimentación
     * @param minimoRecomendado
     * @param maximoRecomendado
     * @return cadena de caracteres con el diagnóstico de alimentación
     */
    public String diagnosticoAlimentacion(float minimoRecomendado, float maximoRecomendado) {
        return this.infoAlimentacion.diagnostico(minimoRecomendado, maximoRecomendado);
    }

    // Getters and setters

    /**
     * Método para dar la fecha
     * @return fecha
     */

    public LocalDate getFecha() {
        return fecha;
    }


    /**
     * Método para dar la emoción predominante
     * @return emocion
     */
    public Emocion getEmocion() {
        return emocion;
    }

    /**
     * Método para agregar la emoción predominante
     * @param emocion
     */
    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    /**
     * Método para dar el peso (kg)
     * @return peso
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Método para agregar el peso (kg)
     * @param peso
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /**
     * Método para dar la altura (cm)
     * @return altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Método para agregar la altura (cm)
     * @param altura
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Método para dar la información de ejercicio
     * @return infoEjercicio
     */
    public InfoEjercicio getInfoEjercicio() {
        return infoEjercicio;
    }

    /**
     * Método para agregar la información de ejercicio
     * @param infoEjercicio
     */
    public void setInfoEjercicio(InfoEjercicio infoEjercicio) {
        this.infoEjercicio = infoEjercicio;
    }

    /**
     * Método para dar la información de alimentación
     * @return InfoAlimentacion
     */
    public InfoAlimentacion getInfoAlimentacion() {
        return infoAlimentacion;
    }

    /**
     * Método para agregar la información de alimentación
     * @param infoAlimentacion
     */
    public void setInfoAlimentacion(InfoAlimentacion infoAlimentacion) {
        this.infoAlimentacion = infoAlimentacion;
    }
    
}
