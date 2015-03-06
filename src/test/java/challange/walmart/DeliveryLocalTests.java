package challange.walmart;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.service.LogisticsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Romero Meireles on 06/03/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:WEB-INF/application-context.xml",
	"classpath:WEB-INF/database/database-context.xml" })
@ActiveProfiles(profiles = { "hibernate" })
@Transactional(propagation = Propagation.REQUIRED)
@TransactionConfiguration(defaultRollback = true)
public class DeliveryLocalTests {
	@Autowired
	private LogisticsService logisticsServiceMock;

	@Test
	public void assertLogisticsMapExists() throws Exception {
		final String POINT_A = "A";
		final String POINT_B = "B";
		final String MAP_NAME = "A-B";
		final Double DISTANCE = 5.0;

		this.logisticsServiceMock.createLogisticsMap(
			POINT_A, POINT_B, MAP_NAME, DISTANCE
		);

		DeliveryPoint deliveryPointA = this.logisticsServiceMock.findDeliveryPointByName(POINT_A);
		Assert.assertNotNull(deliveryPointA);


		DeliveryPoint deliveryPointB = this.logisticsServiceMock.findDeliveryPointByName(POINT_B);
		Assert.assertNotNull(deliveryPointB);
	}

	@Test
	public void assetLogisticsMapsExistForFindingBestRoute() throws Exception {
		final String POINT_A = "A";
		final String POINT_B = "B";
		final String MAP_NAME = "A-B";
		final Double DISTANCE = 5.0;
		final Double AUTONOMY = 10D;
		final Double FUEL_PRICE = 3D;

		this.logisticsServiceMock.createLogisticsMap(
			POINT_A, POINT_B, MAP_NAME, DISTANCE
		);

		DeliveryPoint deliveryPointA = this.logisticsServiceMock.findDeliveryPointByName(POINT_A);
		Assert.assertNotNull(deliveryPointA);

		DeliveryPoint deliveryPointB = this.logisticsServiceMock.findDeliveryPointByName(POINT_B);
		Assert.assertNotNull(deliveryPointB);

		BestRoutDTO bestRoutDTO = this.logisticsServiceMock.calculateBestRoute(
			deliveryPointA,
			deliveryPointB,
			AUTONOMY,
			FUEL_PRICE);

		System.out.println(bestRoutDTO);

		Assert.assertNotNull(bestRoutDTO);
	}
}
