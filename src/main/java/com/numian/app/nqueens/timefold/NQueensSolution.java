package com.numian.app.nqueens.timefold;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;
import java.util.Set;

import com.numian.app.nqueens.common.domain.Column;
import com.numian.app.nqueens.common.domain.Row;
import com.numian.app.nqueens.timefold.domain.QueenEntity;
import com.numian.app.nqueens.timefold.dto.SolutionResponse;

@PlanningSolution
public class NQueensSolution {

  @PlanningEntityCollectionProperty
  private final List<QueenEntity> queens;

  @ValueRangeProvider
  @ProblemFactCollectionProperty
  private final List<Column> columns;

  @ValueRangeProvider
  @ProblemFactCollectionProperty
  private final List<Row> rows;

  @PlanningScore
  private HardSoftScore score;

  public NQueensSolution() {
    this.rows = List.of();
    this.columns = List.of();
    this.queens = List.of();
    this.score = HardSoftScore.ZERO;
  }

  public NQueensSolution(
    int n,
    List<Row> rows,
    List<Column> columns,
    List<QueenEntity> queens
  ) {
    this.rows = rows;
    this.columns = columns;
    this.queens = queens;
    this.score = HardSoftScore.ZERO;
  }

  public List<QueenEntity> getQueens() {
    return queens;
  }

  public String getScore() {
    return "%shard/%ssoft".formatted(score.hardScore(), score.softScore());
  }

  public SolutionResponse toResponse() {
    return new SolutionResponse(getScore(), Set.copyOf(queens.stream().map(QueenEntity::toQueen).toList()));
  }
}
