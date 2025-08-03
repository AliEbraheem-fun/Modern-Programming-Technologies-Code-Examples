package lecture.eight.student.service;

import lecture.eight.student.model.Student;

import java.util.List;

/**
 * Service interface for managing {@link Student} entities.
 * <p>
 * Provides basic CRUD operations that can be implemented by any service class.
 * </p>
 */
public interface StudentService {

    /**
     * Retrieve all students.
     *
     * @return list of all students
     */
    List<Student> getAll();

    /**
     * Find a student by their ID.
     *
     * @param id the ID of the student
     * @return the found {@link Student}, or null if not found
     */
    Student getById(Long id);

    /**
     * Save or update a student.
     *
     * @param student the student to save
     * @return the saved {@link Student}
     */
    Student save(Student student);

    /**
     * Delete a student by ID.
     *
     * @param id the ID of the student to delete
     */
    void deleteById(Long id);
}
