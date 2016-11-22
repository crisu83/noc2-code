package org.cniska.noc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Represents a lottery.
 */
class Lottery {
    private BufferedReader input;
    private Number[] pickedNumbers;
    private Config config;

    /**
     * Creates a new lottery.
     *
     * @param config The configuration
     */
    Lottery(Config config) {
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.config = config;
    }

    /**
     * Draws the numbers for the lottery in a random fashion.
     */
    void draw() {
        this.pickedNumbers = new Number[this.config.numberOfNumbers];

        Number pickedNumber;
        boolean isNumberAvailable;

        for (int i = 0; i < this.pickedNumbers.length; i++) {
            isNumberAvailable = false;

            while (!isNumberAvailable) {
                pickedNumber = this.pickNumber();

                if (!Utils.arrayContainsNumber(this.pickedNumbers, pickedNumber)) {
                    this.pickedNumbers[i] = pickedNumber;
                    isNumberAvailable = true;
                }
            }
        }

        Arrays.sort(this.pickedNumbers);
    }

    /**
     * Runs the lottery.
     */
    void run() {
        boolean isGuessCorrect = false;
        int numberOfNumbersCorrect;
        int numberOfGuesses = 0;

        while (!isGuessCorrect) {
            numberOfNumbersCorrect = this.askForGuess();

            if (numberOfNumbersCorrect == this.config.numberOfNumbers) {
                isGuessCorrect = true;
            }

            numberOfGuesses++;
        }

        UI.outputHeading("GRATTIS!");
        UI.outputLine("Du kom fram till den rätta lotto-raden (" + Utils.numberArrayToCommaSeparatedString(this.pickedNumbers) + ") med " + numberOfGuesses + " gissningar!");
    }

    /**
     * Picks a single number for the lottery.
     *
     * @return The drawn number
     */
    private Number pickNumber() {
        Number drawnNumber = null;
        boolean isDrawnNumberAvailable = false;

        while (!isDrawnNumberAvailable) {
            drawnNumber = new Number(Utils.randomNumberWithinRange(this.config.smallestNumber, this.config.largestNumber));

            if (!Utils.arrayContainsNumber(this.pickedNumbers, drawnNumber)) {
                isDrawnNumberAvailable = true;
            }
        }

        return drawnNumber;
    }

    /**
     * Asks the user to guess the lottery numbers.
     *
     * @return The number of correct numbers
     */
    private int askForGuess() {
        String guess;
        Number[] guessedNumbers = new Number[this.config.numberOfNumbers];
        boolean isGuessValid = false;
        boolean isWithinRange;
        boolean isWithoutDuplicates;
        boolean isOfCorrectLength;
        Number[] correctNumbers;
        int numberOfCorrectNumbers;

        while (!isGuessValid) {
            guess = this.askQuestion("Ange din gissning för lotto-raden (t.ex. 1, 4, 9, 17, 24, 31, 38)");

            try {
                guessedNumbers = Utils.stringArrayToNumberArray(Utils.commaSeparatedStringToStringArray(guess));

                isWithinRange = Utils.numberArrayIsWithinRange(guessedNumbers, this.config.smallestNumber, this.config.largestNumber);
                isWithoutDuplicates = Utils.numberArrayIsWithoutDuplicates(guessedNumbers);
                isOfCorrectLength = guessedNumbers.length == this.config.numberOfNumbers;

                if (isWithinRange && isWithoutDuplicates && isOfCorrectLength) {
                    isGuessValid = true;
                } else {
                    UI.outputError("Din gissning måste bestå av " + this.config.numberOfNumbers + " olika heltal mellan " + this.config.smallestNumber + " och " + this.config.largestNumber + "!");
                }
            } catch (NumberFormatException exception) {
                UI.outputError("Din gissning måste bestå av exakt sju heltal!");
            }
        }

        correctNumbers = this.filterCorrectNumbers(guessedNumbers);
        numberOfCorrectNumbers = Utils.countNonNullItemsInArray(correctNumbers);

        if (numberOfCorrectNumbers > 0) {
            UI.outputLine("Du har " + numberOfCorrectNumbers + " " + (numberOfCorrectNumbers == 1 ? "siffra" : "siffror") + " rätt! (" + Utils.numberArrayToCommaSeparatedString(correctNumbers) + ")");
        } else {
            UI.outputLine("Du har inga siffror rätt!");
        }

        return numberOfCorrectNumbers;
    }

    /**
     * Compares the given guessed numbers against the picked numbers and returns the correct numbers.
     *
     * @param guessedNumbers The guessed numbers
     * @return The correct numbers
     */
    private Number[] filterCorrectNumbers(Number[] guessedNumbers) {
        Number[] correctNumbers = new Number[this.config.numberOfNumbers];
        int numberOfCorrectNumbers = 0;

        for (Number pickedNumber : this.pickedNumbers) {
            for (Number guessedNumber : guessedNumbers) {
                if (pickedNumber.equals(guessedNumber)) {
                    correctNumbers[numberOfCorrectNumbers++] = guessedNumber;
                }
            }
        }

        return correctNumbers;
    }

    /**
     * Asks the user a question.
     *
     * @param question The question to ask
     * @return The answer
     */
    private String askQuestion(String question) {
        String answer = null;

        try {
            UI.outputLine(question + ": ");
            answer = this.input.readLine();
        } catch (IOException e) {
            // We should never get here, so we might as well silence this error.
        }

        return answer;
    }
}
