package edu.br.myclass.infrastructure.datamapper;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentDataMapperDTO {

    private String name;

    private String email;

    private LocalDateTime createdAt;

}
