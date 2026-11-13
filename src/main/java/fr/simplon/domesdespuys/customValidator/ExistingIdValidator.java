package fr.simplon.domesdespuys.customValidator;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

@RequiredArgsConstructor
public class ExistingIdValidator implements ConstraintValidator<iExistingId, Long> {

    private final EntityManager entityManager;
    private JpaRepository<?, Long> repository;

    @Override
    public void initialize(iExistingId annotation) {
        JpaRepositoryFactory factory = new JpaRepositoryFactory(entityManager);
        repository = factory.getRepository(annotation.repository());
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext validatorContext) {
        if (id == null) {
            return true;
        }
        return repository.existsById(id);
    }
}