package com.numian.app.nqueens.application;

import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.SolverConfig;
import com.numian.app.nqueens.entities.Column;
import com.numian.app.nqueens.entities.Queen;
import com.numian.app.nqueens.entities.Row;
import com.numian.app.nqueens.model.NQueensSolution;
import com.numian.app.nqueens.model.constraints.NQueensConstraintProvider;
import java.time.Duration;
import java.util.stream.IntStream;

public final class NQueensApp {

  public String solve(int size, int maxSeconds) {
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
      queens
    );

    SolverFactory<NQueensSolution> solverFactory = SolverFactory.create(
      new SolverConfig()
        .withSolutionClass(NQueensSolution.class)
        .withEntityClasses(Queen.class)
        .withConstraintProviderClass(NQueensConstraintProvider.class)
        .withTerminationSpentLimit(Duration.ofSeconds(maxSeconds))
    );

    var solver = solverFactory.buildSolver();
    NQueensSolution bestSolution = solver.solve(initialSolution);
    String score = bestSolution.getScore();
    String queensPositions = bestSolution
      .getQueens()
      .stream()
      .map(queen ->
        "<c" + queen.getColumn().value() + ",r" + queen.getRow().value() + ">"
      )
      .reduce("", (acc, pos) -> acc + pos + " ");

    System.out.println("Score: " + score);
    for (Row row : rows) {
      for (Column column : columns) {
        Queen queen = bestSolution
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
    return score + "," + queensPositions;
  }
}
