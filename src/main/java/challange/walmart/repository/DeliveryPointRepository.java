package challange.walmart.repository;

import challange.walmart.model.DeliveryPoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * created by Romero Meireles on 03/03/15.
 */
public interface DeliveryPointRepository extends CrudRepository<DeliveryPoint, Long> {

	DeliveryPoint findByName(String pointName);

	List<DeliveryPoint> findAllByName(String pointName);
}
