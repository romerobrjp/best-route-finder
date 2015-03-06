package challange.walmart.repository;

import challange.walmart.model.DeliveryPath;
import org.springframework.data.repository.CrudRepository;

/**
 * created by Romero Meireles on 03/03/15.
 */
public interface DeliveryPathRepository extends CrudRepository<DeliveryPath, Long> {
	DeliveryPath findByName(String mapName);
}
