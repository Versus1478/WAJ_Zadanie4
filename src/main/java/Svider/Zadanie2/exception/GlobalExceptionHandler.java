package Svider.Zadanie2.exception;

import Svider.Zadanie2.response.ResponseFactory;
import Svider.Zadanie2.response.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ResponseFactory response;

    public GlobalExceptionHandler(ResponseFactory response) {
        this.response = response;
    }

    // Validačné chyby (@Valid @RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleValidation(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", String.valueOf(error.getDefaultMessage())
                ))
                .toList();

        return response.error(HttpStatus.BAD_REQUEST, "Validačné chyby", errors);
    }

    // Neplatný typ parametra v URL, napr. /employees/abc
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Parameter '" + ex.getName() + "' má neplatnú hodnotu";
        return response.error(HttpStatus.BAD_REQUEST, message);
    }

    // Nečitateľné JSON telo (rozbitý JSON, zlý formát dátumu birthDate, text namiesto čísla v salary...)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleNotReadable(HttpMessageNotReadableException ex) {
        return response.error(HttpStatus.BAD_REQUEST,
                "Telo požiadavky nie je platný JSON alebo obsahuje hodnotu v nesprávnom formáte "
                        + "(napr. birthDate musí byť v tvare RRRR-MM-DD)");
    }

    // Chýbajúci povinný query parameter, napr. /employees/search bez firstName
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleMissingParam(MissingServletRequestParameterException ex) {
        return response.error(HttpStatus.BAD_REQUEST,
                "Chýba povinný parameter '" + ex.getParameterName() + "'");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return response.error(HttpStatus.METHOD_NOT_ALLOWED,
                "HTTP metóda '" + ex.getMethod() + "' nie je pre tento endpoint povolená");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleNoResource(NoResourceFoundException ex) {
        return response.error(HttpStatus.NOT_FOUND, "Požadovaný endpoint neexistuje");
    }

    // Biznis pravidlá
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleObjectNotFound(ObjectNotFoundException ex) {
        return response.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleEmailExists(EmailAlreadyExistsException ex) {
        List<Map<String, String>> errors = List.of(
                Map.of("field", "email", "message", ex.getMessage())
        );
        return response.error(HttpStatus.CONFLICT, "Email už existuje", errors);
    }

    // Všetky ostatné neošetrené výnimky
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Void>> handleGeneral(Exception ex) {
        // Detail chyby neposielame klientovi, len ho zalogujeme
        log.error("Neočakávaná chyba", ex);
        return response.error(HttpStatus.INTERNAL_SERVER_ERROR, "Nastala neočakávaná chyba");
    }
}
