package library.crud.application.book.controller.response

import library.crud.application.book.entity.Book
import java.time.LocalDate

class BookResponseQuery(
    val id: Long?,
    val name: String,
    val publishDate: LocalDate,
    var pageCount: Int
) {
    constructor(book : Book) : this(book.id, book.name, book.publishDate, book.pageCount)
}