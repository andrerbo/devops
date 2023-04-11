package edu.br.myclass.model.domain;

public class StudentFactoryImpl implements StudentFactory {

    @Override
    public Student create(String name, String email) {
        return new StudentImpl(name, email);
    }
    
}
