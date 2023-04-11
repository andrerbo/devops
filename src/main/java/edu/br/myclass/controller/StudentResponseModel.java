package edu.br.myclass.controller;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class StudentResponseModel {
    
    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime responseTime;

}
