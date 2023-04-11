package edu.br.myclass.usecase;

import edu.br.myclass.controller.StudentRequestModel;
import edu.br.myclass.controller.StudentResponseModel;
import edu.br.myclass.usecase.exception.EmailInvalidException;
import edu.br.myclass.usecase.exception.EntityNotFoundException;
import edu.br.myclass.usecase.exception.StudentExistsException;

public interface StudentInteractor {
    
    public StudentResponseModel createStudent(StudentRequestModel model) throws EmailInvalidException, StudentExistsException;

    public StudentResponseModel getStudent(Integer id) throws EntityNotFoundException;

}
