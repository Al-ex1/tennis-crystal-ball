package org.strangeforest.tcb.stats.prediction;

import java.time.*;

import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.strangeforest.tcb.stats.model.prediction.*;
import org.testng.annotations.*;

@ContextConfiguration(classes = PredictionITsConfig.class, initializers = ConfigFileApplicationContextInitializer.class)
@Test(sequential = true)
public class PredictionVerificationIT extends BasePredictionVerificationIT {

	private static final LocalDate FROM_DATE = LocalDate.of(2005, 1, 1);
	private static final LocalDate TO_DATE = LocalDate.now();

	@Test
	public void verifyDefaultPrediction() throws InterruptedException {
		verifyPrediction(PredictionConfig.defaultConfig(), FROM_DATE, TO_DATE);
	}

	@Test
	public void allAreasAllItemsPredictions() throws InterruptedException {
		verifyPrediction(PredictionConfig.EQUAL_WEIGHTS, FROM_DATE, TO_DATE);
	}

	@Test
	public void singleAreaAllItemsPredictions() throws InterruptedException {
		for (PredictionArea area : PredictionArea.values())
			verifyPrediction(PredictionConfig.areaEqualWeights(area), FROM_DATE, TO_DATE);
	}

	@Test
	public void singleItemPredictions() throws InterruptedException {
		for (PredictionArea area : PredictionArea.values()) {
			for (PredictionItem item : area.getItems())
				verifyPrediction(new PredictionConfig(area, 1.0, item, 1.0), FROM_DATE, TO_DATE);
		}
	}
}
