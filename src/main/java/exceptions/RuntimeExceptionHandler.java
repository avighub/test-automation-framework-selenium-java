package exceptions;

public class RuntimeExceptionHandler extends RuntimeException {
  public RuntimeExceptionHandler(String message, Throwable cause) {
    super(message, cause);
  }
}