package com.epam.lab.hospitalspring.transfer;


import com.epam.lab.hospitalspring.model.Personal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PersonalDto {
    private String firstName;
    private String lastName;

    public static PersonalDto from (Personal personal) {
        return PersonalDto.builder()
                .lastName(personal.getLastName())
                .firstName(personal.getFirstName())
                .build();
    }
}
