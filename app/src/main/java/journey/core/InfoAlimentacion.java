package journey.core;
import java.util.HashMap;

public class InfoAlimentacion {

    /**
     * Información de una "comida" (desayuno, almuerzo, merienda).
     *
     * Contiene la cantidad de porciones consumidas de cada alimento.
     *
     * Extiende a un {@code HashMap<Alimento, Integer>}.
     */
    private HashMap<Alimento, Integer> desayuno = new HashMap<Alimento, Integer>();
    private HashMap<Alimento, Integer> almuerzo = new HashMap<Alimento, Integer>();
    private HashMap<Alimento, Integer> merienda = new HashMap<Alimento, Integer>();

    public float caloriasTotales() {
        float resultado = 0;

        for (var alimento : desayuno.keySet()) {
            int porciones = desayuno.get(alimento);

            resultado += alimento.getCaloriasPorPorcion() * porciones;
        }

        for (var alimento : almuerzo.keySet()) {
            int porciones = almuerzo.get(alimento);

            resultado += alimento.getCaloriasPorPorcion() * porciones;
        }

        for (var alimento : merienda.keySet()) {
            int porciones = merienda.get(alimento);

            resultado += alimento.getCaloriasPorPorcion() * porciones;
        }

        return resultado;
    }

    public String diagnostico(float minimoRecomendado, float maximoRecomendado) {
        var caloriasTotales = this.caloriasTotales();

        if (caloriasTotales < minimoRecomendado) {
            return "Tienes una alimentación desbalanceada. Procura consumir más calorías.";
        }
        if (caloriasTotales > maximoRecomendado) {
            return "Tienes una alimentación desbalanceada. Procura consumir menos calorías.";
        }

        return "¡Felicidades! Tu alimentación está dentro del rango de consumo de calorías recomendadas.";
    }
    /*TODO: metodos de agregar solicitados por la profe, Esta clase tiene constructor??*/
    public void agregarDesayuno(){
        //LLENAR
    }
    
    public void agregarAlmuerzo(){
        //LLENAR
    }
    
    public void agregarMerienda(){
        //LLENAR
    }
    //Getters and setters
    public HashMap<Alimento, Integer> getDesayuno() {
        return desayuno;
    }

    public HashMap<Alimento, Integer> getAlmuerzo() {
        return almuerzo;
    }

    public HashMap<Alimento, Integer> getMerienda() {
        return merienda;
    }
}