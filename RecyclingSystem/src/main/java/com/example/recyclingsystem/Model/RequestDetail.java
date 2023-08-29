package com.example.recyclingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "request code should not be empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String request_code;

    @NotEmpty(message = "status should not be empty")
    @Column(columnDefinition = "varchar(10) check (status = \"confirmed\" or status = \"pending\")")
    @Pattern(regexp = "\\W*((?i)confirmed|pending(?-i))\\W*", message = "status should be pending or confirmed only")
    private String status;

}
