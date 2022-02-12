package ru.gb.hw7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.hw7.entity.Student;
import ru.gb.hw7.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public String getStudent(Model model, @PathVariable Long id) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "student";
    }

    @GetMapping("/")
    public String getStudentsList(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "student_list";
    }

    @GetMapping("/create")
    public String createStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student_form";
    }

    @GetMapping("/update/{id}")
    public String updateStudentForm(Model model, @PathVariable Long id) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "student_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student/";
    }

    @PostMapping("/")
    public String createStudent(Student student) {
        studentService.createStudent(student);
        return "redirect:/student/";
    }

    @PutMapping("/")
    public String updateStudent(Student student) {
        studentService.updateStudent(student);
        return "redirect:/student/";
    }
}
