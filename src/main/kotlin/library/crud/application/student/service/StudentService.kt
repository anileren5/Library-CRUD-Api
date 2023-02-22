package library.crud.application.student.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import library.crud.application.common.dto.MessageResponse
import library.crud.application.common.dto.MessageType
import library.crud.application.book.entity.Book
import library.crud.application.student.entity.Student
import library.crud.application.student.repository.StudentRepository

@Service
class StudentService @Autowired constructor(private val studentRepository: StudentRepository) {

    @Transactional
    fun addStudent(student: Student) : MessageResponse{
        studentRepository.save(student)
        return MessageResponse("Student has been saved successfully", MessageType.SUCCESS)
    }

    fun getAllStudents(): List<Student> = studentRepository.findAll()
    fun getStudentById(id : Long): Student = studentRepository.findById(id).orElseThrow()

    @Transactional
    fun deleteStudent(id: Long): MessageResponse {
        if (!studentRepository.existsById(id)) throw NoSuchElementException()
        studentRepository.deleteById(id)
        return MessageResponse("Student with id $id has been deleted successfully!", MessageType.SUCCESS)
    }

    @Transactional
    fun addBook(id: Long, book: Book): MessageResponse {
        if (!studentRepository.existsById(id)) throw NoSuchElementException()
        val student : Student = studentRepository.findById(id).get()
        try {
            student.addBook(book)
            return MessageResponse("Book named ${book.name} has been added successfully!", MessageType.SUCCESS)
        } catch (e : Exception){
            return MessageResponse(e.message.toString(), MessageType.ERROR)
        }
    }

    @Transactional
    fun updateStudent(id: Long, updatedStudent: Student) : MessageResponse {
        try {
            val student: Student = studentRepository.findById(id).orElseThrow()
            student.updateStudent(updatedStudent)
            studentRepository.save(student)
            return MessageResponse("Student with id $id has been updated successfully!", MessageType.SUCCESS);
        } catch (e : Exception){
            return  MessageResponse("Student with id $id not found!", MessageType.ERROR)
        }
    }

    fun isExistStudent(id: Long) : Boolean = studentRepository.existsById(id)


}