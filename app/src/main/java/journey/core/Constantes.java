package journey.core;

import java.time.format.DateTimeFormatter;
/**
 * Esta clase contiene las constantes usadas en otras clases; en específico, patrón y formato de las fechas.
 * @author Grupo 23
 * @version 30/01/2022
 */
public class Constantes {
    public static String DATE_PATTERN = "dd-MM-yyyy";
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Constantes.DATE_PATTERN);
}