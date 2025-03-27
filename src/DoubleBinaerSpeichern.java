import java.io.*;

public class DoubleBinaerSpeichern {


    public static void main(String[] args) {
        String inputFilePath = "H:\\SoSe25\\GdP2\\Java\\ESA1\\untitled\\src\\datei1702_vor.html";
        String outputFilePath = "H:\\SoSe25\\GdP2\\Java\\ESA1\\untitled\\src\\datei1702_nach.html";

        try {
            // Datei einlesen
            String content = readFile(inputFilePath);

            // Umlaute ersetzen
            content = content.replaceAll("ä", "&auml;")
                    .replaceAll("Ä", "&Auml;")
                    .replaceAll("ö", "&ouml;")
                    .replaceAll("Ö", "&Ouml;")
                    .replaceAll("ü", "&uuml;")
                    .replaceAll("Ü", "&Uuml;")
                    .replaceAll("ß", "&szlig;");

            // Datei schreiben
            writeFile(outputFilePath, content);

            System.out.println("Die Datei wurde erfolgreich konvertiert und gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    // Methode zum Einlesen einer Datei und Rückgabe des Inhalts als String
    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");  // Zeilen hinzufügen und mit Zeilenumbruch trennen
            }
        }
        return content.toString();
    }

    // Methode zum Schreiben eines Strings in eine Datei
    public static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"))) {
            writer.write(content);
        }
    }
}
