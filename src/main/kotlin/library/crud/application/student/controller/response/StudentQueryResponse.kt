package library.crud.application.student.controller.response

import library.crud.application.student.entity.Student

class StudentQueryResponse(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val tcKimlikNumber: String,
    val studentNumber: String
) {
    constructor(student : Student) : this(student.id, student.firstName, student.lastName, student.email, student.tcKimlikNumber, student.studentNumber)
}