package com.amex.fruitshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class FruitShopApplicationTests {

	// Normally would separate controller tests and unit/service/repo tests
	FruitOrder od = new FruitOrder(10, 20);

	@Autowired
	private MockMvc mvc;
	FruitRepo fRepo;

    @Test
	public void createFruitOrder() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.post("/fruitshop/createFruitOrder")
				.content(asJsonString(new FruitOrder(4, 2)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.numOfApples").exists());
	}
	
    @Test
    public void getAllFruitOrders() throws Exception {
      mvc.perform( MockMvcRequestBuilders
          .get("/amex/FruitOrders")
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.jsonPath("$.FruitOrders").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$.FruitOrders[*].id").isNotEmpty());
    }

    @Test
    public void getFruitOrderById() throws Exception {
      mvc.perform( MockMvcRequestBuilders
          .get("/amex/{id}", 1)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

	@Test
	void testPrintFruitOrder() {
		assertTrue(od.printFruitOrder().contains("10"));
	}

	@Test
	void testAppleCost() {
		double expectedCost = 3.0;
		assertEquals(expectedCost, od.calcAppleCost(10));
		expectedCost = 6.0;
		assertEquals(expectedCost, od.calcAppleCost(20));
	}

	@Test
	void testOrangeCost() {
		double expectedCost = 1.75;
		assertEquals(expectedCost, od.calcOrangeCost(10));
		expectedCost = 3.5;
		assertEquals(expectedCost, od.calcOrangeCost(20));
		expectedCost = 1.0;
		assertEquals(expectedCost, od.calcOrangeCost(5));
	}

	@Test
	void testCombinedCost() {
		double expectedCost = 6.5;
		assertEquals(expectedCost, od.calcTotalCost());
	}

	@Test
	void testGetById() {
		long testid = 1;
		FruitOrder testOrder = fRepo.getReferenceById(testid);
		assertEquals(6, testOrder.getnumOfApples());
	}

	@Test
	void testGetAll() {
		ArrayList<FruitOrder> FruitOrders = new ArrayList<>();
		fRepo.findAll().forEach(FruitOrders::add);
		assertTrue(FruitOrders.size() > 1);
	}

	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
