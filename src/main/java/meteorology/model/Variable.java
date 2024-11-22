package meteorology.model;

public class Variable {
  public Double sum = 0.0;
  public Double minimum = Double.MAX_VALUE;
  public Double maximum = Double.MIN_VALUE;
  public int count = 0;
  public String name;

  public Variable(String name) {
    this.name = name;
  }

  public void update(Double value) {
    if (value < this.minimum) {
      this.minimum = value;
    }

    if (value > this.maximum) {
      this.maximum = value;
    }

    this.sum += value;
    ++this.count;
  }

  public void show() {
    System.out.printf(" - %s: [average=%f][minimum=%f][maximum=%f]\n", this.name, this.sum / this.count, this.minimum, this.maximum);
  }
}
