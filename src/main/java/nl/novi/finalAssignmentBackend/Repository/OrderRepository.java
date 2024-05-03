package nl.novi.finalAssignmentBackend.Repository;

import nl.novi.finalAssignmentBackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
