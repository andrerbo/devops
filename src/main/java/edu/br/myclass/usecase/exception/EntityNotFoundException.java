package edu.br.myclass.usecase.exception;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(String error){
        super(error);
    }
    
}
