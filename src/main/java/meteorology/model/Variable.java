package meteorology.model;

public class Variable {
  public Double average = 0.0;
  public Double minimum = 0.0;
  public Double maximum = 0.0;
  public int count = 0;
  public String name;

  public Variable(String name) {
    this.name = name;
  }

  public void update(Double value) {
    if (count == 0) {
      this.maximum = value;
      this.minimum = value;
    }

    if (value < this.minimum) {
      this.minimum = value;
    }

    if (value > this.maximum) {
      this.maximum = value;
    }

    this.average += value;
    ++this.count;
  }

  public void show() {
    System.out.printf(" - %s: [average=%f][minimum=%f][maximum=%f]\n", this.name, this.average / this.count, this.minimum, this.maximum);
  }
}
