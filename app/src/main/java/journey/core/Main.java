package journey.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public boolean isRunning = true;

    public ArrayList<Alimento> bancoAlimentos = new ArrayList<>();
    public HashMap<String, Paciente> pacientes = new HashMap<>();
    public Paciente loggedInPaciente = null;

    private void setup() {
        this.bancoAlimentos.add(new Alimento("Pan", 265, TipoAlimento.CEREAL));
        this.bancoAlimentos.add(new Alimento("Huevo", 155, TipoAlimento.PROTEINA));
        this.bancoAlimentos.add(new Alimento("Agua", 0, TipoAlimento.LIQUIDO));
    }

    private void testSetup() {
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
                new InfoDia(LocalDate.now(), Emocion.FELIZ, new InfoEjercicio(30, IntensidadEjercicio.FUERTE),
                        testAlimentacion));

        this.pacientes.put(testPaciente.getUsername(), testPaciente);
    }
}