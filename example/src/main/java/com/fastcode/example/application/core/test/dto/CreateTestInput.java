package com.fastcode.example.application.core.test.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTestInput {

    @NotNull(message = "version Should not be null")
    private Long version;
}
