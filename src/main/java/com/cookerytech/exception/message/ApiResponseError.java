package com.cookerytech.exception.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponseError {

    //Bu classın amacı: benim istediğim şekilde mesaj şablonları oluşturmak

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss") //çıktılar json formatında çıktığı için bizde bu çiktiları değiştireceğiz
    private LocalDateTime timestamp;

    private String message;

    private String requestURI;

    //GETTER- SETTER

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }


    //Const
    private ApiResponseError (){

        timestamp = LocalDateTime.now();
    }

    public ApiResponseError(HttpStatus status){
        this();
        this.message="Unexpected Error";
        this.status=status;
    }

    public ApiResponseError(HttpStatus status, String message, String requestURI){
        this(status);
        this.message=message;
        this.requestURI=requestURI;
    }


}
