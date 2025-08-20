package meet.meeting_automation.repo;

import meet.meeting_automation.model.entity.ZoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZoomUserRepository extends JpaRepository<ZoomUser, Long> {
    Optional<ZoomUser> findByEmail(
            final String email
    );

    boolean existsByEmail(
            final String email
    );
}
