package library.crud.application.student.controller.request

import jakarta.validation.constraints.NotEmpty
import lombok.RequiredArgsConstructor
import library.crud.application.book.entity.Book
import java.time.LocalDate

@RequiredArgsConstructor
class AddBookToStudentRequest (
    @field:NotEmpty val name: String,
    val publishDate: LocalDate,
    val pageCount: Int,
){
    fun toEntity() : Book = Book(this.name, this.publishDate, this.pageCount)
}