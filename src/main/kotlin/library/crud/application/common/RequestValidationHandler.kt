package library.crud.application.common

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import library.crud.application.common.dto.ErrorDTO
import java.time.LocalDateTime

@RestControllerAdvice
class RequestValidationHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>
        = ResponseEntity(ErrorDTO(HttpStatus.BAD_REQUEST, LocalDateTime.now(),"Validation Error", ex.bindingResult.allErrors.stream().map { error -> error.defaultMessage}.toList()), HttpStatus.BAD_REQUEST)


}