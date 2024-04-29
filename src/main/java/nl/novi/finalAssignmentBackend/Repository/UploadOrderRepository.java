package nl.novi.finalAssignmentBackend.Repository;

import nl.novi.finalAssignmentBackend.entities.UploadOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadOrderRepository extends JpaRepository<UploadOrder, String> {
}
