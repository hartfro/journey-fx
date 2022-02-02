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
                Sexo.MASCULINO, "102938", "Estudiante");

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
    }
}
