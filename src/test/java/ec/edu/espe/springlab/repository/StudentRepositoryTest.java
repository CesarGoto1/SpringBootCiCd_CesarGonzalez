package ec.edu.espe.springlab.repository;

import ec.edu.espe.springlab.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class StudentRepositoryTest{
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldSaveAndFindStudentByEmail(){
        Student student = new Student();
        student.setFullName("Test User");
        student.setEmail("test@example.com");
        student.setBirthDate(LocalDate.of(2001, 12, 01));
        student.setActive(true);
        studentRepository.save(student);

        var result = studentRepository.findByEmail("test@example.com");
        assert(result).isPresent();
        assert(result.get().getFullName()).equals("Test User");
    }
}
