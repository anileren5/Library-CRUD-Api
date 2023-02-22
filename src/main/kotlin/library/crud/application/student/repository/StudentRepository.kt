package library.crud.application.student.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import library.crud.application.student.entity.Student

@Repository
interface StudentRepository : JpaRepository<Student, Long>{
}