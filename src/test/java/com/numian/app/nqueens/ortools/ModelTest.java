package com.numian.app.nqueens.ortools;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import com.numian.app.nqueens.common.domain.Column;
import com.numian.app.nqueens.common.domain.Queen;
import com.numian.app.nqueens.common.domain.Row;

public class ModelTest {
    @Test
    public void test4QueensSolve() {
        Model model = new Model(4);
        var solutionResponse = model.solve();
        assertEquals(Set.of(
                new Queen(new Column(1), new Row(3)),
                new Queen(new Column(2), new Row(1)),
                new Queen(new Column(3), new Row(4)),
                new Queen(new Column(4), new Row(2))), solutionResponse.queens());
    }
}
