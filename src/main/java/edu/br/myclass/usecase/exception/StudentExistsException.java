package edu.br.myclass.usecase.exception;

public class StudentExistsException extends Exception {

    public StudentExistsException(String error){
        super(error);
    }
    
}
