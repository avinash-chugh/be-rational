package com.thoughtworks.spikes;

public class Rational implements Comparable<Rational> {
  private final int numerator;
  private final int denominator;

  public Rational(int numerator, int denominator) {
    if (denominator == 0) throw new IllegalArgumentException("Denominator must be non-zero");

    int divisor = gcd(Math.abs(numerator), denominator);

    this.numerator = numerator / divisor;
    this.denominator = denominator / divisor;
  }

  public Rational(int numerator) {
    this(numerator, 1);
  }

  public Rational negate() {
    return new Rational(-1 * numerator, denominator);
  }

  public Rational plus(Rational that) {
    return new Rational(this.numerator * that.denominator + that.numerator * this.denominator, this.denominator * that.denominator);
  }

  public Rational minus(Rational that) {
    return plus(that.negate());
  }

  @Override
  public int compareTo(Rational that) {
    return (this.numerator * that.denominator) - (this.denominator * that.numerator);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Rational that = (Rational) o;

    if (numerator != that.numerator && denominator != that.denominator) return false;
    return true;
  }

  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }

  private int gcd(int a, int b) {
    return (b == 0) ? a : gcd(b, a % b);
  }
}
