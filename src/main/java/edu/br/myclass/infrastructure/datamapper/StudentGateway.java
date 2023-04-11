package edu.br.myclass.infrastructure.datamapper;

public interface StudentGateway {
    
    public void saveStudent(StudentDataMapperDTO requestModel);

    public StudentDataMapperDTO fetchStudent(Integer id);

    public boolean existsByName(String name);

    public boolean existsById(Integer id);

}
