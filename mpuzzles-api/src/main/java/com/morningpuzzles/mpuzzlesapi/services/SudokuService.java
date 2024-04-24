package com.morningpuzzles.mpuzzlesapi.services;

import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.morningpuzzles.mpuzzlesapi.dto.SudokuMatchDTO;
import com.morningpuzzles.mpuzzlesapi.entities.SudokuMatch;

@Service
public class SudokuService {
    
    private SudokuMatch currentMatch;

    private String getRandomSudokuData() {
        String line;
        List<String> lines = new ArrayList<>();

        File file = new File("MorningPuzzlesAPI\\mpuzzles-api\\src\\main\\resources\\sudoku_set.txt");
        try (Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                lines.add(line);
            }
            sc.close();
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
            return null;
        }

        Random random = new Random();
        line = lines.get(random.nextInt(lines.size()));
        System.out.println(line);
        return line;
    }

    private void generateNewMatch() {
        String line = getRandomSudokuData();
        String[] data = line.split(",\\s*");
    
        SudokuMatch newSudokuMatch = new SudokuMatch();
        newSudokuMatch.setId(Long.parseLong(data[0]));
    
        short grid[][] = new short[9][9];
        short solution[][] = new short[9][9];
    
        int dataIndex = 0; // Começa a partir do índice 1, pois o índice 0 é o ID
    
        // Preenche as matrizes grid e solution
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (data[1].charAt(dataIndex) == '.'){
                    grid[i][j] = 0; dataIndex++;
                    continue;
                }
                grid[i][j] = (short) (data[1].charAt(dataIndex) - '0');
                dataIndex++;
            }
        }

        dataIndex = 0;
    
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solution[i][j] = (short) (data[2].charAt(dataIndex) - '0');
                dataIndex++;
            }
        }
    
        newSudokuMatch.setGrid(grid);
        newSudokuMatch.setSolution(solution);
        newSudokuMatch.setDate(LocalDate.now());
        this.currentMatch = newSudokuMatch;
    }
    

    public boolean submiteSolution(SudokuMatchDTO sudokuSubmissionDTO) {
        final short MAX = 9;

        short solutionGrid[][] = currentMatch.getSolution();
        short submitedGrid[][] = sudokuSubmissionDTO.getGrid();

        for(short i = 0; i < MAX; i++){
            for(short j = 0; j < MAX; j++) {
                if (solutionGrid[i][j] != submitedGrid[i][j]) {
                    System.out.println("i = " + i + " j = " + j);
                    System.out.println("solution = " + solutionGrid[i][j]);
                    System.out.println("submited = " + submitedGrid[i][j]);
                    return false;
                }
            }
        }

        return true;
    }

    public SudokuMatchDTO getSudokuMatch() {
        if (currentMatch == null) generateNewMatch();

        LocalDate now = LocalDate.now();
        LocalDate lastDate = currentMatch.getDate();

        boolean theGameIsNotOutOfDate = (
            now.getDayOfMonth() == lastDate.getDayOfMonth() &&
            now.getMonthValue() == lastDate.getMonthValue() &&
            now.getYear() == lastDate.getYear()
        );
        
        if (!theGameIsNotOutOfDate) generateNewMatch();
        return new SudokuMatchDTO(this.currentMatch);
    }
}
