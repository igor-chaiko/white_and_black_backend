package black_and_while.exceptionHandler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errorMessages =
            ex.bindingResult.fieldErrors
                .joinToString(", ") { it.defaultMessage ?: "Invalid value" }

        val response = mapOf("message" to errorMessages)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}