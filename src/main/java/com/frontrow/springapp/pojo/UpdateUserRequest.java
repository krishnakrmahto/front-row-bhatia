package com.frontrow.springapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserRequest {

    @NotNull
    private Long id;

    private String name;

    private String email;
}
