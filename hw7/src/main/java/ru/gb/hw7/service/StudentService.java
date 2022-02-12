package ru.gb.hw7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;
import ru.gb.hw7.dao.StudentRepository;
import ru.gb.hw7.entity.Student;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentDao;

    @Autowired
    public StudentService(StudentRepository studentDao) {
        this.studentDao = studentDao;
    }

    public Student getById(Long id) {
        Optional<Student> student = studentDao.findById(id);
        return student.orElse(null);
    }

    public List<Student> getAll() {
        List<Student> students = studentDao.findAll();
        return students;
    }

    public void createStudent(Student student) {
        studentDao.save(student);
    }

    public void updateStudent(Student student) {
        studentDao.save(student);
    }

    public void deleteStudent(Long id) {
        Optional<Student> student = studentDao.findById(id);
        student.ifPresent(studentDao::delete);
    }

}
