package hw5;

import hw5.dao.StudentRepository;
import hw5.entity.Student;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sf = HibernateSessionFactory.getFactory();
        StudentRepository repository = new StudentRepository(sf);
        Student student = new Student("Alex", 4.f);
        repository.insert(student);

        student.setName("Nick");

        repository.update(student);

        repository.delete(student);

        Student searchedStudent = repository.findById(1);

        System.out.println(searchedStudent.getName());


    }
}
