package testing.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountSummarizer {
  public List<List<String>> summarizeAccounts(Account... accounts) {
    List<List<String>> summary = new ArrayList<>();
    summary.add(Arrays.asList("Num Investments", "Strategy"));
    for (Account account : accounts) {
      summary.add(summarize(account));
    }
    return summary;
  }

  List<String> summarize(Account account) {
    String accountDescription;
    switch (account.getStrategy()) {
    case AGGRESIVE:
      accountDescription = "aggressive high risk";
      break;
    case CONSERVATIVE:
      accountDescription = "conservative low risk";
      break;
    default:
      accountDescription = "unknown strategy";
      break;
    }
    String investments = String.format("%d investments", account.getInvestments().size());
    return Arrays.asList(investments, accountDescription);
  }
}
