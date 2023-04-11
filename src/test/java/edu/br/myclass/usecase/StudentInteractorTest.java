package edu.br.myclass.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.br.myclass.controller.StudentPresenterImpl;
import edu.br.myclass.controller.StudentRequestModel;
import edu.br.myclass.controller.StudentResponseModel;
import edu.br.myclass.infrastructure.datamapper.StudentDataMapperDTO;
import edu.br.myclass.infrastructure.datamapper.StudentGatewayImpl;
import edu.br.myclass.model.domain.StudentFactoryImpl;
import edu.br.myclass.model.domain.StudentImpl;
import edu.br.myclass.usecase.exception.EmailInvalidException;
import edu.br.myclass.usecase.exception.EntityNotFoundException;
import edu.br.myclass.usecase.exception.StudentExistsException;

@ExtendWith(SpringExtension.class)
public class StudentInteractorTest {
    
    @Mock
    private StudentGatewayImpl gateway;
    @Mock
    private StudentFactoryImpl factory;
    @Mock
    private StudentPresenterImpl presenter;
    @InjectMocks
    private StudentInteractorImpl interactor;

    @Test
    public void testSaveStudent_sucess() throws EmailInvalidException, StudentExistsException {
        String name = new String("Andre");
        String email = new String("andre@gmail.com");
        LocalDateTime creationTime = LocalDateTime.now();
        StudentRequestModel request = new StudentRequestModel(name, email);

        when(factory.create(anyString(), anyString()))
            .thenReturn(new StudentImpl(name, email));
        
        when(gateway.existsByName(anyString()))
            .thenReturn(false);

        when(presenter.success(any(StudentResponseModel.class)))
            .thenReturn(new StudentResponseModel(name, email, creationTime));

        StudentResponseModel response = interactor.createStudent(request);

        assertEquals(name, response.getName());
        assertEquals(email, response.getEmail());
        assertEquals(creationTime, response.getCreatedAt());

    }


    @Test
    public void testSaveStudent_failWithInvalidEmail() throws EmailInvalidException, StudentExistsException {
        String name = new String("Andre");
        String email = new String("andre-gmail.com");
        LocalDateTime creationTime = LocalDateTime.now();
        StudentRequestModel request = new StudentRequestModel(name, email);

        when(factory.create(anyString(), anyString()))
            .thenReturn(new StudentImpl(name, email));
        
        when(gateway.existsByName(anyString()))
            .thenReturn(false);

        when(presenter.success(any(StudentResponseModel.class)))
            .thenReturn(new StudentResponseModel(name, email, creationTime));

        
        EmailInvalidException thrown = assertThrows(EmailInvalidException.class,
            () -> interactor.createStudent(request));

        assertTrue(thrown.getMessage().contentEquals("INVALID_EMAIL"));
    }

    @Test
    public void testSaveStudent_failStudentExists() throws EmailInvalidException, StudentExistsException {
        String name = new String("Andre");
        String email = new String("andre@gmail.com");
        LocalDateTime creationTime = LocalDateTime.now();
        StudentRequestModel request = new StudentRequestModel(name, email);

        when(factory.create(anyString(), anyString()))
            .thenReturn(new StudentImpl(name, email));
        
        when(gateway.existsByName(anyString()))
            .thenReturn(true);

        when(presenter.success(any(StudentResponseModel.class)))
            .thenReturn(new StudentResponseModel(name, email, creationTime));

        
        StudentExistsException thrown = assertThrows(StudentExistsException.class,
            () -> interactor.createStudent(request));

        assertTrue(thrown.getMessage().contentEquals("STUDENT_EXISTS"));
    }


    @Test
    public void testfecthStudent_sucess() throws EntityNotFoundException {
        String name = new String("Andre");
        String email = new String("andre@gmail.com");
        LocalDateTime creationTime = LocalDateTime.now();
        
        when(gateway.existsById(anyInt()))
            .thenReturn(true);

        when(gateway.fetchStudent(anyInt()))
            .thenReturn(new StudentDataMapperDTO(name, email, creationTime));

        when(presenter.success(any(StudentResponseModel.class)))
            .thenReturn(new StudentResponseModel(name, email, creationTime));
        
        StudentResponseModel response = interactor.getStudent(1);
        assertEquals(name, response.getName());
        assertEquals(email, response.getEmail());
        assertEquals(creationTime, response.getCreatedAt());
    }


    @Test
    public void testfecthStudent_entityNotFoun() throws EntityNotFoundException {
        String name = new String("Andre");
        String email = new String("andre@gmail.com");
        LocalDateTime creationTime = LocalDateTime.now();
        
        when(gateway.existsById(anyInt()))
            .thenReturn(false);

        when(gateway.fetchStudent(anyInt()))
            .thenReturn(new StudentDataMapperDTO(name, email, creationTime));

        when(presenter.success(any(StudentResponseModel.class)))
            .thenReturn(new StudentResponseModel(name, email, creationTime));
        
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
        () -> interactor.getStudent(1));

        assertTrue(thrown.getMessage().contentEquals("ENTITY_NOT_FOUND"));
    }
    
}
