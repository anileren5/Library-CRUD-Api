package library.crud.application.book.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import library.crud.application.book.controller.response.BookResponseQuery
import library.crud.application.book.service.BookService
import library.crud.application.common.dto.ErrorDTO
import java.time.LocalDateTime

@RestController
@RequestMapping("/books")
class BookController @Autowired constructor(val bookService: BookService){
    @GetMapping
    fun getAllBooks() : List<BookResponseQuery> = bookService.getAllBooks().stream().map { book -> BookResponseQuery(book) }.toList()

    @GetMapping("/{student_id}")
    fun getBooksByStudentId(@PathVariable("student_id") id : Long) : List<BookResponseQuery> = bookService.getBooksByStudentId(id).stream().map { book -> BookResponseQuery(book) }.toList()

    @ExceptionHandler(value = [NoSuchElementException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNoSuchElementException(noSuchElementException: NoSuchElementException) : ErrorDTO = ErrorDTO(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "User not found!", mutableListOf<String?>())
}