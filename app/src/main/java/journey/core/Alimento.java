package journey.core;

public class Alimento {
    private float caloriasPorPorcion;
    private String nombre;
    private TipoAlimento tipo;

    public Alimento(String nombre, float caloriasPorPorcion, TipoAlimento tipo) {
        this.nombre = nombre;
        this.caloriasPorPorcion = caloriasPorPorcion;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Nombre: "+nombre+"\nTipo: "+tipo+"\nCalorias por porcion: "+caloriasPorPorcion;
    }

    // Getters and setters

    public float getCaloriasPorPorcion() {
        return caloriasPorPorcion;
    }

    public void setCaloriasPorPorcion(float caloriasPorPorcion) {
        this.caloriasPorPorcion = caloriasPorPorcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoAlimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlimento tipo) {
        this.tipo = tipo;
    }
    
}
