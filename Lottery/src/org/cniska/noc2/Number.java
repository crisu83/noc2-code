package org.cniska.noc2;

/**
 * Represents a single number in a lottery.
 */
class Number implements Comparable<Number> {
    private int value;

    Number(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getValue() == ((Number) obj).getValue();
    }

    @Override
    public int compareTo(Number other) {
        return this.getValue() - other.getValue();
    }
}
