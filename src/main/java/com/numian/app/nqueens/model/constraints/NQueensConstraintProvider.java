package com.numian.app.nqueens.model.constraints;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;
import com.numian.app.nqueens.entities.Queen;

public class NQueensConstraintProvider implements ConstraintProvider {

  @Override
  public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
    return new Constraint[] {
      columnConflict(constraintFactory),
      rowConflict(constraintFactory),
      ascendingDiagonalConflict(constraintFactory),
      descendingDiagonalConflict(constraintFactory)
    };
  }

  private Constraint rowConflict(ConstraintFactory constraintFactory) {
    return constraintFactory
      .forEachUniquePair(Queen.class, Joiners.equal(Queen::getRow))
      .penalize(HardSoftScore.ONE_HARD)
      .asConstraint("Row conflict");
  }

  private Constraint columnConflict(ConstraintFactory constraintFactory) {
    return constraintFactory
      .forEachUniquePair(Queen.class, Joiners.equal(Queen::getColumn))
      .penalize(HardSoftScore.ONE_HARD)
      .asConstraint("Column conflict");
  }

  private Constraint ascendingDiagonalConflict(ConstraintFactory constraintFactory) {
    return constraintFactory
      .forEachUniquePair(Queen.class, Joiners.equal(Queen::getAscendingDiagonal))
      .penalize(HardSoftScore.ONE_HARD)
      .asConstraint("Ascending diagonal conflict");
  }

  private Constraint descendingDiagonalConflict(ConstraintFactory constraintFactory) {
    return constraintFactory
      .forEachUniquePair(Queen.class, Joiners.equal(Queen::getDescendingDiagonal))
      .penalize(HardSoftScore.ONE_HARD)
      .asConstraint("Descending diagonal conflict");
  }
}
