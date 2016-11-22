package org.cniska.noc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // Moment 1 (4p): Skriv ett program som väljer ett heltal mellan 1 och 10 slumpmässigt (använd
        // Math.random) och ber användaren att gissa ett tal. Programmet skall meddela om det gissade talet är större
        // eller mindre än det eftersökta talet och räkna hur många gissningar har givits. Programmet terminerar när
        // det korrekta talet har gissats och ange hur många gissningar det gick åt. Lägg här också till reagering för
        // ogiltiga inputs, t.ex. om användaren anger "sju" eller 6.34 istället för ett heltal vilket krävdes.

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int secretNumber = (int) ((Math.random() * 10) + 1);
        int guessedNumber;
        boolean isGuessCorrect = false;

        while (!isGuessCorrect) {
            System.out.println("Ange ett tal mellan 1 och 10: ");
            guessedNumber = Integer.parseInt(reader.readLine());

            if (guessedNumber == secretNumber) {
                isGuessCorrect = true;
            } else if (guessedNumber < secretNumber) {
                System.out.println("Din gissning var för liten.");
            } else {
                System.out.println("Din gissning var för stor.");
            }
        }

        System.out.println("GRATTIS! Du gissade rätt!");
    }
}
