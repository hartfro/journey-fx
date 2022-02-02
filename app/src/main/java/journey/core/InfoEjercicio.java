package journey.core;
/**
 * Esta clase contiene la información y acciones relacionadas al registro del ejercicio. Se utiliza en la clase InfoDia y Paciente.
 * @author Grupo 23
 * @version 30/01/2022
 */
public class InfoEjercicio {
    // Atributos

    /**
     * Intensidad del ejercicio (NULO, LEVE, MODERADO, FUERTE)
     */
    private IntensidadEjercicio intensidad;
    /**
     * Duración del ejercicio en minutos
     */
    private int tiempo;

    /**
     * Constructor para InfoEjercicio
     * @param tiempo
     * @param intensidad
     */
    public InfoEjercicio(int tiempo, IntensidadEjercicio intensidad) {
        this.tiempo = tiempo;
        this.intensidad = intensidad;
    }

    // Métodos públicos

    /**
     * Método que devuelve el diagnóstico de ejercicio, en base a la intensidad y duración
     * @return cadena de caracteres con el diagnóstico
     */
    public String diagnostico() {
        if (tiempo == 0)
            return "Debes realizar más ejercicio para mantenerte saludable.";

        if (tiempo < 30) {
            if (intensidad == IntensidadEjercicio.LEVE)
                return "Es una buena manera de iniciar.";
            if (intensidad == IntensidadEjercicio.MODERADO)
                return "Es una buena manera de iniciar. Solo recuerda no sobreesforzarte.";
            if (intensidad == IntensidadEjercicio.FUERTE)
                return "¡Ten cuidado! No te sobreesfuerces.";
        }

        if (tiempo <= 60) {
            if (intensidad == IntensidadEjercicio.LEVE)
                return "¡Tienes un buen ritmo! Si te sientes bien, ¡sigue así!";
            if (intensidad == IntensidadEjercicio.MODERADO)
                return "¡Continúa así! Mantienes un buen ritmo de ejercicio.";
            if (intensidad == IntensidadEjercicio.FUERTE)
                return "¡Ten cuidado! Procura bajar la intensidad.";
        }

        // Más de una hora
        if (intensidad == IntensidadEjercicio.LEVE)
            return "Si te sientes bien así, está correcto, siempre que no aumentes la intensidad.";
        if (intensidad == IntensidadEjercicio.MODERADO)
            return "¡Ten cuidado! Recuerda que calidad es antes que tiempo.";
        if (intensidad == IntensidadEjercicio.FUERTE)
            return "¡Detente ahora mismo! ¡Te estás haciendo daño!";

        return "Error de diagnóstico";
    }

    // Getters and setters

    /**
     * Método que devuelve la intensidad del ejercicio
     * @return intensidad
     */

    public IntensidadEjercicio getIntensidad() {
        return intensidad;
    }

    /**
     * Método que agrega la intensidad del ejercicio
     * @param intensidad
     */
    public void setIntensidad(IntensidadEjercicio intensidad) {
        this.intensidad = intensidad;
    }

    /**
     * Método que devuelve el tiempo de ejercicio realizado
     * @return tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * Método que agrega el tiempo de ejercicio realizado
     * @param tiempo
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
}
