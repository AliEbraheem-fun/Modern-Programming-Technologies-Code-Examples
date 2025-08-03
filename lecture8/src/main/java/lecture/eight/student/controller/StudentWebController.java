package lecture.eight.student.controller;

import lecture.eight.student.model.Student;
import lecture.eight.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling student-related web operations using Thymeleaf templates.
 */
@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Displays the list of all students and the add form.
     *
     * @param model Spring MVC model to populate data
     * @return view name for student list page
     */
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("student", new Student()); // Needed for the form
        return "students";
    }

    /**
     * Displays a standalone add form (not used if form is in the main page).
     *
     * @param model Spring MVC model
     * @return view name for the add form
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    /**
     * Handles submission of new student data.
     *
     * @param student form-bound student object
     * @return redirect to the list page
     */
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    /**
     * Deletes a student by ID.
     *
     * @param id ID of the student to delete
     * @return redirect to the list page
     */
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    /**
     * Switches to edit mode for a student.
     *
     * @param id    student ID to edit
     * @param model Spring MVC model to inject editing state
     * @return the student list page with an editable row
     */
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable long id, Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("student", studentRepository.findById(id).orElse(new Student()));
        model.addAttribute("editingId", id);
        return "students";
    }

    /**
     * Handles save/update for a student.
     *
     * @param student student object with updated fields
     * @return redirect to the list page
     */
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }
}
