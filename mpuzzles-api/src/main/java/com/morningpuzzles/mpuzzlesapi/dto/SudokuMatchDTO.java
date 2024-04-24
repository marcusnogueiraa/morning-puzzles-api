package com.morningpuzzles.mpuzzlesapi.dto;

import com.morningpuzzles.mpuzzlesapi.entities.SudokuMatch;

public class SudokuMatchDTO {
    private short[][] grid;

    public SudokuMatchDTO() {}
    public SudokuMatchDTO(SudokuMatch sudokuMatch) {grid = sudokuMatch.getGrid();}

    public short[][] getGrid() {
        return grid;
    }

    public void setGrid(short grid[][]) {
        this.grid = grid;
    }
}
