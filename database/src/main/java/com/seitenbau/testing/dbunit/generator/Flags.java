package com.seitenbau.testing.dbunit.generator;

import com.seitenbau.testing.dbunit.extend.DatabaseTesterCleanAction;
import com.seitenbau.testing.dbunit.extend.DatasetIdGenerator;

public enum Flags
{
  /**
   * Erzeugt in der DBUnit Spalte eine AutoIncrement Flag. Sinnvoll
   * falls Beispielsweise ein {@link DatabaseTesterCleanAction} diese
   * Information ben�tigt. Gleichzeitig wird aber auch eine nextId()
   * Methode f�r das Feld generiert. Gleich wie beim AddNextIdMethod.
   */
  AutoIncrement,
  /**
   * Erzeugt zus�tzlich zu den setter Methoden der Spalte noch eine
   * nextId Methode (Wobei Id = Spaltenname). Diese Methode erzeugt
   * �ber einen {@link DatasetIdGenerator} aus dem DataSetModel beim
   * Aufruf die n�chste ID. Au�erdem werden in der Builder Klasse des
   * erzeugten DataSets bei einem create*() die nextMethoden
   * automatisch gerufen.
   */
  AddNextIdMethod,
  /**
   * 
   */
  AutoInvokeNextIdMethod;
}
