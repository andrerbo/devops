package edu.br.myclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.br.myclass.controller.StudentPresenterImpl;
import edu.br.myclass.infrastructure.datamapper.StudentGatewayImpl;
import edu.br.myclass.infrastructure.repository.StudentRepository;
import edu.br.myclass.model.domain.StudentFactoryImpl;
import edu.br.myclass.usecase.StudentInteractorImpl;

@SpringBootApplication
public class MyclassApplication {
    
    @Bean
    public StudentPresenterImpl studentPresenterImpl(){
        return new StudentPresenterImpl();
    }
    
    @Bean
    public StudentFactoryImpl studentFactoryImpl(){
        return new StudentFactoryImpl();
    }

    @Bean
    public StudentGatewayImpl studentGateway(StudentRepository repository){
        return new StudentGatewayImpl(repository);
    }
    
    @Bean
    public StudentInteractorImpl studentInteractor(StudentGatewayImpl studentGateway,
        StudentFactoryImpl studentFactory, StudentPresenterImpl studentPresenter){
        return new StudentInteractorImpl(studentGateway, studentFactory, studentPresenter);
    }
    
    
    public static void main(String[] args) {
        SpringApplication.run(MyclassApplication.class, args);
    }
}
