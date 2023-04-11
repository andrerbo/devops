package edu.br.myclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.br.myclass.controller.StudentRequestModel;
import edu.br.myclass.usecase.StudentInteractor;

@Component
public class StudentCommandLineRunner implements CommandLineRunner {

    @Autowired
    private StudentInteractor interactor;

    @Override
    public void run(String... args) throws Exception {
        interactor.createStudent(new StudentRequestModel("Andre", "andre@gmail.com"));
    }

}
