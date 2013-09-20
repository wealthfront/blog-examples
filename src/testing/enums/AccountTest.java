package testing.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
  Mockery mockery;
  Strategy mockStrategy;
  Account account;

  @Before
  public void before() {
    mockery = new Mockery();
    mockery.setImposteriser(ClassImposteriser.INSTANCE);
    mockStrategy = mockery.mock(Strategy.class);
    account = new Account(mockStrategy);
  }

  @Test
  public void testAcceptableRisk_getsAdded() {
    mockery.checking(new Expectations() {{
      oneOf(mockStrategy).isRiskAcceptable(0.1, 0.2);
      will(returnValue(true));
    }});
    account.addInvestment("BND", 0.1, 0.2);
    mockery.assertIsSatisfied();
    assertEquals(1, account.getInvestments().size());
  }

  @Test
  public void testUnacceptableRisk_doesntGetAdded() {
    mockery.checking(new Expectations() {{
      oneOf(mockStrategy).isRiskAcceptable(0.1, 0.2);
      will(returnValue(false));
    }});
    account.addInvestment("VWO", 0.1, 0.2);
    mockery.assertIsSatisfied();
    assertTrue(account.getInvestments().isEmpty());
  }

}
