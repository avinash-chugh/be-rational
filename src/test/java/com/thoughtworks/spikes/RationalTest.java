package com.thoughtworks.spikes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class RationalTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void shouldRender() {
    assertThat(new Rational(1, 2).toString(), is(equalTo("1/2")));
  }

  @Test
  public void shouldNegate() {
    assertThat(new Rational(1, 2).negate().toString(), is(equalTo("-1/2")));
  }

  @Test
  public void shouldAdd() {
    Rational actual = new Rational(1, 2).plus(new Rational(2, 3));
    assertThat(actual.toString(), is(equalTo("7/6")));
  }

  @Test
  public void shouldMinus() {
    Rational actual = new Rational(2, 3).minus(new Rational(1, 2));
    assertThat(actual.toString(), is(equalTo("1/6")));
  }

  @Test
  public void shouldCompare() {
    Rational left = new Rational(1, 2);
    Rational right = new Rational(2, 3);

    assertThat(left, is(lessThan(right)));
    assertThat(right, is(not(lessThan(left))));
  }

  @Test
  public void shouldIncludeIntegers() {
    assertThat(new Rational(25).toString(), is(equalTo("25/1")));
  }

  @Test
  public void shouldReduceByHCF() {
    assertThat(new Rational(8, 12), is(equalTo(new Rational(2, 3))));
    assertThat(new Rational(-5, 20), is(equalTo(new Rational(-1, 4))));
    assertThat(new Rational(50, 25), is(equalTo(new Rational(2))));
    assertThat(new Rational(4, 4), is(equalTo(new Rational(1))));
    assertThat(new Rational(0, 7), is(equalTo(new Rational(0))));
  }

  @Test
  public void shouldReduce() {
    Rational a = new Rational(1, 2);
    Rational b = new Rational(2, 3);

    assertThat(b.plus(b).plus(a).toString(), is(equalTo("11/6")));
  }

  @Test
  public void shouldRaiseError() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Denominator must be non-zero");
    new Rational(5, 0);
  }
}
