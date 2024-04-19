package com.numian.app.nqueens.timefold.domain;

import java.util.UUID;

import com.numian.app.nqueens.common.domain.Column;
import com.numian.app.nqueens.common.domain.Queen;
import com.numian.app.nqueens.common.domain.Row;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class QueenEntity {

  @PlanningId
  private String id;

  @PlanningVariable
  private Column column;

  @PlanningVariable
  private Row row;

  public static QueenEntity of(Queen queen) {
    QueenEntity queenEntity = new QueenEntity();
    queenEntity.setColumn(queen.column());
    queenEntity.setRow(queen.row());
    return queenEntity;
  }

  public static Queen toQueen(QueenEntity queenEntity) {
    return new Queen(queenEntity.getColumn(), queenEntity.getRow());
  }

  public QueenEntity() {
    this.id = UUID.randomUUID().toString();
  }

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
