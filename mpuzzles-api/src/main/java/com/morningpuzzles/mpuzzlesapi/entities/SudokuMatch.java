package com.morningpuzzles.mpuzzlesapi.entities;

import java.time.LocalDate;

public class SudokuMatch {
    private long Id;
    private short[][] grid;
    private short[][] solution;
    private LocalDate date;

    public SudokuMatch() {}

    public SudokuMatch(long id, short[][] grid, int[][] solution, LocalDate date) {
        Id = id;
        this.grid = grid;
        this.date = date;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public short[][] getGrid() {
        return grid;
    }

    public void setGrid(short[][] grid) {
        this.grid = grid;
    }

    public short[][] getSolution() {
        return solution;
    }

    public void setSolution(short[][] solution) {
        this.solution = solution;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
