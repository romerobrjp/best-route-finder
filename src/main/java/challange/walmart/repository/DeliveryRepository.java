package challange.walmart.repository;

import challange.walmart.model.LogisticsMap;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Romero Meireles on 28/02/15.
 */

public interface DeliveryRepository extends CrudRepository<LogisticsMap, Long> {
}
