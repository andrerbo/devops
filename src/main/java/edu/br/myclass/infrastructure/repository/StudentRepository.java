package edu.br.myclass.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.br.myclass.infrastructure.datamapper.StudentDataMapper;

@Repository
public interface StudentRepository extends CrudRepository<StudentDataMapper, Integer> {
    
    public boolean existsByName(String name);

}
