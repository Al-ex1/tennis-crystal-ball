package org.strangeforest.tcb.stats.prediction;

import java.time.*;
import java.util.function.*;
import java.util.stream.*;

import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.strangeforest.tcb.stats.model.prediction.*;
import org.testng.annotations.*;

import static java.util.Arrays.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.strangeforest.tcb.stats.model.prediction.PredictionArea.*;

@ContextConfiguration(classes = PredictionITsConfig.class, initializers = ConfigFileApplicationContextInitializer.class)
public class PredictionTuningIT extends BasePredictionVerificationIT {

	private static final LocalDate FROM_DATE = LocalDate.of(2005, 1, 1);
	private static final LocalDate TO_DATE = LocalDate.now();
	private static final Function<PredictionResult, Double> METRICS = PredictionResult::getPredictablePredictionRate;
	private static final double MIN_WEIGHT = 0.0;
	private static final double MAX_WEIGHT = 10.0;
	private static final double WEIGHT_STEP = 1.0;


	// Starting from equal weights

	@Test
	public void tunePredictionByArea() throws InterruptedException {
		doTunePredictionByArea(PredictionConfig.EQUAL_WEIGHTS);
	}

	@Test
	public void tunePredictionByItem() throws InterruptedException {
		doTunePredictionByItem(PredictionConfig.EQUAL_WEIGHTS);
	}

	@Test
	public void tunePredictionInRankingArea() throws InterruptedException {
		doTunePredictionInAreaFromPointZero(RANKING);
	}

	@Test
	public void tunePredictionInRecentFormArea() throws InterruptedException {
		doTunePredictionInAreaFromPointZero(RECENT_FORM);
	}

	@Test
	public void tunePredictionInH2HArea() throws InterruptedException {
		doTunePredictionInAreaFromPointZero(H2H);
	}

	@Test
	public void tunePredictionInWinningPctArea() throws InterruptedException {
		doTunePredictionInAreaFromPointZero(WINNING_PCT);
	}


	// Starting from default weights

	@Test
	public void tuneDefaultPredictionByArea() throws InterruptedException {
		doTunePredictionByArea(PredictionConfig.defaultConfig());
	}

	@Test
	public void tuneDefaultPredictionByItem() throws InterruptedException {
		doTunePredictionByItem(PredictionConfig.defaultConfig());
	}

	@Test
	public void tuneDefaultPredictionInRankingArea() throws InterruptedException {
		doTunePredictionInArea(PredictionConfig.defaultConfig(), RANKING);
	}

	@Test
	public void tuneDefaultPredictionInRecentFormArea() throws InterruptedException {
		doTunePredictionInArea(PredictionConfig.defaultConfig(), RECENT_FORM);
	}

	@Test
	public void tuneDefaultPredictionInH2HArea() throws InterruptedException {
		doTunePredictionInArea(PredictionConfig.defaultConfig(), H2H);
	}

	@Test
	public void tuneDefaultPredictionInWinningPctArea() throws InterruptedException {
		doTunePredictionInArea(PredictionConfig.defaultConfig(), WINNING_PCT);
	}


	// Tuning

	private void doTunePredictionByArea(PredictionConfig config) throws InterruptedException {
		tunePrediction(config, asList(PredictionArea.values()), METRICS);
	}

	private void doTunePredictionByItem(PredictionConfig config) throws InterruptedException {
		tunePrediction(config, Stream.of(PredictionArea.values()).flatMap(area -> Stream.of(area.getItems())).collect(toList()), METRICS);
	}

	private void doTunePredictionInAreaFromPointZero(PredictionArea area) throws InterruptedException {
		tunePrediction(PredictionConfig.areaEqualWeights(area), asList(area.getItems()), METRICS);
	}

	private void doTunePredictionInArea(PredictionConfig config, PredictionArea area) throws InterruptedException {
		tunePrediction(config, asList(area.getItems()), METRICS);
	}

	private void tunePrediction(PredictionConfig config, Iterable<Weighted> features, Function<PredictionResult, Double> metrics) throws InterruptedException {
		TuningContext context = new TuningContext(comparing(metrics), MIN_WEIGHT, MAX_WEIGHT, WEIGHT_STEP);
		PredictionResult result = verifyPrediction(config, FROM_DATE, TO_DATE);

		for (context.initialResult(result); context.startStep() != null; context.endStep()) {
			for (Weighted weighted : features) {
				PredictionConfig stepDownConfig = context.stepDown(weighted);
				if (stepDownConfig != null)
					context.nextResult(verifyPrediction(stepDownConfig, FROM_DATE, TO_DATE));
				PredictionConfig stepUpConfig = context.stepUp(weighted);
				if (stepUpConfig != null)
					context.nextResult(verifyPrediction(stepUpConfig, FROM_DATE, TO_DATE));
			}
		}
		context.finish();
	}
}
