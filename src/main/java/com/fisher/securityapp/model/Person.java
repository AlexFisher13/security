package com.fisher.securityapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1900, message = "Год не должен быть меньше 1900")
    private int yearOfBirth;
    @NotEmpty
    @Size(min = 2, max = 45, message = "Имя должно быть до 45 символов")
    private String username;
    private String password;
    private String role;
}
