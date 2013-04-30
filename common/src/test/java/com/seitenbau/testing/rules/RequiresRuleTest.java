package com.seitenbau.testing.rules;

import org.junit.Test;
import static com.seitenbau.testing.asserts.fest.Assertions.*;

import junit.framework.TestCase;

public class RequiresRuleTest
{

  @Test
  public void testNotImpl()
  {
    assertThat(RequiresRule.isMethodToIgnore(""))
        .isEqualTo("Test ignored because feature '' is not active.");
    assertThat(RequiresRule.isMethodToIgnore("notImpl:rainer"))
        .isEqualTo("Test ignored because feature 'rainer' is not active.");
  }

  @Test
  public void testImpl()
  {
    assertThat(RequiresRule.isMethodToIgnore("impl:rainer"))
        .isNull();
    assertThat(RequiresRule.isMethodToIgnore("ignored:ticket1"))
        .isNull();
  }

  @Test
  public void testTicket()
  {
    assertThat(RequiresRule.isMethodToIgnore("opEN:ticket1"))
        .isEqualTo(
            "Test ignored because ticket 'ticket1' is not solved. Is OPEN");
    assertThat(RequiresRule.isMethodToIgnore("ticket1"))
        .isEqualTo(
            "Test ignored because feature 'ticket1' is not active.");
  }

  @Test
  public void testTicketFixed()
  {
    assertThat(RequiresRule.isMethodToIgnore("fixeD:rainer"))
        .isNull();

  }

  @Test
  public void testTicketCombi()
  {
    assertThat(
        RequiresRule.isMethodToIgnore("impl:feature1", "fixed:ticket1",
            "implemented :feature2", "ticket3"))
        .isEqualTo("Test ignored because feature 'ticket3' is not active.");
    assertThat(
        RequiresRule.isMethodToIgnore("impl:feature1", "fixed:ticket1",
            "notImpl:feature2", "ticket3"))
        .isEqualTo(
            "Test ignored because feature 'feature2' is not active.");
  }

}