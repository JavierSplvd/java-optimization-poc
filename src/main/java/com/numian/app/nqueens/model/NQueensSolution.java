package com.numian.app.nqueens.model;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import com.numian.app.nqueens.entities.Column;
import com.numian.app.nqueens.entities.Queen;
import com.numian.app.nqueens.entities.Row;
import java.util.List;

@PlanningSolution
public class NQueensSolution {

  private final int n;

  @PlanningEntityCollectionProperty
  private final List<Queen> queens;

  @ValueRangeProvider
  @ProblemFactCollectionProperty
  private final List<Column> columns;

  @ValueRangeProvider
  @ProblemFactCollectionProperty
  private final List<Row> rows;

  @PlanningScore
  private HardSoftScore score;

  public NQueensSolution() {
    this.n = 0;
    this.rows = List.of();
    this.columns = List.of();
    this.queens = List.of();
    this.score = HardSoftScore.ZERO;
  }

  public NQueensSolution(
    int n,
    List<Row> rows,
    List<Column> columns,
    List<Queen> queens
  ) {
    this.n = n;
    this.rows = rows;
    this.columns = columns;
    this.queens = queens;
    this.score = HardSoftScore.ZERO;
  }

  public List<Queen> getQueens() {
    return queens;
  }

  public String getScore() {
    return "%shard/%ssoft".formatted(score.hardScore(), score.softScore());
  }
}
