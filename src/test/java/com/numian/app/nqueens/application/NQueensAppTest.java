package com.numian.app.nqueens.application;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import com.numian.app.nqueens.common.domain.Column;
import com.numian.app.nqueens.common.domain.Queen;
import com.numian.app.nqueens.common.domain.Row;
import com.numian.app.nqueens.timefold.dto.SolutionResponse;

public class NQueensAppTest {
    @Test
    public void test4QueensSolve() {
        NQueensApp nQueensApp = new NQueensApp();
        SolutionResponse result = nQueensApp.solve(4, 1);
        assertEquals("0hard/0soft", result.score());
        assertEquals(Set.of(
            new Queen(new Column(1), new Row(2)),
            new Queen(new Column(2), new Row(4)),
            new Queen(new Column(3), new Row(1)),
            new Queen(new Column(4), new Row(3))
        ), result.queens());
    }
}
