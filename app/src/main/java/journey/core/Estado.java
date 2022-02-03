package journey.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Estado {
    private ArrayList<Alimento> bancoAlimentos = new ArrayList<>();
    private HashMap<String, Paciente> pacientes = new HashMap<>();
    private Paciente loggedInPaciente = null;

    public ArrayList<Alimento> getBancoAlimentos() {
        return bancoAlimentos;
    }

    public HashMap<String, Paciente> getPacientes() {
        return pacientes;
    }

    public Paciente getLoggedInPaciente() {
        return loggedInPaciente;
    }

    /**
     * Constructor de Estado. El argumento determina si se está en un ambiente de producción o de desarrollo.
    */
    public Estado(boolean debug) {
        this.setup();

        if (debug)
            this.testSetup();
    }

    public void registerUser(Paciente paciente) throws Exception {
        if (this.pacientes.containsKey(paciente.getUsername())) {
            throw new Exception("Nombre de usuario ya ocupado.");
        }

        this.pacientes.put(paciente.getUsername(), paciente);
    }

    public Paciente login(String username, String password) {
        Paciente user = pacientes.get(username);

        // Check password
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        this.loggedInPaciente = user;
        return user;
    }

    public void logout() {
        this.loggedInPaciente = null;
    }

    public void setup() {
        this.bancoAlimentos.add(new Alimento("Pan", 265, TipoAlimento.CEREAL));
        this.bancoAlimentos.add(new Alimento("Huevo", 155, TipoAlimento.PROTEINA));
        this.bancoAlimentos.add(new Alimento("Agua", 0, TipoAlimento.LIQUIDO));
    }

    public void testSetup() {
        Paciente testPaciente = new Paciente("admin", "admin", "Anthony", "Suárez", LocalDate.parse("2003-12-20"),
                Sexo.MASCULINO, "0923475926", "Estudiante");

        InfoAlimentacion testAlimentacion = new InfoAlimentacion();
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 10);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1), 5);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 7);

        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 8);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1), 9);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 5);

        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 6);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1), 10);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 2);

        testPaciente.infoDiaria.add(
                new InfoDia(LocalDate.of(2021, 11, 12), 60, 170, Emocion.FELIZ, new InfoEjercicio(30, IntensidadEjercicio.FUERTE),
                        testAlimentacion));

        this.pacientes.put(testPaciente.getUsername(), testPaciente);

        Paciente paciente2 = new Paciente("juan", "juan", "Juan", "Cevallos", LocalDate.parse("2002-11-22"),
                Sexo.MASCULINO, "0987044325", "Empleado");

        InfoAlimentacion alimentacion2 = new InfoAlimentacion();
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 7);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1),4);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 5);

        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 6);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1), 7);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 4);

        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 3);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1), 5);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 3);

        paciente2.infoDiaria.add(
                new InfoDia(LocalDate.of(2022, 1, 1), 45, 159, Emocion.ABURRIDO, new InfoEjercicio(15, IntensidadEjercicio.LEVE),
                        alimentacion2));

        this.pacientes.put(paciente2.getUsername(), paciente2);

        Paciente paciente3 = new Paciente("marco", "marco", "Marco", "Rojas", LocalDate.of(2003, 10, 4),
                Sexo.MASCULINO, "0983028493", "Estudiante");

        InfoAlimentacion alimentacion3 = new InfoAlimentacion();
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 5);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1), 4);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 5);

        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 6);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1), 2);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 4);

        testAlimentacion.getDesayuno().put(bancoAlimentos.get(0), 3);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(1), 5);
        testAlimentacion.getDesayuno().put(bancoAlimentos.get(2), 1);

        paciente3.infoDiaria.add(
                new InfoDia(LocalDate.of(2020, 10, 5), 49, 162, Emocion.FELIZ, new InfoEjercicio(18, IntensidadEjercicio.FUERTE),
                        alimentacion3));

        this.pacientes.put(paciente3.getUsername(), paciente3);
    }
}
