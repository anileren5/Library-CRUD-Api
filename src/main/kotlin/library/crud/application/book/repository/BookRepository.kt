package library.crud.application.book.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import library.crud.application.book.entity.Book

@Repository
interface BookRepository : JpaRepository<Book, Long> {
    @Query("SELECT * FROM book B WHERE B.student_id = :student_id", nativeQuery = true)
    fun findByStudentId(@Param("student_id") id : Long) : List<Book>
}