package com.numian.app.nqueens.common.dto;

import java.util.Set;

import com.numian.app.nqueens.common.domain.Queen;

public record SolutionResponse(String score, Set<Queen> queens) {
    
}
