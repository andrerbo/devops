package edu.br.myclass.infrastructure.datamapper;

import edu.br.myclass.infrastructure.repository.StudentRepository;

public class StudentGatewayImpl implements StudentGateway {

    private final StudentRepository repository;

    public StudentGatewayImpl(StudentRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public void saveStudent(StudentDataMapperDTO model) {
        StudentDataMapper studentMapper = new StudentDataMapper(model.getName(),
            model.getEmail(), model.getCreatedAt());
        repository.save(studentMapper);
    }

    @Override
    public StudentDataMapperDTO fetchStudent(Integer id) {
        StudentDataMapper model = repository.findById(id).get();
        return new StudentDataMapperDTO(model.getName(), model.getEmail(),
            model.getCreatedAt());
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
    
}
