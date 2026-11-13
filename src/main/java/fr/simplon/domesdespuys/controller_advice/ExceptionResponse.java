package fr.simplon.domesdespuys.controller_advice;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domesdespuys.view.ExceptionView;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    @JsonView(ExceptionView.Message.class)
    private String message;

    @JsonView(ExceptionView.Status.class)
    private int status;

    @JsonView(ExceptionView.Name.class)
    private String name;
}
