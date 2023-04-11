package edu.br.myclass.model.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudentImpl implements Student {

    @NonNull
    private String name;

    @NonNull
    private String email;

    public boolean isEmailValid(){
        if(!this.email.contains("@")){
            return false;
        }
        return true;
    } 

}
