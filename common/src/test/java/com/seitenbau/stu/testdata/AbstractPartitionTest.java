package com.seitenbau.stu.testdata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import com.seitenbau.stu.testdata.AbstractPartition;
import com.seitenbau.stu.testdata.NullPartitionValue;
import com.seitenbau.stu.testdata.PartitionValue;

public class AbstractPartitionTest
{

  @Test
  public void testCreateDefaultValue_EmptyList()
  {
    PartitionValue defaultValue = AbstractPartition.createDefaultValue(Collections.EMPTY_LIST);
    assertEquals(NullPartitionValue.class, defaultValue.getClass());
  }
  
  @Test
  public void testCreateDefaultValue_ListWithNullValue()
  {
    ArrayList<PartitionValue> list = new ArrayList<PartitionValue>();
    list.add(null);
    PartitionValue defaultValue = AbstractPartition.createDefaultValue(list);
    assertEquals(NullPartitionValue.class, defaultValue.getClass());
  }

}
