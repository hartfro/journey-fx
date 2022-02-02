package journey.core;
/**
 * Esta clase contiene la información y acciones relacionadas a los alimentos. Se utiliza en la clase InfoAlimentación.
 * @author Grupo 23
 * @version 30/01/2022
 */
public class Alimento {
    /**
     * Calorías por porción (1 porción = 100 gramos) del alimento
     */
    private float caloriasPorPorcion;
    /**
     * Nombre del alimento
     */
    private String nombre;
    /**
     * Tipo del alimento (LIQUIDO, VEGETAL, PROTEINA, CEREAL)
     */
    private TipoAlimento tipo;

    /**
     * Constructor para Alimento
     * @param nombre
     * @param caloriasPorPorcion
     * @param tipo
     */
    public Alimento(String nombre, float caloriasPorPorcion, TipoAlimento tipo) {
        this.nombre = nombre;
        this.caloriasPorPorcion = caloriasPorPorcion;
        this.tipo = tipo;
    }

    //Métodos públicos

    /**
     * Método que crea una cadena a partir de los datos dados
     * @return Cadena con nombre, tipo y calorías por porción de alimento
     */
    @Override
    public String toString() {
        return "Nombre: "+nombre+"\nTipo: "+tipo+"\nCalorias por porcion: "+caloriasPorPorcion;
    }

    // Getters and setters

    /**
     * Método que devuelve las calorías por porción de un alimento
     * @return caloriasPorPorcion
     */
    public float getCaloriasPorPorcion() {
        return caloriasPorPorcion;
    }

    /**
     * Método que agrega las calorías por porción de un alimento
     * @param caloriasPorPorcion
     */
    public void setCaloriasPorPorcion(float caloriasPorPorcion) {
        this.caloriasPorPorcion = caloriasPorPorcion;
    }

    /**
     * Método que devuelve el nombre de un alimento
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que agrega el nombre de un alimento
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que devuelve el tipo de alimento
     * @return tipo
     */
    public TipoAlimento getTipo() {
        return tipo;
    }

    /**
     * Método que agrega el tipo de alimento
     * @param tipo
     */
    public void setTipo(TipoAlimento tipo) {
        this.tipo = tipo;
    }
    
}
