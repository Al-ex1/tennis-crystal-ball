package org.strangeforest.tcb.stats.model.records.categories;

import org.strangeforest.tcb.stats.model.records.*;

import static java.util.Arrays.*;
import static org.strangeforest.tcb.stats.model.records.categories.HeadToHeadCategory.PctRecordType.*;
import static org.strangeforest.tcb.stats.model.records.categories.HeadToHeadCategory.RecordType.*;
import static org.strangeforest.tcb.stats.model.records.RecordRowFactory.*;

public class HeadToHeadCategory extends RecordCategory {

	enum RecordType {
		PLAYED("Played", HTH_TOTAL),
		WON("Won", "h2h_won"),
		DRAW("Draw", "h2h_draw"),
		LOST("Lost", "h2h_lost");

		final String name;
		final String column;

		RecordType(String name, String column) {
			this.name = name;
			this.column = column;
		}
	}

	enum PctRecordType {
		WINNING("Winning", "(h2h_won + 0.5 * h2h_draw) / (" + HTH_TOTAL + ")", "wonLostPct", WINNING_W_DRAW_PCT, WON_COLUMN, LOST_COLUMN),
		LOSING("Losing", "(h2h_lost + 0.5 * h2h_draw) / (" + HTH_TOTAL + ")", "lostWonPct", LOSING_W_DRAW_PCT, LOST_COLUMN, WON_COLUMN);

		final String name;
		final String expression;
		final String pctAttr;
		final RecordRowFactory rowFactory;
		final RecordColumn value1RecordColumn;
		final RecordColumn value2RecordColumn;

		PctRecordType(String name, String expression, String pctAttr, RecordRowFactory rowFactory, RecordColumn value1RecordColumn, RecordColumn value2RecordColumn) {
			this.name = name;
			this.pctAttr = pctAttr;
			this.expression = expression;
			this.rowFactory = rowFactory;
			this.value1RecordColumn = value1RecordColumn;
			this.value2RecordColumn = value2RecordColumn;
		}
	}

	private static final String HTH_TOTAL = "h2h_won + h2h_draw + h2h_lost";

	private static final String H2H_WIDTH =      "100";
	private static final String H2H_SMALL_WIDTH = "60";
	private static final String PCT_WIDTH =      "100";

	private static final RecordColumn WON_COLUMN = new RecordColumn("won", "numeric", null, H2H_SMALL_WIDTH, "right", "Won");
	private static final RecordColumn LOST_COLUMN = new RecordColumn("lost", "numeric", null, H2H_SMALL_WIDTH, "right", "Lost");

	public HeadToHeadCategory(boolean infamous) {
		super("Head-to-Head (minimum 3 matches in H2H series, minimum 5 H2H series)");
		if (!infamous) {
			register(mostH2HSeries(PLAYED));
			register(mostH2HSeries(WON));
			register(mostH2HSeries(DRAW));
			register(greatestH2HSeriesPct(WINNING));
		}
		else {
			register(mostH2HSeries(LOST));
			register(greatestH2HSeriesPct(LOSING));
		}
	}

	private static Record mostH2HSeries(RecordType type) {
		return new Record(
			"H2HSeries" + type.name, "Most Head-to-Head Series " + type.name,
			/* language=SQL */
			"SELECT player_id, " + type.column + " AS value\n" +
			"FROM player_h2h\n" +
			"WHERE " + HTH_TOTAL + " >= 5 AND " + type.column + " > 0",
			"r.value", "r.value DESC", "r.value DESC", RecordRowFactory.INTEGER,
			asList(new RecordColumn("value", "numeric", null, H2H_WIDTH, "right", "H2H Series " + type.name))
		);
	}

	private static Record greatestH2HSeriesPct(PctRecordType type) {
		return new Record(
			"H2HSeries" + type.name + "Pct", "Greatest Head-to-Head Series " + type.name + " Pct.",
			/* language=SQL */
			"SELECT player_id, " + type.expression + " AS pct, h2h_won AS won, h2h_draw AS draw, h2h_lost AS lost\n" +
			"FROM player_h2h\n" +
			"WHERE " + HTH_TOTAL + " >= 5",
			"r.pct, r.won, r.draw, r.lost", "r.pct DESC", "r.pct DESC, r.won + r.draw + r.lost DESC", type.rowFactory,
			asList(
				new RecordColumn(type.pctAttr, null, null, PCT_WIDTH, "right", "H2H " + type.name + " Pct."),
				type.value1RecordColumn,
				new RecordColumn("draw", "numeric", null, H2H_SMALL_WIDTH, "right", "Draw"),
				type.value2RecordColumn,
				new RecordColumn("played", "numeric", null, H2H_SMALL_WIDTH, "right", "Played")
			)
		);
	}
}