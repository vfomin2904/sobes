package hw5.dao;

import hw5.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

public class StudentRepository implements Repository<Student> {

    private final Session session;

    public StudentRepository(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    public void update(Student entity) {
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
    }

    @Override
    public void insert(Student entity) {
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Student entity) {
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    @Override
    public Student findById(int id) {
        return session.get(Student.class, id);
    }
}
