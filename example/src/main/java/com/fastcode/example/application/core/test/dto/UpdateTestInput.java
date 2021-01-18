package com.fastcode.example.application.core.test.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTestInput {

    @NotNull(message = "id Should not be null")
    private Integer id;

    @NotNull(message = "version Should not be null")
    private Long version;

    private Long versiono;
}
