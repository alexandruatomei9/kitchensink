package com.example.kitchensink.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "members")
public class Member implements Serializable {

    @Id
    @Getter
    private String id;

    @Getter
    @Setter
    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @Getter
    @Setter
    @NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    @Column(name = "phone_number")
    private String phoneNumber;
}
