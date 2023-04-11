package edu.br.myclass.controller;

import java.time.LocalDateTime;

public class StudentPresenterImpl implements StudentPresenter {

    @Override
    public StudentResponseModel success(StudentResponseModel response) {
        response.setResponseTime(LocalDateTime.now());
        return response;
    }

    // @Override
    // public StudentResponseModel entityNotFoundError(ErrorCatalog error) throws EntityNotFoundException {
    //     throw new EntityNotFoundException(error.toString());
    // }

    // @Override
    // public StudentResponseModel emailInvalidError(ErrorCatalog error) throws EmailInvalidException {
    //     throw new EmailInvalidException(error.toString());
    // }

    // @Override
    // public StudentResponseModel studentExistsError(ErrorCatalog error) throws StudentExistsException {
    //     throw new StudentExistsException(error.toString());
    // }

}
