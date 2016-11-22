package org.cniska.noc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // Moment 2 (4p): Skriv ett omvänt program till uppgiften på mom1, dvs. ett program som letar sig fram till ett
        // heltal mellan 1 och 10 som användaren bestämt. Programmet ger först ett förslag, därefter skall användaren
        // ange om förslaget är större än, mindre än, eller lika med det tänkta talet. I det sista fallet terminerar
        // programmet, annars skall det föreslå en ny gissning.

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int guessedNumber;
        int min = 1;
        int max = 10;
        int answer;
        boolean isGuessCorrect = false;

        while (!isGuessCorrect) {
            // Moment 3 (4p): Gör programmet i mom. 2 att lösa saken med minimala antalet gissningar (googla t.ex.
            // divide and conquer - söndra och härska). På samma stöter du på hur man söker något på ett efektivt sätt
            // vilket är en datastruktur
            guessedNumber = min + ((int) Math.floor(max - min) / 2);

            System.out.println("Ange ifall talet " + guessedNumber + " är mindre (0), större (1) än det du tänkte på eller rätt (2): ");

            answer = Integer.parseInt(reader.readLine());

            if (answer == 2) {
                isGuessCorrect = true;
            } else if (answer == 1) {
                min = guessedNumber;
            } else {
                max = guessedNumber;
            }

            // Moment 4 (3p): Gör programmet i mom. 2 att reagera på fusk från anvädarens sida. T.ex. talet är 3 och
            // datorn gissar 5 till vilket användaren anger mindre - datorn gissar 2 till vilket användaren anger mindre -
            // datorn gissar 1 till vilket användaren anger större - reagerar på med att skriva ut: FUSK! <och terminerar>.
            if ((max - min) < 2) {
                System.out.println("Ditt svar är inte möjligt, du ljuger!");
                System.exit(1);
            }
        }

        System.out.println("GRATTIS!");
    }
}
