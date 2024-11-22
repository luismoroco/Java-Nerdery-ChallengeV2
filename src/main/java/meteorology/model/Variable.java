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

  public Double getAverage() {
    return this.count == 0 ? 0.0 : this.sum / this.count;
  }

  public void show() {
    System.out.printf(" - %s: [average=%.2f][minimum=%.2f][maximum=%.2f]\n", this.name, this.getAverage(), this.minimum, this.maximum);
  }
}
