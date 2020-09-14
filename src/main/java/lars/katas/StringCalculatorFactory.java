package lars.katas;

class StringCalculatorFactory {

  public StringCalculator create() {
    return new StringCalculator(new Splitter(), new Transformer());
  }
}