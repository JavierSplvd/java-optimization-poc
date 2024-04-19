package com.numian.app.nqueens.ortools;

import java.util.HashSet;
import java.util.Set;

import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverSolutionCallback;
import com.google.ortools.sat.IntVar;
import com.google.ortools.sat.LinearExpr;
import com.numian.app.nqueens.common.domain.Column;
import com.numian.app.nqueens.common.domain.Queen;
import com.numian.app.nqueens.common.domain.Row;
import com.numian.app.nqueens.common.dto.SolutionResponse;

public class Model {

    final CpModel model = new CpModel();
    final int boardSize;
    final IntVar[] queens;
    int[] solution;
    int solutionCount = 0;

    public Model(int boardSize) {
        Loader.loadNativeLibraries();
        this.boardSize = boardSize;
        this.queens = new IntVar[boardSize];
        for (int i = 0; i < boardSize; ++i) {
            queens[i] = model.newIntVar(0, boardSize - 1, "x" + i);
        }

        // All rows must be different.
        model.addAllDifferent(queens);

        // No two queens can be on the same diagonal.
        LinearExpr[] diag1 = new LinearExpr[boardSize];
        LinearExpr[] diag2 = new LinearExpr[boardSize];
        for (int i = 0; i < boardSize; ++i) {
            diag1[i] = LinearExpr.newBuilder().add(queens[i]).add(i).build();
            diag2[i] = LinearExpr.newBuilder().add(queens[i]).add(-i).build();
        }
        model.addAllDifferent(diag1);
        model.addAllDifferent(diag2);
    }

    public SolutionResponse solve() {
        CpSolverSolutionCallback cb = new CpSolverSolutionCallback() {
            @Override
            public void onSolutionCallback() {
                solutionCount++;
                solution = new int[queens.length];
                System.out.println("-Solution: " + solutionCount);
                for (int row = 0; row < queens.length; ++row) {
                    for (int column = 0; column < queens.length; ++column) {
                        if (value(queens[column]) == row) {
                            solution[column] = row;
                            System.out.print("Q");
                        } else {
                            System.out.print("_");
                        }
                        if (column != queens.length - 1) {
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }
                System.out.println("-END-");
                System.out.println("");
            }
        };
        CpSolver solver = new CpSolver();
        solver.solve(model, cb);
        // Map solution[i] as row and i as column to Queen object.
        Set<Queen> queens = new HashSet<>();
        for (int i = 0; i < this.queens.length; ++i) {
            queens.add(new Queen(new Column(i + 1), new Row(solution[i] + 1)));
        }
        return new SolutionResponse(
                "%s".formatted(solutionCount),
                queens);
    }
}
