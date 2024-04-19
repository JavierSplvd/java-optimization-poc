package com.numian.app.nqueens.timefold.dto;

import java.util.Set;

import com.numian.app.nqueens.common.domain.Queen;

public record SolutionResponse(String score, Set<Queen> queens) {
    
}
