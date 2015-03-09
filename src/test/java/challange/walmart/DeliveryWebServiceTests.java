package challange.walmart;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.internals.util.TestUtil;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.service.LogisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
@ActiveProfiles(profiles = { "hibernate" })
@Transactional(propagation = Propagation.REQUIRED)
@TransactionConfiguration(defaultRollback = true)
public class DeliveryWebServiceTests {
    final String POINT_1 = "A";
    final String POINT_2 = "B";
    final String POINT_3 = "C";
    final String POINT_4 = "D";
    final String POINT_5 = "E";
    final String MAP_NAME_1 = "AB";
    final String MAP_NAME_2 = "BD";
    final String MAP_NAME_3 = "AC";
    final String MAP_NAME_4 = "CD";
    final String MAP_NAME_5 = "BE";
    final String MAP_NAME_6 = "DE";
    final Double DISTANCE_1 = 10D;
    final Double DISTANCE_2 = 15D;
    final Double DISTANCE_3 = 20D;
    final Double DISTANCE_4 = 30D;
    final Double DISTANCE_5 = 50D;
    final Double DISTANCE_6 = 30D;

	private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    private LogisticsService logisticsServiceMock;
	//private LogisticsService logisticsServiceMock = Mockito.mock(LogisticsServiceImpl.class);

	@Test
	public void calculateBestRoute_ShouldReturnSingleBestRouteDTO() {
        /*this.logisticsServiceMock.createLogisticsMap(MAP_NAME_1, POINT_1, POINT_2, DISTANCE_1);
        this.logisticsServiceMock.createLogisticsMap(MAP_NAME_2, POINT_2, POINT_4, DISTANCE_2);
        this.logisticsServiceMock.createLogisticsMap(MAP_NAME_3, POINT_1, POINT_3, DISTANCE_3);
        this.logisticsServiceMock.createLogisticsMap(MAP_NAME_4, POINT_3, POINT_4, DISTANCE_4);
        this.logisticsServiceMock.createLogisticsMap(MAP_NAME_5, POINT_2, POINT_5, DISTANCE_5);
        this.logisticsServiceMock.createLogisticsMap(MAP_NAME_6, POINT_4, POINT_5, DISTANCE_6);

        DeliveryPoint deliveryPointA = this.logisticsServiceMock.findDeliveryPointByName(POINT_1);
        DeliveryPoint deliveryPointB = this.logisticsServiceMock.findDeliveryPointByName(POINT_2);
        DeliveryPoint deliveryPointD = this.logisticsServiceMock.findDeliveryPointByName(POINT_4);

        System.out.println(" >>> deliveryPointA: " + deliveryPointA);

        Double autonomy = 10D;
		Double fuelPrice = 2.5D;

        BestRoutDTO bestRoutDTO = new BestRoutDTO();
        bestRoutDTO.getBestPath().add(deliveryPointA);
        bestRoutDTO.getBestPath().add(deliveryPointB);
        bestRoutDTO.getBestPath().add(deliveryPointD);
        bestRoutDTO.setCost(6.25D);
        bestRoutDTO.setDistance(25D);

		try {
			when(logisticsServiceMock.calculateBestRoute(deliveryPointA, deliveryPointB, autonomy, fuelPrice))
				.thenReturn(bestRoutDTO);

            doReturn(bestRoutDTO).when(logisticsServiceMock).calculateBestRoute(deliveryPointA, deliveryPointB, autonomy, fuelPrice);

			mockMvc.perform(get("/delivery"))
				.andExpect(status().isOk())
                    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[1].cost", is(6.25)))
                    .andExpect(jsonPath("$[0].distance", is(25)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		verify(logisticsServiceMock, times(1)).calculateBestRoute(deliveryPointA, deliveryPointB, autonomy, fuelPrice);
		verifyNoMoreInteractions(logisticsServiceMock);*/
	}

	@Test
	public void createLogisticsMap_ShouldReturnSingleLogisticsMap() {
        /*doNothing().when(logisticsServiceMock).createLogisticsMap(MAP_NAME_1, POINT_1, POINT_2, DISTANCE_1);

		try {
			mockMvc.perform(post("/delivery")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
            )
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(MAP_NAME_1)))
				.andExpect(jsonPath("$[0].minDistance", is(DISTANCE_1)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		verifyZeroInteractions(logisticsServiceMock);*/
	}
}
