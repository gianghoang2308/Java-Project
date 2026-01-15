package com.example.springapi09.dto.AssembleIn;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssembleInRequest{
   
    @NotNull(message="Ten quoc gia khong de trong!")
    private String country;
}