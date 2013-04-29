package com.seitenbau.testing.fest;

import com.seitenbau.testing.expression4j.Assertable;

public class AssertableDelegate<A> implements Assertable<A>
{
  private A _assert;

  public AssertableDelegate(A assertImpl)
  {
    _assert = assertImpl;
  }

  public A getAssertBuilder()
  {
    return _assert;
  }

  public static <TYPE> Assertable<TYPE> create(TYPE assertImpl)
  {
    return new AssertableDelegate<TYPE>(assertImpl);
  }

}
