package journey.core.ejercicio;

public class InfoEjercicio {
    /**Duración del ejercicio en minutos.*/
    private int tiempo;
    private IntensidadEjercicio intensidad;

    public InfoEjercicio(int tiempo, IntensidadEjercicio intensidad) {
        this.tiempo = tiempo;
        this.intensidad = intensidad;
    }

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

    public int getTiempo() {
        return tiempo;
    }

    public IntensidadEjercicio getIntensidad() {
        return intensidad;
    }

}
