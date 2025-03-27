import java.io.*;
import java.util.Arrays;

/**
 * Ein einfacher Währungsrechner für die Umrechnung zwischen Euro und US-Dollar.
 * Der Benutzer kann einen Betrag eingeben und ihn basierend auf einem Wechselkurs umrechnen.
 * Falls kein Wechselkurs angegeben wird, wird ein Standardwert von 1,09 verwendet.
 *
 * @author Keno Buß,
 * @version 1.0
 * @since 2025-03-22
 */
class Eurorechner {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String errorMsg = "Falsche Eingabe, bitte nochmal!";

    /**
     * Hauptmethode, die das Programm ausführt.
     * Das Programm fragt den Benutzer nach der Ausgangswährung, dem Betrag und dem Wechselkurs.
     * Danach wird die Umrechnung durchgeführt und das Ergebnis ausgegeben.
     * Das Programm läuft in einer Schleife, bis der Benutzer entscheidet, es zu beenden.
     *
     * @param args Kommandozeilenargumente (nicht genutzt)
     */
    public static void main(String[] args){
        do {
            EuroDollarConverter edc;
            String decision = askForStringWithSpecificAllowedDecisions(
                    "Eingabe der Ausgangswaehrung (E)uro oder (U)S Dollar ? : ",
                    new String[] {"e", "u"}
            );

            edc = new EuroDollarConverter(
                    askForFloat("Bitte nenne nun den Betrag welcher umgerechnet werden soll. : "),
                    askForFloatWithDefaultValue(
                            "Bitte nenne nun den Wechselkurs EUR zu USD. [Leer = default: 1.09 Stand: 22.03.2025]: ",
                            1.09f
                    )
            );

            switch (decision) {
                case "u":
                    System.out.println(edc.getAmount() + " US-Dollar entsprechen: " + edc.convertDollarToEuro() + " Euro.\n");
                    break;
                case "e":
                    System.out.println(edc.getAmount() + " Euro entsprechen: " + edc.convertEuroToDollar() + " US-Dollar.\n");
                    break;
            }

        } while (askForStringWithSpecificAllowedDecisions(
                "Wollen Sie noch einmal j/n? : ",
                new String[] {"j", "n"}).equals("j")
        );
    }

    /**
     * Fordert den Benutzer auf, eine Zeichenkette aus einer begrenzten Auswahl gültiger Eingaben einzugeben.
     * Falls eine ungültige Eingabe gemacht wird, wird der Benutzer erneut gefragt.
     *
     * @param message Die Nachricht, die dem Benutzer angezeigt wird.
     * @param validInputs Ein Array von erlaubten Eingaben.
     * @return Die gültige Eingabe als String.
     */
    private static String askForStringWithSpecificAllowedDecisions(String message, String[] validInputs) {
        while (true) {
            try{
                System.out.print(message);
                String input = reader.readLine().toLowerCase();
                if(Arrays.asList(validInputs).contains(input))
                    return input;
                System.out.println(errorMsg);
            }catch (Exception e){
                System.out.println(errorMsg);
            }
        }
    }

    /**
     * Fordert den Benutzer auf, eine Fließkommazahl einzugeben.
     * Falls eine ungültige Eingabe gemacht wird, wird der Benutzer erneut gefragt.
     *
     * @param message Die Nachricht, die dem Benutzer angezeigt wird.
     * @return Die eingegebene Fließkommazahl.
     */
    private static float askForFloat(String message) {
        while (true) {
            try{
                System.out.print(message);
                return Float.parseFloat(reader.readLine());
            }catch (Exception e){
                System.out.println(errorMsg);
            }
        }
    }

    /**
     * Fordert den Benutzer auf, eine Fließkommazahl einzugeben, mit der Option, eine Standardwert zu verwenden.
     * Falls der Benutzer keine Eingabe macht (drückt Enter), wird der Standardwert zurückgegeben.
     *
     * @param message Die Nachricht, die dem Benutzer angezeigt wird.
     * @param defaultValue Der Standardwert, falls keine Eingabe erfolgt.
     * @return Die eingegebene Fließkommazahl oder der Standardwert.
     */
    private static float askForFloatWithDefaultValue(String message, float defaultValue) {
        while (true) {
            try{
                System.out.print(message);
                String input = reader.readLine();

                return input.isEmpty() ? defaultValue : Float.parseFloat(input);
            }catch (Exception e){
                System.out.println(errorMsg);
            }
        }
    }

    /**
     * Klasse zur Umrechnung zwischen Euro und US-Dollar.
     * Speichert den Betrag und die Wechselkurse und bietet Methoden zur Währungsumrechnung.
     */
    static class EuroDollarConverter {
        private final float AMOUNT;
        private final float EXCHANGE_RATE_EUR_TO_USD;
        private final float EXCHANGE_RATE_USD_TO_EUR;

        /**
         * Konstruktor zur Erstellung eines Währungsumrechners.
         *
         * @param amount Der Betrag in der Ausgangswährung.
         * @param exchangeRateEurToUsd Der Wechselkurs von Euro zu US-Dollar.
         */
        public EuroDollarConverter(float amount, float exchangeRateEurToUsd) {
            this.AMOUNT = amount;
            this.EXCHANGE_RATE_EUR_TO_USD = exchangeRateEurToUsd;
            this.EXCHANGE_RATE_USD_TO_EUR = 1 / exchangeRateEurToUsd;
        }

        /**
         * Wandelt einen Betrag in US-Dollar in Euro um.
         *
         * @return Der umgerechnete Betrag in Euro, formatiert mit zwei Dezimalstellen.
         */
        public String convertDollarToEuro() {
            return String.format("%.2f", this.AMOUNT * this.EXCHANGE_RATE_USD_TO_EUR);
        }

        /**
         * Wandelt einen Betrag in Euro in US-Dollar um.
         *
         * @return Der umgerechnete Betrag in US-Dollar, formatiert mit zwei Dezimalstellen.
         */
        public String convertEuroToDollar() {
            return String.format("%.2f", this.AMOUNT * this.EXCHANGE_RATE_EUR_TO_USD);
        }

        /**
         * Gibt den ursprünglichen Betrag zurück, formatiert mit zwei Dezimalstellen.
         *
         * @return Der ursprüngliche Betrag als formatierter String.
         */
        public String getAmount() {
            return String.format("%.2f", this.AMOUNT);
        }
    }
}
