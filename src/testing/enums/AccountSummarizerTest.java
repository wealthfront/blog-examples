package testing.enums;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("This breaks with an ArrayIndexOutOfBoundsException as described in http://www.javaspecialists.eu/archive/Issue161.html")
public class AccountSummarizerTest {
  Mockery mockery;
  Strategy mockStrategy;
  AccountSummarizer summarizer = new AccountSummarizer();

  @Before
  public void before() throws Exception {
    mockery = new Mockery();
    mockery.setImposteriser(ClassImposteriser.INSTANCE);
    mockStrategy = mockery.mock(Strategy.class);
    Field ordinalField = Enum.class.getDeclaredField("ordinal");
    ordinalField.setAccessible(true);
    ordinalField.setInt(mockStrategy, Strategy.values().length);
  }

  @Test
  public void testSummarize() {
    Account aggressive = new Account(Strategy.AGGRESIVE);
    aggressive.addInvestment("ABC", 0.1, 0.1);
    aggressive.addInvestment("DEF", 0.1, 0.1);
    Account conservative = new Account(Strategy.CONSERVATIVE);
    conservative.addInvestment("GHI", 0.1, 0.1);
    Account unknown = new Account(mockStrategy);
    List<List<String>> expected = list(list("Num Investments", "Strategy"),
        list("2 investments", "aggressive high risk"),
        list("1 investments", "conservative low risk"),
        list("0 investments", "unknown strategy"));
    assertEquals(expected,
        summarizer.summarizeAccounts(aggressive, conservative, unknown));
  }

  @SafeVarargs
  private final <T> List<T> list(T... a) {
    return Arrays.asList(a);
  }

}
