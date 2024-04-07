package nl.novi.finalAssignmentBackend.Repository;

import nl.novi.finalAssignmentBackend.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
