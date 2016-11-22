package org.cniska.noc2;

public class Main {

    public static void main(String[] args) {
        // Moment 5 (15p): Skriv ett program som genererar en Lottorad (7 tal mellan 1 och 39) och sedan ber
        // användaren mata in sina 7 siffror och visar hur många korrekta som angavs. Efter detta, skall programmet
        // be användaren ange 7 siffror igen till användaren kommit till rätt svar. Programmet skall räkna antalet fel
        // gissningar och i slutet ange detta samt den rätta raden. Uppenbart, programmet ska reagera på felinput
        // liksom decimaltal, dubletter, utanför intervallet [1, 39] (även "null input) eller textuell input.

        Config config = new Config();
        Lottery lottery = new Lottery(config);

        lottery.draw();
        lottery.run();
    }
}
