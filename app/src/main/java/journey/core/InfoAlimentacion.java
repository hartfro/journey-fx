package journey.core;

import java.util.HashMap;
/**
 * Esta clase contiene la información y acciones relacionadas al registro de la alimentación. Se utiliza en la clase InfoDia.
 * Contiene la información de las varias "comidas" del día (desayuno, almuerzo,
 * merienda).
 *
 * Cada comida contiene la cantidad de porciones consumidas de cada alimento,
 * almacenadas en un {@link HashMap}.
 *
 * @author Grupo 23
 * @version 30/01/2022
 */

public class InfoAlimentacion {

    /**
     * HashMaps con información de una "comida" (desayuno, almuerzo, merienda).
     *
     * Contiene la cantidad de porciones consumidas de cada alimento.
     *
     * Extiende a un {@code HashMap<Alimento, Integer>}.
     */
    private HashMap<Alimento, Integer> desayuno = new HashMap<Alimento, Integer>();
    private HashMap<Alimento, Integer> almuerzo = new HashMap<Alimento, Integer>();
    private HashMap<Alimento, Integer> merienda = new HashMap<Alimento, Integer>();

    public InfoAlimentacion(HashMap<Alimento, Integer> desayuno, HashMap<Alimento, Integer> almuerzo,
            HashMap<Alimento, Integer> merienda) {
        this.desayuno = desayuno;
        this.almuerzo = almuerzo;
        this.merienda = merienda;
    }

    // Métodos públicos

    /**
     * Método para calcular las calorías totales consumidas
     * Se consideran tres comidas del días (desayuno, almuerzo, merienda)
     * @return calorías totales
     */
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

    /**
     * Método que devuelve el diagnóstico en base a las calorías totales consumidas y el rango de calorías recomendado
     * @param minimoRecomendado
     * @param maximoRecomendado
     * @return cadena de caracteres con el diagnóstico
     */
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

    /**
     * Método para agregar los alimentos y porciones a la categoría desayuno
     * @param alimento
     * @param porciones
     */
    public void agregarADesayuno(Alimento alimento, int porciones) {
        this.desayuno.put(alimento, porciones);
    }

    /**
     * Método para agregar los alimentos y porciones a la categoría almuerzo
     * @param alimento
     * @param porciones
     */
    public void agregarAAlmuerzo(Alimento alimento, int porciones) {
        this.almuerzo.put(alimento, porciones);
    }

    /**
     * Método para agregar los alimentos y porciones a la categoría merienda
     * @param alimento
     * @param porciones
     */
    public void agregarAMerienda(Alimento alimento, int porciones) {
        this.merienda.put(alimento, porciones);
    }

    //Getters

    /**
     * Método que devuelve la información del HashMap de desayuno
     * @return desayuno
     */
    public HashMap<Alimento, Integer> getDesayuno() {
        return desayuno;
    }

    /**
     * Método que devuelve la información del HashMap de almuerzo
     * @return almuerzo
     */
    public HashMap<Alimento, Integer> getAlmuerzo() {
        return almuerzo;
    }

    /**
     * Método que devuelve la información del HashMap de merienda
     * @return merienda
     */
    public HashMap<Alimento, Integer> getMerienda() {
        return merienda;
    }
}
