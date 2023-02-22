package library.crud.application.student.controller

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import library.crud.application.common.dto.ErrorDTO
import library.crud.application.common.dto.MessageResponse
import library.crud.application.student.controller.request.AddBookToStudentRequest
import library.crud.application.student.controller.request.AddStudentRequest
import library.crud.application.student.controller.request.UpdateStudentRequest
import library.crud.application.student.controller.response.StudentQueryResponse
import library.crud.application.student.entity.Student
import library.crud.application.student.service.StudentService
import java.time.LocalDateTime

@RestController
@RequestMapping("/students")
class StudentController @Autowired constructor(val studentService: StudentService) {

    @GetMapping
    fun getAllStudents() : List<StudentQueryResponse> = studentService.getAllStudents().map {student: Student ->  StudentQueryResponse(student)}.toList()

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id : Long) : StudentQueryResponse = StudentQueryResponse(studentService.getStudentById(id))

    @PostMapping
    fun addStudent(@Valid @RequestBody addStudentRequest: AddStudentRequest) : MessageResponse {
        val student : Student = addStudentRequest.toEntity()
        val messageResponse = studentService.addStudent(student)
        return studentService.addStudent(student)

    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long) : MessageResponse = studentService.deleteStudent(id)

    @PostMapping("/{id}")
    fun addBookToStudent(@PathVariable id: Long, @Valid @RequestBody request: AddBookToStudentRequest) : MessageResponse = studentService.addBook(id, request.toEntity())


    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id : Long, @Valid @RequestBody updateStudentRequest: UpdateStudentRequest) : MessageResponse = studentService.updateStudent(id, updateStudentRequest.toEntity())


    @ExceptionHandler(value = [NoSuchElementException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNoSuchElementException(noSuchElementException: NoSuchElementException) : ErrorDTO = ErrorDTO(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "User not found!", mutableListOf<String?>())
}