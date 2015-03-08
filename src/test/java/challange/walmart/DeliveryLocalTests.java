package challange.walmart;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.DeliveryPath;
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
	public void assertDeliveryPointsExistsAfterCreatingLogisticsMap() throws Exception {
		final String POINT_A = "A";
		final String POINT_B = "B";
		final String MAP_NAME = "A-B";
		final Double DISTANCE = 5.0;

		this.logisticsServiceMock.createLogisticsMap(
			POINT_A, POINT_B, MAP_NAME, DISTANCE
		);

		DeliveryPoint deliveryPointA = this.logisticsServiceMock.findDeliveryPointByName(POINT_A);
		Assert.assertNotNull(deliveryPointA);
        Assert.assertEquals(deliveryPointA.getName(), POINT_A);


		DeliveryPoint deliveryPointB = this.logisticsServiceMock.findDeliveryPointByName(POINT_B);
		Assert.assertNotNull(deliveryPointB);
        Assert.assertEquals(deliveryPointB.getName(), POINT_B);
	}

    @Test
    public void assertLogisticsMapCreation() {
        final String MAP_NAME = "AB";
        final String POINT_A = "A";
        final String POINT_B = "B";
        final Double DISTANCE = 10D;

        this.logisticsServiceMock.createLogisticsMap(MAP_NAME, POINT_A, POINT_B, DISTANCE);

        DeliveryPath logisticsMap = this.logisticsServiceMock.findDeliveryPathByName("AB");

        Assert.assertNotNull(logisticsMap);
        Assert.assertEquals(logisticsMap.getName(), "AB");
    }

    @Test
    public void assertBestRoutCalculation() {
        DeliveryPath deliveryPath1 = new DeliveryPath();
        deliveryPath1.setId(1L);
        deliveryPath1.setName("AB");
        deliveryPath1.setDistance(10.0);

        DeliveryPath deliveryPath2 = new DeliveryPath();
        deliveryPath2.setId(2L);
        deliveryPath2.setName("CD");
        deliveryPath2.setDistance(15.0);

        DeliveryPath deliveryPath3 = new DeliveryPath();
        deliveryPath3.setId(3L);
        deliveryPath3.setName("AC");
        deliveryPath3.setDistance(20.0);

        DeliveryPath deliveryPath4 = new DeliveryPath();
        deliveryPath4.setId(4L);
        deliveryPath4.setName("CD");
        deliveryPath4.setDistance(30.0);

        DeliveryPath deliveryPath5 = new DeliveryPath();
        deliveryPath5.setId(5L);
        deliveryPath5.setName("BE");
        deliveryPath5.setDistance(50.0);

        DeliveryPath deliveryPath6 = new DeliveryPath();
        deliveryPath6.setId(6L);
        deliveryPath6.setName("DE");
        deliveryPath6.setDistance(30.0);

        DeliveryPoint originPoint = this.logisticsServiceMock.findDeliveryPointByName("A");
        DeliveryPoint destinyPoint = this.logisticsServiceMock.findDeliveryPointByName("B");
        Double autonomy = 10D;
        Double fuelPrice = 3D;

        BestRoutDTO bestRoutDTO = this.logisticsServiceMock.calculateBestRoute(originPoint, destinyPoint, autonomy, fuelPrice);

        Assert.assertNotNull(bestRoutDTO);
        System.out.println(" >>> Best Route: " + bestRoutDTO);
    }
}
