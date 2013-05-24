package com.seitenbau.testing.dbunit.dsl;

public class GeneralTableRowCallback<R, F, D extends DatabaseReference> implements IParsedTableRowCallback
{
  private TableRowModel _head;

  private int _columns;

  private int _lineNr;

  private int _colRef;

  private int _colId;

  private final ITableAdapter<R, F, D> _tableAdapter;

  public GeneralTableRowCallback(ITableAdapter<R, F, D> tableAdapter)
  {
    _tableAdapter = tableAdapter;
    _head = null;
    _columns = 0;
    _lineNr = 0;
    _colRef = -1;
    _colId = -1;
  }
  
  R getRowBuilder(TableRowModel row)
  {
    R result = null;
    boolean isNewRef = false;
    Object id = null;
    
    // get builder by reference (if available)
    if (_colRef != -1)
    {
      @SuppressWarnings("unchecked")
      D ref = (D)row.getValue(_colRef);
      result = _tableAdapter.getRowByReference(ref);
      isNewRef = (result == null);
    }

    if (_colId != -1)
    {
      @SuppressWarnings("unchecked")
      ColumnBinding<R, F> column = (ColumnBinding<R, F>) _head.getValue(_colId);
      id = row.getValue(_colId);
      
      R builderById = null;
      // TODO NM use getWhere instead of findWhere
      try
      {
        builderById = column.query(_tableAdapter.getFindWhere(), CastUtil.cast(id, column.getDataType()));
      }
      catch (Exception e)
      {
      }
      if (result != null && builderById != null && builderById != result) {
        throw new RuntimeException("Table structure failure");
      }
      if (builderById != null) {
        result = builderById;
      }
    }
    
    if (result == null)
    {
      result = _tableAdapter.insertRow();
    }

    if (isNewRef) {
      @SuppressWarnings("unchecked")
      D ref = (D)row.getValue(_colRef);
      if (id != null) {
        // Set manual given ID
        @SuppressWarnings("unchecked")
        ColumnBinding<R, F> column = (ColumnBinding<R, F>) _head.getValue(_colId);
        column.set(result, id);
      } 
      _tableAdapter.handleReferences(ref, result);
    }
    return result;
  }

  public void parsedRow(TableRowModel row)
  {
    _lineNr++;
    if (row.isHeadRow())
    {
      _head = row;
      _colRef = _head.getRefColumn();
      _colId = _head.getIdentifierColumn();
      _columns = _head.getColumnCount();
      return;
    }

    if (row.getColumnCount() != _columns)
      throwColumnsDoNotMatchException(_lineNr, row);
    R rowbuilder = getRowBuilder(row);

    for (int columnIndex = 0; columnIndex < _columns; columnIndex++)
    {
      if (columnIndex == _colRef || columnIndex == _colId)
      {
        continue;
      }

      @SuppressWarnings("unchecked")
      ColumnBinding<R, F> column = (ColumnBinding<R, F>) _head.getValue(columnIndex);
      Object value = row.getValue(columnIndex);
      if (value instanceof DatabaseReference) {
        // TODO 
        DatabaseReference ref = (DatabaseReference)value;
        column.setReference(rowbuilder, ref);
      } else {
        column.set(rowbuilder, value);
      }
    }

    if (_colRef != -1 && row.getValue(_colRef) != null)
    {
      @SuppressWarnings("unchecked")
      D ref = (D) row.getValue(_colRef);
      _tableAdapter.referenceUsed(ref, rowbuilder);
    }
  }

  private void throwColumnsDoNotMatchException(int lineNr, TableRowModel row)
  {
    throwException("column count does not match", lineNr, row);
  }

  private void throwException(String message, int lineNr, TableRowModel row)
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Error in " + _tableAdapter.getTableName() + ", line " + lineNr + ": " + message);
    if (row.getValues().size() > 0)
    {
      builder.append(" [TableRowModel: ");
      for (Object value : row.getValues())
      {
        builder.append(value);
        builder.append(" | ");
      }
      builder.setLength(builder.length() - 3);
      builder.append("]");
    }
    throw new IllegalArgumentException(builder.toString());
  }
}