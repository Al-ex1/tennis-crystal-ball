package org.strangeforest.tcb.stats.prediction;

import org.strangeforest.tcb.stats.model.prediction.*;

import static com.google.common.base.MoreObjects.*;

public class PredictionResult {

	private final double predictablePct;
	private final double predictionRate;
	private final double profit;
	private final PredictionConfig config;

	public PredictionResult(double predictablePct, double predictionRate, double profit, PredictionConfig config) {
		this.predictablePct = predictablePct;
		this.predictionRate = predictionRate;
		this.profit = profit;
		this.config = config;
	}

	public double getPredictablePct() {
		return predictablePct;
	}

	public double getPredictionRate() {
		return predictionRate;
	}

	public double getPredictablePredictionRate() {
		return predictablePct * predictionRate;
	}

	public double getProfit() {
		return profit;
	}

	public double getPredictionProfit() {
		return predictablePct * profit;
	}

	public PredictionConfig getConfig() {
		return config;
	}


	// Object methods

	@Override public String toString() {
		return toStringHelper(this)
			.add("predictablePct", predictablePct)
			.add("predictionRate", predictionRate)
			.add("profit", profit)
			.add("config", config)
		.toString();
	}
}
