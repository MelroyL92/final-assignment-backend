package nl.novi.finalAssignmentBackend.Repository;

import nl.novi.finalAssignmentBackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

//    List<Order> findOrderByUser(String username);
}
