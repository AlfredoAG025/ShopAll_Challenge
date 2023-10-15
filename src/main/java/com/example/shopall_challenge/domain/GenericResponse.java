package com.example.shopall_challenge.domain;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GenericResponse {
    private int code;
    private String message;
    private List data;
}
