package challange.walmart.service.impl;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.internals.djikstra.DjikstraCustom;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.model.DeliveryPath;
import challange.walmart.repository.DeliveryPathRepository;
import challange.walmart.repository.DeliveryPointRepository;
import challange.walmart.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Romero Meireles on 02/03/15.
 */
@Service
public class LogisticsServiceImpl implements LogisticsService {
	@Autowired
	DeliveryPathRepository deliveryPathRepository;
	@Autowired
	DeliveryPointRepository deliveryPointRepository;
	@Autowired
	DjikstraCustom djikstraCustom;

	@Override
	public void createLogisticsMap(String mapName,
								   String originPointName,
								   String destinyPointName,
								   Double distance) {
		DeliveryPoint originPoint = deliveryPointRepository.findByName(originPointName);
		DeliveryPoint destinyPoint = deliveryPointRepository.findByName(destinyPointName);
		DeliveryPath deliveryPathOrigin = new DeliveryPath(mapName);

		if (destinyPoint == null) {
			destinyPoint = new DeliveryPoint(destinyPointName);
		}
		else {
			if (originPoint != null) {
				originPoint.setPreviousDeliveryPoint(destinyPoint);
			}
		}

		if (originPoint == null) {
			originPoint = new DeliveryPoint(originPointName);

			deliveryPathOrigin.setDistance(distance);
			deliveryPathOrigin.setOriginDeliveryPoint(originPoint);
			deliveryPathOrigin.setDestinyDeliveryPoint(destinyPoint);

			originPoint.getAdjacencies().add(deliveryPathOrigin);

			this.deliveryPathRepository.save(deliveryPathOrigin);
		}
		else {
			if (originPoint.getAdjacencies() != null) {
				boolean alreadyHasDestinyAdjacency = false;

				//using iterator instead foreach for avoiding ConcurrentModificationException
				for (Iterator<DeliveryPath> ppIt  = originPoint.getAdjacencies().iterator(); ppIt.hasNext();) {
					DeliveryPath pp = ppIt.next();
					if (pp.getDestinyDeliveryPoint().getName().equals(destinyPoint)) {
						alreadyHasDestinyAdjacency = true;
						break;
					}
				}

				if (!alreadyHasDestinyAdjacency) {
					deliveryPathOrigin = new DeliveryPath(mapName);
					deliveryPathOrigin.setDistance(distance);
					deliveryPathOrigin.setOriginDeliveryPoint(originPoint);
					deliveryPathOrigin.setDestinyDeliveryPoint(destinyPoint);

					originPoint.getAdjacencies().add(deliveryPathOrigin);

					this.deliveryPathRepository.save(deliveryPathOrigin);
				}
			}
			else {
				deliveryPathOrigin = new DeliveryPath(mapName);
				deliveryPathOrigin.setDistance(distance);
				deliveryPathOrigin.setOriginDeliveryPoint(originPoint);
				deliveryPathOrigin.setDestinyDeliveryPoint(destinyPoint);

				originPoint.getAdjacencies().add(deliveryPathOrigin);

				this.deliveryPathRepository.save(deliveryPathOrigin);
			}
		}

		this.deliveryPointRepository.save(originPoint);
		this.deliveryPointRepository.save(destinyPoint);
	}

	@Override
	public BestRoutDTO calculateBestRoute(DeliveryPoint origin, DeliveryPoint destiny, Double autonomy, Double fuelPrice) {
		BestRoutDTO bestRoutDTO = new BestRoutDTO();

		djikstraCustom.computePathsFromOrigin(origin);
		destiny = deliveryPointRepository.findByName(destiny.getName());

		bestRoutDTO.setBestPath(djikstraCustom.getShortestPathToDestiny(destiny));
		bestRoutDTO.setCost(djikstraCustom.calculateCost(destiny.getMinDistance(), fuelPrice, autonomy));

		return bestRoutDTO;
	}

	@Override
	public DeliveryPoint findDeliveryPointByName(String name) {
		return this.deliveryPointRepository.findByName(name);
	}

	@Override
	public DeliveryPath findDeliveryPathByName(String name) {
		return this.deliveryPathRepository.findByName(name);
	}
}
