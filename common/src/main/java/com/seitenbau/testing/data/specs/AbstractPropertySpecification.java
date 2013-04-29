package com.seitenbau.testing.data.specs;

import java.util.List;

import com.seitenbau.testing.data.PropertySpecification;
import com.seitenbau.testing.data.detail.Representant;
import com.seitenbau.testing.data.detail.Representant.RepresentantType;

public abstract class AbstractPropertySpecification<T> implements PropertySpecification<T>
{

  public List<Representant<T>> getValidRepresentations()
  {
    return getRepresentatives(RepresentantType.VALID);
  }

  public List<Representant<T>> getInValidRepresentations()
  {
    return getRepresentatives(RepresentantType.INVALID);
  }

  protected abstract List<Representant<T>> getRepresentatives(RepresentantType valid);

  protected boolean safe(Boolean value, boolean defaultValue)
  {
    if (value != null)
    {
      return value;
    }
    return defaultValue;
  }

}
