package testing.enums;

import java.util.HashMap;
import java.util.Map;

class Account {
  private Strategy strategy;
  private Map<String, Double> investments;

  Account(Strategy strategy) {
    this.strategy = strategy;
    this.investments = new HashMap<>();
  }

  boolean addInvestment(String symbol, double fraction, double riskiness) {
    if (strategy.isRiskAcceptable(fraction, riskiness)) {
      investments.put(symbol, fraction);
      return true;
    }
    return false;
  }

  public Strategy getStrategy() {
    return strategy;
  }

  public Map<String, Double> getInvestments() {
    return investments;
  }
}
