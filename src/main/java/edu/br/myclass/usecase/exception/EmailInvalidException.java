package edu.br.myclass.usecase.exception;

public class EmailInvalidException extends Exception{

    public EmailInvalidException(String error){
        super(error);
    }
    
}
