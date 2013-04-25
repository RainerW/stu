package com.seitenbau.testing.dbunit.modifier;

import org.dbunit.dataset.IDataSet;

/**
 * Interface f�r alle Modifier die eigentlich einen Filter auf das
 * DataSet anwenden.
 */
public interface IDataSetFilter extends IDataSetModifier
{
  /**
   * F�hrt eine Filterung auf dem �bergeben DataSet aus.
   * 
   * @param current
   *          Das aktuelle DataSet.
   * 
   * @return Das gefilterte DataSet
   * 
   * @throws Exception
   *           Fehler.
   */
  public IDataSet filter(IDataSet current) throws Exception;
}
