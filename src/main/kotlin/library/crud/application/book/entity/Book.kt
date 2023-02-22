package library.crud.application.book.entity

import jakarta.persistence.Entity
import library.crud.application.common.entity.BaseEntity
import java.time.LocalDate

@Entity
class Book constructor(
    var name: String,
    var publishDate: LocalDate,
    var pageCount: Int
) : BaseEntity() {

    constructor() : this("defaultBookName",LocalDate.now(), 0) {

    }

    fun hasSameNameAs(toBeAddedBook : Book) : Boolean = this.name == toBeAddedBook.name
}