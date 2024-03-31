package nl.novi.finalAssignmentBackend.Repository;

import nl.novi.finalAssignmentBackend.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
