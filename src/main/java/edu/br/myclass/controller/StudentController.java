package edu.br.myclass.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import edu.br.myclass.usecase.StudentInteractorImpl;
import edu.br.myclass.usecase.exception.EmailInvalidException;
import edu.br.myclass.usecase.exception.EntityNotFoundException;
import edu.br.myclass.usecase.exception.StudentExistsException;

@RestController
public class StudentController {

    @Autowired
    private RestTemplate restTemplate;

    private final StudentInteractorImpl studentInteractor;

    // private final ObservationRegistry registry;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public StudentController(StudentInteractorImpl studentInteractor, RestTemplate restTemplate/*, ObservationRegistry registry*/){
        this.studentInteractor = studentInteractor;
        this.restTemplate = restTemplate;
        // this.registry = registry;
    }

    @PostMapping("/students")
    public void newStudent(@RequestBody StudentRequestModel request) throws EmailInvalidException, StudentExistsException {
      try {
        
        log.info(request.toString());
        studentInteractor.createStudent(request);
      } 
      catch(EmailInvalidException e){
        log.error(e.getMessage());
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
      }
      catch(StudentExistsException e){
        log.error(e.getMessage());
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
      }

    }

    @GetMapping("/students/{id}")
    public StudentResponseModel getStudent(@PathVariable Integer id){
      try{
        log.info("requesting student with " + id);
        StudentResponseModel response = studentInteractor.getStudent(id);
        return response;
      }
      catch(EntityNotFoundException e) {
        log.error(e.getMessage());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      }

    }

    @GetMapping("/devops")
    public StudentResponseModel devops(){

      log.info("teste");
      String url = "http://localhost:8080/students/1";
      ResponseEntity<StudentResponseModel> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<StudentResponseModel>() { });
      StudentResponseModel reponseModel = response.getBody();
      
      return reponseModel;

    }

}
