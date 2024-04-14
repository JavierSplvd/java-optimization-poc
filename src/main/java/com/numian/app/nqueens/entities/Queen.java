package com.numian.app.nqueens.entities;

import java.util.UUID;
import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Queen {

  @PlanningId
  private String id;

  @PlanningVariable
  private Column column;

  @PlanningVariable
  private Row row;

  public Queen(Column column, Row row) {
    id = UUID.randomUUID().toString();
    this.column = column;
    this.row = row;
  }

  public Queen() {}

  public int getAscendingDiagonal() {
    return column.value() + row.value();
  }

  public int getDescendingDiagonal() {
    return column.value() - row.value();
  }

  public String getId() {
    return id;
  }

  public Column getColumn() {
    return column;
  }

  public Row getRow() {
    return row;
  }

  public void setColumn(Column column) {
    this.column = column;
  }

  public void setRow(Row row) {
    this.row = row;
  }

  @Override
  public String toString() {
    return "Queen{" + "id=" + id + ", column=" + column + ", row=" + row + '}';
  }
}
