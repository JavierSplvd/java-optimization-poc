package com.numian.app.nqueens.application;

import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.SolverConfig;
import com.numian.app.nqueens.common.domain.Column;
import com.numian.app.nqueens.common.domain.Queen;
import com.numian.app.nqueens.common.domain.Row;
import com.numian.app.nqueens.timefold.NQueensSolution;
import com.numian.app.nqueens.timefold.constraints.NQueensConstraintProvider;
import com.numian.app.nqueens.timefold.domain.QueenEntity;
import com.numian.app.nqueens.timefold.dto.SolutionResponse;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class NQueensApp {

  public SolutionResponse solve(int size, int maxSeconds) {
    var rows = IntStream.rangeClosed(1, size).boxed().map(Row::new).toList();
    var columns = IntStream
      .rangeClosed(1, size)
      .boxed()
      .map(Column::new)
      .toList();
    var queens = IntStream
      .rangeClosed(1, size)
      .boxed()
      .map(i -> new Queen(columns.get(i - 1), rows.get(i - 1)))
      .toList();
    NQueensSolution initialSolution = new NQueensSolution(
      size,
      rows,
      columns,
      queens.stream().map(QueenEntity::of).toList()
    );

    SolverFactory<NQueensSolution> solverFactory = SolverFactory.create(
      new SolverConfig()
        .withSolutionClass(NQueensSolution.class)
        .withEntityClasses(QueenEntity.class)
        .withConstraintProviderClass(NQueensConstraintProvider.class)
        .withTerminationSpentLimit(Duration.ofSeconds(maxSeconds))
    );

    var solver = solverFactory.buildSolver();
    NQueensSolution bestSolution = solver.solve(initialSolution);
    String score = bestSolution.getScore();

    System.out.println("Score: " + score);
    for (Row row : rows) {
      for (Column column : columns) {
        QueenEntity queen = bestSolution
          .getQueens()
          .stream()
          .filter(q -> q.getRow().equals(row) && q.getColumn().equals(column))
          .findFirst()
          .orElse(null);
        if (queen != null) {
          System.out.print("Q");
        } else {
          System.out.print(".");
        }
      }
      System.out.println();
    }
    return new SolutionResponse(
      score,
      bestSolution
        .getQueens()
        .stream()
        .map(QueenEntity::toQueen)
        .collect(Collectors.toSet())
    );
  }
}
