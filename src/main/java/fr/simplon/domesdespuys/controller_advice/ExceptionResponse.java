package fr.simplon.domesdespuys.controller_advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    String message;
    int status;
    String name;
}
