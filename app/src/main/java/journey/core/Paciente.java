package journey.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;

public class Paciente {
    static int MINIMO_ENTRADAS_CALCULO_EJERCICIO = 5;

    private String username;
    private String password;
    private String primerNombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Sexo sexo;
    private String numeroContacto;
    private String ocupacion;
    ArrayList <InfoDia> infoDia = new ArrayList<InfoDia>();

    public SortedSet<InfoDia> infoDiaria = new TreeSet<>(Comparator.comparing(InfoDia::getFecha));

    public Paciente(String username, String password, String primerNombre, String apellido, LocalDate fechaNacimiento,
            Sexo sexo, String numeroContacto, String ocupacion) {
        this.username = username;
        this.password = password;
        this.primerNombre = primerNombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
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

    public void agregarInfoDia(InfoDia infoDia) {
        this.infoDiaria.add(infoDia);
    }

    public String buscarDiagnosticoIMCPorDia(LocalDate fecha) {
        return this.buscarInfoDiaPorFecha(fecha).diagnosticoIMC();
    }

    public String nombreCompleto() {
        return this.getPrimerNombre() + " " + this.getApellido();
    }

    public int calcularEdad() {
        return (int) ChronoUnit.YEARS.between(this.getFechaNacimiento(), LocalDate.now());
    }

    /**Calcula el factor de actividad, definido según la cantidad de veces promedio de ejercicio semanal.
     *
     * Si el número de entradas en el diario es menor a MINIMO_ENTRADAS_CALCULO_EJERCICIO, se asume que el factor de actividad es el mínimo (1.2).
     * */
    public float calcularFactorActividad() {
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

        else
            return 1.725f;
    }

    /**Calcula la media consumo ideal de calorías diarias de acuerdo a las fórmulas de Harris-Bennedict.
     *
     * Se sugiere utilizar un rango recomendad de +-100.
     * */
    public float idealCaloriasDiariasMedia() {
        var peso = this.infoDiaria.last().getPeso();
        var altura = this.infoDiaria.last().getAltura();

        if (this.getSexo() == Sexo.MASCULINO) {
            return (66 + (13.7f * peso)) + ((5f * altura)   - (6.8f * calcularEdad())) * calcularFactorActividad();
        }
        return (655 + (9.6f * peso)) + ((1.8f * altura) - (4.7f * calcularEdad())) * calcularFactorActividad();
    }
    /**Devuelve el mínimo de calorías diarias recomendadas usando las fórmulas de Harris-Bennedict con un error de +-100.*/
    public float idealCaloriasDiariasMinimo() {
        return idealCaloriasDiariasMedia() - 100;
    }
    /**Devuelve el máximo de calorías diarias recomendadas usando las fórmulas de Harris-Bennedict con un error de +-100.*/
    public float idealCaloriasDiariasMaximo() {
        return this.idealCaloriasDiariasMedia() + 100;
    }

    public String rangoIdealCalorias() {
        return "[" + idealCaloriasDiariasMinimo() + ", " + idealCaloriasDiariasMaximo() + "]";
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

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
}