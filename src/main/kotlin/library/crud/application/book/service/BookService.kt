package library.crud.application.book.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import library.crud.application.book.entity.Book
import library.crud.application.book.repository.BookRepository
import library.crud.application.student.service.StudentService

@Service
class BookService @Autowired  constructor(val bookRepository: BookRepository, val studentService: StudentService){
    fun getAllBooks(): List<Book> = bookRepository.findAll()
    fun getBooksByStudentId(id : Long): List<Book> {
        if (!studentService.isExistStudent(id)) throw NoSuchElementException("Student with id $id not found!")
        return bookRepository.findByStudentId(id)
    }
}