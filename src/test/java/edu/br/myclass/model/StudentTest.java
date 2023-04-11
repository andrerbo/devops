package edu.br.myclass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.br.myclass.model.domain.Student;
import edu.br.myclass.model.domain.StudentFactoryImpl;

@ExtendWith(SpringExtension.class)
public class StudentTest {
    
    @InjectMocks
    private StudentFactoryImpl factory;

    @Test
    public void createStudentEntity_sucess() {

        Student student = factory.create("Andre", "andre@gmail.com");
        assertEquals("Andre", student.getName());
        assertEquals("andre@gmail.com", student.getEmail());

    }

    @Test
    public void createStudentEntity_emailInvalid() {

        Student student = factory.create("Andre", "andre-gmail.com");
        assertFalse(student.isEmailValid());

    }

}
