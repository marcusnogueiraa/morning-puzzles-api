package com.morningpuzzles.mpuzzlesapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.morningpuzzles.mpuzzlesapi.dto.SudokuMatchDTO;
import com.morningpuzzles.mpuzzlesapi.dto.SudokuResponseDTO;
import com.morningpuzzles.mpuzzlesapi.services.SudokuService;

@RestController
@RequestMapping("/api/sudoku/")
public class SudokuController {
    
    @Autowired
    private SudokuService sudokuService;

    @GetMapping("today")
    SudokuMatchDTO getSudokuMatch() {
        return sudokuService.getSudokuMatch();
    }

    @PostMapping("submit")
    SudokuResponseDTO submiteSolution(@RequestBody SudokuMatchDTO sudokuSubmissionDTO) {
        return sudokuService.submiteSolution(sudokuSubmissionDTO);
    }

}
