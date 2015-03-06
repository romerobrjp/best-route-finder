package challange.walmart;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.internals.util.TestUtil;
import challange.walmart.model.DeliveryPath;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.service.LogisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Romero Meireles on 05/03/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:WEB-INF/application-context.xml",
	"classpath:WEB-INF/database/database-context.xml" })
@ActiveProfiles(profiles = { "eclipselink" })
@Transactional(propagation = Propagation.REQUIRED)
@TransactionConfiguration(defaultRollback = true)
public class DeliveryWebServiceTest {

	private MockMvc mockMvc;
	@Autowired
	private LogisticsService logisticsServiceMock;

	@Test
	public void calculateBestRoute_ShouldReturnSingleBestRouteDTO() {
		DeliveryPoint originPoint = this.logisticsServiceMock.findDeliveryPointByName("A");
		DeliveryPoint destinyPoint = this.logisticsServiceMock.findDeliveryPointByName("B");
		Double autonomy = 10D;
		Double fuelPrice = 3D;

		try {
			when(logisticsServiceMock.calculateBestRoute(originPoint, destinyPoint, autonomy, fuelPrice))
				.thenReturn(new BestRoutDTO());

			mockMvc.perform(get("/delivery"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("A-B")))
				.andExpect(jsonPath("$[0].minDistance", is("5.0")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("A-C")))
				.andExpect(jsonPath("$[1].title", is("10.0")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		verify(logisticsServiceMock, times(1)).calculateBestRoute(originPoint, destinyPoint, autonomy, fuelPrice);
		verifyNoMoreInteractions(logisticsServiceMock);
	}

	@Test
	public void createLogisticsMap_ShouldReturnSingleLogisticsMap() {
		DeliveryPath deliveryPath1 = new DeliveryPath();
		deliveryPath1.setId(1L);
		deliveryPath1.setName("A-B");
		deliveryPath1.setDistance(5.0);

		DeliveryPath deliveryPath2 = new DeliveryPath();
		deliveryPath1.setId(2L);
		deliveryPath1.setName("A-C");
		deliveryPath1.setDistance(10.0);

		DeliveryPoint point1 = new DeliveryPoint();
		point1.setId(1L);
		point1.setName("A");
		point1.setMinDistance(Double.POSITIVE_INFINITY);
		point1.getAdjacencies().add(deliveryPath1);
		point1.getAdjacencies().add(deliveryPath2);

		DeliveryPoint point2 = new DeliveryPoint();
		point2.setId(2L);
		point2.setName("B");
		point2.setMinDistance(Double.POSITIVE_INFINITY);
		point2.setPreviousDeliveryPoint(point1);

		DeliveryPoint point3 = new DeliveryPoint();
		point3.setId(3L);
		point3.setName("C");
		point3.setMinDistance(Double.POSITIVE_INFINITY);
		point3.setPreviousDeliveryPoint(point1);

		try {
			mockMvc.perform(post("/delivery"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("A-B")))
				.andExpect(jsonPath("$[0].minDistance", is("5.0")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("A-C")))
				.andExpect(jsonPath("$[1].title", is("10.0")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		verify(logisticsServiceMock, times(1)).createLogisticsMap("A-B", "A", "B", 5.0);
		verifyNoMoreInteractions(logisticsServiceMock);
	}


}
