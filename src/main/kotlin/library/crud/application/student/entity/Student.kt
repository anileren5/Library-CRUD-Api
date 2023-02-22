package library.crud.application.student.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import library.crud.application.book.entity.Book
import library.crud.application.common.dto.MessageResponse
import library.crud.application.common.dto.MessageType
import library.crud.application.common.entity.BaseEntity
import java.time.LocalDate

@Entity
@Table(name = "student")
class Student(
    var firstName: String,
    var lastName: String,
    var email: String,
    var tcKimlikNumber: String,
    var studentNumber: String
) : BaseEntity() {

    constructor() : this("defaultFirstName", "defaultLastName", "defaultEmail", "defaultTcKimlikNumber", "defaultStudentNumber")

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    var books: MutableList<Book> = listOf<Book>().toMutableList()

    init {
        val algorithmsBook : Book = Book("Introduction to Algorithms", LocalDate.parse("1989-01-01"), 1312)
        this.books.add(algorithmsBook)
    }

    private fun hasSameBook(bookToBeAdded : Book) : Boolean = books.stream().anyMatch({ book -> book.hasSameNameAs(bookToBeAdded)})

    private fun canAddBook(bookToBeAdded: Book) : MessageResponse =  if (this.hasSameBook(bookToBeAdded)) MessageResponse("Student has already the book ${bookToBeAdded}!", MessageType.ERROR) else MessageResponse("", MessageType.SUCCESS)

    fun addBook(bookToBeAdded: Book) : Unit{
        if (this.canAddBook(bookToBeAdded).hasError()) throw Exception("Student with student number ${this.studentNumber} already had the book with name ${bookToBeAdded.name}!")
        else {
            this.books.add(bookToBeAdded)
            println(books)
        }
    }

    fun updateStudent(updatedStudent: Student) {
        this.firstName = updatedStudent.firstName
        this.lastName =  updatedStudent.lastName
        this.email = updatedStudent.email
        this.tcKimlikNumber = updatedStudent.email
        this.studentNumber = updatedStudent.studentNumber
    }
}















