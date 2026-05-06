package ec.edu.espe.springlab.service;

import ec.edu.espe.springlab.domain.Student;
import ec.edu.espe.springlab.dto.StudentCreateRequest;
import ec.edu.espe.springlab.repository.StudentRepository;
import ec.edu.espe.springlab.service.impl.StudentServiceImpl;
import ec.edu.espe.springlab.web.advice.ConflictException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.time.LocalDate;

@DataJpaTest
@Import({StudentServiceImpl.class})
public class StudentServiceTest {
    @Autowired
    private StudentServiceImpl service;

    @Autowired
    private StudentRepository repository;

    @Test
    void shouldNotAllowDuplicatedEmail(){
        Student existing = new Student();
        existing.setFullName("Existing");
        existing.setEmail("duplicated@example.com");
        existing.setBirthDate(LocalDate.of(2001,12,1));
        existing.setActive(true);
        repository.save(existing);

        StudentCreateRequest req = new StudentCreateRequest();
        req.setFullName("New User");
        req.setEmail("duplicated@example.com");
        req.setBirthDate(LocalDate.of(2001,12,1));

        assertThatThrownBy(() -> service.create(req)).isInstanceOf(ConflictException.class);
    }
}
