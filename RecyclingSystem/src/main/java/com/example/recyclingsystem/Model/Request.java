package com.example.recyclingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null unique")
    private String request_code;

    @NotEmpty(message = "waste type should not be empty")
    @Column(columnDefinition = "varchar(40) not null")
    private String waste_type;

    @NotNull(message = "weight should not be empty")
    @Column(columnDefinition = "double not null")
    private Double weight;

    @Column(columnDefinition = "int not null")
    private Integer resident_id;

    @Column(columnDefinition = "int")
    private Integer company_id;

}
