package testing.enums;

enum Strategy {
  AGGRESIVE {
    @Override
    boolean isRiskAcceptable(double percentOfAssets, double riskiness) {
      return percentOfAssets * riskiness < 0.25;
    }
  },
  CONSERVATIVE {
    @Override
    boolean isRiskAcceptable(double percentOfAssets, double riskiness) {
      return riskiness < 0.25;
    }
  };
  abstract boolean isRiskAcceptable(double percentOfAssets, double riskiness);
}
