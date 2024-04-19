package com.numian.app.nqueens.common.domain;

public record Queen(Column column, Row row) {
  public int getAscendingDiagonal() {
    return column.value() + row.value();
  }

  public int getDescendingDiagonal() {
    return column.value() - row.value();
  }
}
