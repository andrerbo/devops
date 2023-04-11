package edu.br.myclass.usecase;

import java.time.LocalDateTime;

import edu.br.myclass.controller.StudentPresenterImpl;
import edu.br.myclass.controller.StudentRequestModel;
import edu.br.myclass.controller.StudentResponseModel;
import edu.br.myclass.infrastructure.datamapper.StudentDataMapperDTO;
import edu.br.myclass.infrastructure.datamapper.StudentGatewayImpl;
import edu.br.myclass.model.domain.Student;
import edu.br.myclass.model.domain.StudentFactoryImpl;
import edu.br.myclass.usecase.exception.EmailInvalidException;
import edu.br.myclass.usecase.exception.EntityNotFoundException;
import edu.br.myclass.usecase.exception.StudentExistsException;

public class StudentInteractorImpl implements StudentInteractor{

    private final StudentGatewayImpl studentGateway;
    private final StudentFactoryImpl studentFactory;
    private final StudentPresenterImpl studentPresenter;

    public StudentInteractorImpl(StudentGatewayImpl studentGateway, StudentFactoryImpl studentFactory, StudentPresenterImpl studentPresenter) {
        this.studentGateway = studentGateway;
        this.studentFactory = studentFactory;
        this.studentPresenter = studentPresenter;
    }

    public StudentResponseModel createStudent(StudentRequestModel request) throws EmailInvalidException, StudentExistsException {
        if(studentGateway.existsByName(request.getName())){
            throw new StudentExistsException("STUDENT_EXISTS");
        }

        Student student = studentFactory.create(request.getName(),
            request.getEmail());

        if(!student.isEmailValid()){
            throw new EmailInvalidException("INVALID_EMAIL");
        }

        LocalDateTime now = LocalDateTime.now();
        StudentDataMapperDTO studentDataMapperDTO = new StudentDataMapperDTO(request.getName(),
            request.getEmail(), now);          
        studentGateway.saveStudent(studentDataMapperDTO);
        StudentResponseModel response = new StudentResponseModel(request.getName(), request.getEmail(), now);
        
        return studentPresenter.success(response);
    }

    @Override
    public StudentResponseModel getStudent(Integer id) throws EntityNotFoundException {
        if(!studentGateway.existsById(id)){
            throw new EntityNotFoundException("ENTITY_NOT_FOUND");
        }

        StudentDataMapperDTO studentDataMapperDTO = studentGateway.fetchStudent(id);
        StudentResponseModel response = new StudentResponseModel(studentDataMapperDTO.getName(),
            studentDataMapperDTO.getEmail(), studentDataMapperDTO.getCreatedAt());
        return studentPresenter.success(response);
    }
    
}
