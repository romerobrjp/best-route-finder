package challange.walmart.repository;

import challange.walmart.model.DeliveryPoint;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by root on 03/03/15.
 */
public interface DeliveryPointRepository extends CrudRepository<DeliveryPoint, Long> {

	DeliveryPoint findByName(String pointName);
}
