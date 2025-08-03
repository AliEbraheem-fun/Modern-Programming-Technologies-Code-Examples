package lecture.eight.student.repository;

import lecture.eight.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Student} entities.
 *
 * Inherits CRUD operations from {@link JpaRepository}, including:
 * <ul>
 *     <li>findAll()</li>
 *     <li>findById(Long id)</li>
 *     <li>save(Student student)</li>
 *     <li>deleteById(Long id)</li>
 * </ul>
 *
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
