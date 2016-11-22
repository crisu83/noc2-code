package org.cniska.noc2;

import java.util.Arrays;
import java.util.Objects;

/**
 * Utilities used by the application.
 */
class Utils {

    /**
     * Returns a random number within the given range.
     *
     * @param min The minimum value
     * @param max The maximum value
     * @return The random number
     */
    static Integer randomNumberWithinRange(int min, int max) {
        return ((int) (Math.random() * max)) + min;
    }

    /**
     * Returns whether or not the given array contains the given number.
     *
     * @param array  The array to check
     * @param number The number to find
     * @return The result
     */
    static boolean arrayContainsNumber(Number[] array, Number number) {
        return Arrays.stream(array)
                .filter(item -> item != null && item.equals(number))
                .count() > 0;
    }

    /**
     * Converts a comma-separated string to a string array.
     *
     * @param text The text
     * @return The string array
     */
    static String[] commaSeparatedStringToStringArray(String text) {
        return text.replaceAll("\\s+", "").split(",");
    }

    /**
     * Converts a string array to a number array.
     *
     * @param array The string array
     * @return The number array
     * @throws NumberFormatException
     */
    static Number[] stringArrayToNumberArray(String[] array) throws NumberFormatException {
        return Arrays.stream(array)
                .filter(Objects::nonNull)
                .map(item -> new Number(Integer.parseInt(item)))
                .toArray(Number[]::new);
    }

    /**
     * Converts a number array to a string array.
     *
     * @param array The number array
     * @return The string array
     */
    static String[] numberArrayToStringArray(Number[] array) {
        return Arrays.stream(array)
                .filter(Objects::nonNull)
                .map(item -> String.valueOf(item.getValue()))
                .toArray(String[]::new);
    }

    /**
     * Counts the number of non-null items in the given array.
     *
     * @param array The array to check
     * @return The number of non-null items
     */
    static int countNonNullItemsInArray(Object[] array) {
        return (int) Arrays.stream(array)
                .filter(Objects::nonNull)
                .count();
    }

    /**
     * Converts a number array to a comma-separated string.
     *
     * @param array The number array
     * @return The string
     */
    static String numberArrayToCommaSeparatedString(Number[] array) {
        return String.join(", ", numberArrayToStringArray(array));
    }

    /**
     * Returns whether or not the given number array contains duplicates.
     *
     * @param array The array to check
     * @return The result
     */
    static boolean numberArrayIsWithoutDuplicates(Number[] array) {
        return Arrays.stream(array)
                .filter(item -> Arrays.stream(array).filter(other -> other.equals(item)).count() == 1)
                .count() == array.length;
    }

    /**
     * Returns whether or not the items in the given array is within the given range.
     *
     * @param array          The number array
     * @param smallestNumber The smallest number allowed
     * @param largestNumber  The largest number allowed
     * @return The result
     */
    static boolean numberArrayIsWithinRange(Number[] array, int smallestNumber, int largestNumber) {
        return Arrays.stream(array)
                .filter(item -> item.getValue() >= smallestNumber && item.getValue() <= largestNumber)
                .count() == array.length;
    }
}
