package com.AppSpring.api;

import com.AppSpring.api.Entity.App;
import com.AppSpring.api.Services.AppService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApiApplicationTests extends AppService {

	@Autowired
	private AppService appService;

	@Test
	public void testCreateWithTwentyMin() {
		App app = new App();
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 21; i++) {
			numbers.add(i);
		}
		app.setEntradas(numbers);

		assertThrows(IllegalArgumentException.class, () -> appService.create(app));
	}
	@Test
	public void testCreateWithFortyMax() {
			App app = new App();
			List<Integer> numbers = new ArrayList<>();
			for (int i = 0; i < 39; i++) {
				numbers.add(i);
			}
			app.setEntradas(numbers);
			assertThrows(IllegalArgumentException.class, () -> appService.create(app));
		}
	@Test
	public void testCalculateMean() {
		List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
		BigDecimal mean = calculateMean(numbers);
		assertEquals(new BigDecimal("30.00"), mean);
	}

	@Test
	public void testCalculateVariance() {
		List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
		BigDecimal mean = new BigDecimal("30.00");
		BigDecimal variance = calculateVariance(numbers, mean);
		assertEquals(new BigDecimal("200.00"), variance);
	}

	@Test
	public void testCalculateStandardDeviation() {
		BigDecimal variance = new BigDecimal("200.00");
		BigDecimal stdDeviation = calculateStandardDeviation(variance);
		assertEquals(new BigDecimal("14.14"), stdDeviation);
	}


}
