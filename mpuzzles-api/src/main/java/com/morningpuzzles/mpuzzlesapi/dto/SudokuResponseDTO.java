package com.morningpuzzles.mpuzzlesapi.dto;

public class SudokuResponseDTO {
    private boolean isCorrect;

    public SudokuResponseDTO(){}
    
    public SudokuResponseDTO(boolean status){
        isCorrect = status;
    }

    public void setIsCorrect(boolean status){
        isCorrect = status;
    }

    public boolean getIsCorrect(){
        return isCorrect;
    }
}
