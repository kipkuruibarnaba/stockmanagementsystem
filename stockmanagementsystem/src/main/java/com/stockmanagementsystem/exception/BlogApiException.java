package com.stockmanagementsystem.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Barnaba Mutai
 * Created on Saturday, July , 09, 2022
 */
public class BlogApiException extends  RuntimeException{
  private HttpStatus status;
  private String message;

  public BlogApiException(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public BlogApiException(String message, HttpStatus status, String message1) {
    super(message);
    this.status = status;
    this.message = message1;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
