package library.crud.application.student.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import lombok.RequiredArgsConstructor
import library.crud.application.student.entity.Student

@RequiredArgsConstructor
class AddStudentRequest constructor(
    @field:NotEmpty val firstName: String,
    @field:NotEmpty val lastName: String,
    @field:Email val email : String,
    @field:Size(min = 11, max = 11, message = "TC Identity Number must consist of 11 digit!") val tcKimlikNumber: String,
    @field:Size(min = 7, max = 7, message = "Student must consist of 7 digit!") val studentNumber : String
){
    fun toEntity() : Student = Student(firstName, lastName, email, tcKimlikNumber, studentNumber)
}