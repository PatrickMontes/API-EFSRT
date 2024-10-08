package pe.edu.cibertec.massapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Respuesta> handleAllExceptions(Exception ex, WebRequest request) {
        Respuesta errorRespuesta = Respuesta.builder()
                .estado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .mensaje(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorRespuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Respuesta> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Respuesta errorRespuesta = Respuesta.builder()
                .estado(HttpStatus.NOT_FOUND.value())
                .mensaje(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorRespuesta, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Respuesta> handleInvalidCredentialsException(InvalidCredentialsException ex, WebRequest request) {
        Respuesta errorRespuesta = Respuesta.builder()
                .estado(HttpStatus.BAD_REQUEST.value())
                .mensaje(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorRespuesta, HttpStatus.BAD_REQUEST);
    }

}
