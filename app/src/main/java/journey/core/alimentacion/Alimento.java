package journey.core.alimentacion;

public class Alimento {
    private String nombre;
    private float caloriasPorPorcion;
    private TipoAlimento tipo;

    public Alimento(String nombre, float caloriasPorPorcion, TipoAlimento tipo) {
        this.nombre = nombre;
        this.caloriasPorPorcion = caloriasPorPorcion;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    // Getters and setters

    public String getNombre() {
        return this.nombre;
    }

    public TipoAlimento getTipo() {
        return this.tipo;
    }

    public float getCaloriasPorPorcion() {
        return this.caloriasPorPorcion;
    }
}
