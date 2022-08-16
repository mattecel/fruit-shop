package com.amex.fruitshop;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// Normally would be in a domain package
@Entity
@Table(name = "fruitorders")
public class FruitOrder {

  private @Id @GeneratedValue Long id;
  private int numOfApples;
  private int numOfOranges;

  FruitOrder() {}

  public FruitOrder(int numOfApples, int numOfOranges) {
    this.numOfApples = numOfApples;
    this.numOfOranges = numOfOranges;
  }

  public Long getId() {
    return this.id;
  }

  @Column(name = "numOfApples")
  public int getnumOfApples() {
    return this.numOfApples;
  }

  @Column(name = "numOfOranges")
  public int getnumOfOranges() {
    return this.numOfOranges;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setnumOfApples(int numOfApples) {
    this.numOfApples = numOfApples;
  }

  public void setnumOfOranges(int numOfOranges) {
    this.numOfOranges = numOfOranges;
  }

  public double calcAppleCost(int numOfApples) {
    int extra = numOfApples % 2;
    int dividedApples = (Math.round((numOfApples / 2)));
    return (extra + dividedApples) * (0.60);
  }
  
  public double calcOrangeCost(int numOfOranges) {
    int extra = numOfOranges % 3;
    int dividedOranges = (Math.round((numOfOranges / 3))) * 2;
    return (extra + dividedOranges) * (0.25);
  }
  
  public double calcTotalCost() {
    return calcAppleCost(this.numOfApples) + calcOrangeCost(this.numOfOranges);
  }

  public String printFruitOrder() {

    double appleCost = calcAppleCost(this.numOfApples);
    double orangeCost = calcOrangeCost(this.numOfOranges);

    // would normally use big deci/money
    double totalCost = calcTotalCost();
    return String.format("You purchased: %s apples for $%s and %s oranges for $%s for a total cost of: $%s",
                        numOfApples, appleCost, numOfOranges, orangeCost, totalCost);
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof FruitOrder))
      return false;
    FruitOrder FruitOrder = (FruitOrder) o;
    return Objects.equals(this.id, FruitOrder.id) && Objects.equals(this.numOfApples, FruitOrder.numOfApples)
        && Objects.equals(this.numOfOranges, FruitOrder.numOfOranges);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.numOfApples, this.numOfOranges);
  }

  @Override
  public String toString() {
    return "FruitOrder{" + "id=" + this.id + ", numOfApples='" + this.numOfApples + '\'' + ", numOfOranges='" + this.numOfOranges + '\'' + '}';
  }
}

