package lecture.eight.student.service;

import lecture.eight.student.model.Student;
import lecture.eight.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link StudentService} that interacts with the
 * {@link StudentRepository} to perform database operations on {@link Student} entities.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student getById(Long id) {
        return repository.findById(id).orElse(new Student());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
