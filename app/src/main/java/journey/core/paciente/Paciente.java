package journey.core.paciente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import journey.dia.InfoDia;

public class Paciente {
    /**Corresponde al número mínimo de InfoDias para calcular el diagnóstico de ejercicio, como el factor de actividad.*/
    static int MINIMO_ENTRADAS_CALCULO_EJERCICIO = 5;

    private String username;
    private String password;
    private String primerNombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Sexo sexo;

    /** Peso en kg. */
    private float peso;
    /** Altura en cm. */
    private int altura;

    private String numeroContacto;
    private String ocupacion;

    public SortedSet<InfoDia> infoDiaria = new TreeSet<>(Comparator.comparing(InfoDia::getFecha));

    public Paciente(String username, String password, String primerNombre, String apellido, LocalDate fechaNacimiento,
            Sexo sexo, float peso, int altura, String numeroContacto, String ocupacion) {
        this.username = username;
        this.password = password;

        this.primerNombre = primerNombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.numeroContacto = numeroContacto;
        this.ocupacion = ocupacion;
    }

    // Métodos

    /**
     * Busca un infoDia dada una fecha específica.
     *
     * Si no se encuentra, devuelve null.
     */
    public InfoDia buscarInfoDiaPorFecha(LocalDate fecha) {
        InfoDia encontrado = null;

        for (var infoDia : this.infoDiaria) {
            if (infoDia.getFecha().equals(fecha)) {
                encontrado = infoDia;
            }
        }

        return encontrado;
    }

    public String nombreCompleto() {
        return this.getPrimerNombre() + " " + this.getApellido();
    }

    public int edad() {
        return (int) ChronoUnit.YEARS.between(this.getFechaNacimiento(), LocalDate.now());
    }

    public float imc() {
        float alturaM = (float) this.getAltura() / 100f;

        return (this.getPeso()) / (alturaM * alturaM);
    }

    public String diagnosticoIMC() {
        var imc = this.imc();

        if (imc < 16)
            return "Peso insuficiente. Muy grave.";
        
        if (imc < 17)
            return "Peso bajo severo.";

        if (imc < 18.5)
            return "Peso bajo.";

        if (imc < 25)
            return "Peso normal.";

        if (imc < 30)
            return "Sobrepeso.";

        if (imc < 35)
            return "Obesidad grado I.";

        if (imc < 40)
            return "Obesidad grado II.";

        return "Obesidad grado III.";
    }

    /**Calcula la media consumo ideal de calorías diarias de acuerdo a las fórmulas de Harris-Bennedict.
     *
     * Se sugiere utilizar un rango recomendad de +-100.
     * */
    public float idealCaloriasDiariasMedia() {
        if (this.getSexo() == Sexo.MASCULINO) {
            return (66 + (13.7f * this.getPeso())) + ((5f * this.getAltura())   - (6.8f * this.edad())) * this.factorActividad();
        }

        return (655 + (9.6f * this.getPeso())) + ((1.8f * this.getAltura()) - (4.7f * this.edad())) * this.factorActividad();
    }

    /**Devuelve el mínimo de calorías diarias recomendadas usando las fórmulas de Harris-Bennedict con un error de +-100.*/
    public float idealCaloriasDiariasMinimo() {
        return this.idealCaloriasDiariasMedia() - 100;
    }

    /**Devuelve el máximo de calorías diarias recomendadas usando las fórmulas de Harris-Bennedict con un error de +-100.*/
    public float idealCaloriasDiariasMaximo() {
        return this.idealCaloriasDiariasMedia() + 100;
    }

    /**Calcula el factor de actividad, definido según la cantidad de veces promedio de ejercicio semanal.
     *
     * Si el número de entradas en el diario es menor a MINIMO_ENTRADAS_CALCULO_EJERCICIO, se asume que el factor de actividad es el mínimo (1.2).
     * */
    public float factorActividad() {
        if (this.infoDiaria.size() < MINIMO_ENTRADAS_CALCULO_EJERCICIO) {
            return 1.2f;
        }

        int vecesEjercicioSemana = this.vecesEjercicioPorSemana();

        if (vecesEjercicioSemana == 0)
            return 1.2f;

        if (vecesEjercicioSemana <= 3)
            return 1.375f; 

        if (vecesEjercicioSemana <= 5)
            return 1.55f;

        if (vecesEjercicioSemana <= 7)
            return 1.725f;

        return 1.9f;
    }

    /**Devuelve un aproximado de las veces que el paciente realiza ejercicio por semana.*/
    public int vecesEjercicioPorSemana() {
        // Calcular cantidad de días que ha hecho ejercicio.
        int diasHechoEjercicio = 0;
        for (var infoDia : this.infoDiaria) {
            if (infoDia.getInfoEjercicio().getTiempo() > 0) {
                diasHechoEjercicio += 1;
            }
        }

        // Cantidad de días entre el primer y el úlitmo entry del journal.
        LocalDate primerDia = this.infoDiaria.first().getFecha();
        LocalDate ultimoDia = this.infoDiaria.last().getFecha();
        long rangoDiasDiario = ChronoUnit.DAYS.between(primerDia, ultimoDia);

        float vecesEjercicioPorDia = (float) diasHechoEjercicio / rangoDiasDiario;
        // 2.46
        return (int) Math.ceil(vecesEjercicioPorDia * 7);
    }

    public String rangoIdealCalorias() {
        return "[" + idealCaloriasDiariasMinimo() + ", " + idealCaloriasDiariasMaximo() + "]";
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public float getPeso() {
        return peso;
    }

    public int getAltura() {
        return altura;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public String getOcupacion() {
        return ocupacion;
    }
}
