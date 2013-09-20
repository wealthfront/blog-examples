package testing.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrategyTest {
  @Test
  public void testHighRiskLowFraction_okayForRisky() {
    assertTrue(Strategy.AGGRESIVE.isRiskAcceptable(0.1, 0.75));
    assertTrue(Strategy.AGGRESIVE.isRiskAcceptable(0.2, 0.75));
    assertTrue(Strategy.AGGRESIVE.isRiskAcceptable(0.2, 0.5));
  }

  @Test
  public void testHighRiskHighFraction_notOkayForRisky() {
    assertFalse(Strategy.AGGRESIVE.isRiskAcceptable(1.0, 0.75));
    assertFalse(Strategy.AGGRESIVE.isRiskAcceptable(1.0, 0.5));
    assertFalse(Strategy.AGGRESIVE.isRiskAcceptable(0.5, 0.75));
  }

  @Test
  public void testHighRisk_notOkayForConservative() {
    assertFalse(Strategy.CONSERVATIVE.isRiskAcceptable(1.0, 0.75));
    assertFalse(Strategy.CONSERVATIVE.isRiskAcceptable(0.5, 0.75));
    assertFalse(Strategy.CONSERVATIVE.isRiskAcceptable(0.1, 0.75));
  }

  @Test
  public void testLowRisk_okayForConservative() {
    assertTrue(Strategy.CONSERVATIVE.isRiskAcceptable(0.1, 0.1));
    assertTrue(Strategy.AGGRESIVE.isRiskAcceptable(0.5, 0.1));
    assertTrue(Strategy.AGGRESIVE.isRiskAcceptable(1.0, 0.1));
  }

}
