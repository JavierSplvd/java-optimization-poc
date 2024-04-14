package com.numian.app.nqueens.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NQueensAppTest {
    @Test
    public void test4QueensSolve() {
        NQueensApp nQueensApp = new NQueensApp();
        String result = nQueensApp.solve(4, 1);
        assertEquals(result, "0hard/0soft,<c3,r1> <c4,r3> <c1,r2> <c2,r4> ");
    }

    @Test
    public void test8QueensSolve() {
        NQueensApp nQueensApp = new NQueensApp();
        String result = nQueensApp.solve(8, 5);
        assertEquals(result, "0hard/0soft,<c5,r1> <c3,r7> <c1,r4> <c4,r5> <c2,r2> <c8,r3> <c7,r6> <c6,r8> ");
    }

    @Test
    public void test16QueensSolveWithExtendedTime() {
        NQueensApp nQueensApp = new NQueensApp();
        String result = nQueensApp.solve(16, 20);
        assertEquals(result, "foo");
    }
}
