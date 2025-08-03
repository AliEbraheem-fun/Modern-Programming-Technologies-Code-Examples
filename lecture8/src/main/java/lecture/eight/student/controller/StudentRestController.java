package lecture.eight.student.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lecture.eight.student.model.Student;
import lecture.eight.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Student entities.
 */
@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Returns all students.
     *
     * @return list of students
     */
    @Operation(summary = "Get all students", description = "Retrieves a list of all students")
    @ApiResponse(responseCode = "200", description = "Students fetched successfully")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Creates a new student.
     *
     * @param student student data
     * @return created student with HTTP 201
     */
    @Operation(summary = "Create student", description = "Adds a new student to the system")
    @ApiResponse(responseCode = "201", description = "Student created successfully")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Updates an existing student by ID.
     *
     * @param id      student ID
     * @param updated updated student data
     * @return updated student or 404 if not found
     */
    @Operation(summary = "Update student", description = "Updates a student by their ID")
    @ApiResponse(responseCode = "200", description = "Student updated successfully")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student updated) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        Student student = optional.get();
        student.setName(updated.getName());
        student.setSurname(updated.getSurname());
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    /**
     * Deletes a student by ID.
     *
     * @param id student ID
     * @return HTTP 200 or 404
     */
    @Operation(summary = "Delete student", description = "Deletes a student by ID")
    @ApiResponse(responseCode = "200", description = "Student deleted successfully")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student deleted");
    }
}
