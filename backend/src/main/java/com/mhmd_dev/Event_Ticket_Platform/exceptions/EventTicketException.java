package com.mhmd_dev.Event_Ticket_Platform.exceptions;

//RuntimeException is the base class for all exceptions that can be thrown during the normal operation of the Java Virtual Machine.In simpler words run-time exceptions are those exceptions that are not checked at compile time and are instead checked at run time.
public class EventTicketException extends RuntimeException {

  public EventTicketException() {
  }

  public EventTicketException(String message) {
    super(message);
  }

  public EventTicketException(Throwable cause) {
    super(cause);
  }

  public EventTicketException(String message, Throwable cause) {
    super(message, cause);
  }

  public EventTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
  
}
