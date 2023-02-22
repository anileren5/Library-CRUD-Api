package library.crud.application.common.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorDTO(
    val httpStatus: HttpStatus,
    val time: LocalDateTime,
    val message: String,
    val details: List<String?>
) {
}
