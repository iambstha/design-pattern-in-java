package com.bishal.factorydesign.domain;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

	private HttpStatus status;
	private Integer statusCode;
	private String message;
	private Object data;
	private List<String> details;
	
    public ErrorResponse(HttpStatus notFound,String message, List<String> details){
        super();
        this.status= notFound;
        this.message = message;
        this.details = details;
    }

}
