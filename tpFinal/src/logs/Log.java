package logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final String LOG_FILE = "src/logs/logs.txt";
    private static BufferedWriter writer;

    // Inicializacion estatica para abrir el archivo de log en modo de sobrescritura
    static {
        try {
            writer = new BufferedWriter(new FileWriter(LOG_FILE)); // No pasar true para sobrescribir
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para escribir un mensaje en el log
    private void log(String level, String message, Throwable t) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(String.format("[%s] [%s] %s", timestamp, level, message));
            if (t != null) {
                writer.write("\n" + t.toString());
                for (StackTraceElement ste : t.getStackTrace()) {
                    writer.write("\n\tat " + ste);
                }
            }
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodos publicos para diferentes niveles de log
    public void info(String message) {
        log("INFO", message, null);
    }

    public void warn(String message) {
        log("WARN", message, null);
    }

    public void error(String message, Throwable t) {
        log("ERROR", message, t);
    }

    // Metodo para cerrar el BufferedWriter cuando ya no se necesita más logging
    public static void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
