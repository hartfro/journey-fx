package journey.core;

import java.time.format.DateTimeFormatter;

public class Constantes {
    public static String DATE_PATTERN = "dd-MM-yyyy";
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Constantes.DATE_PATTERN);
}
