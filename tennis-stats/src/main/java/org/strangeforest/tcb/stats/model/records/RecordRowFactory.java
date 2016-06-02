package org.strangeforest.tcb.stats.model.records;

public enum RecordRowFactory {

	INTEGER {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new IntegerRecordRow(rank, playerId, name, countryId, active);
		}
	},
	SEASON_INTEGER {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new SeasonIntegerRecordRow(rank, playerId, name, countryId, active);
		}
	},
	TOURNAMENT_INTEGER {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new TournamentIntegerRecordRow(rank, playerId, name, countryId, active);
		}
	},
	TOURNAMENT_EVENT_INTEGER {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new TournamentEventIntegerRecordRow(rank, playerId, name, countryId, active);
		}
	},
	WINNING_PCT {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new WinningPctRecordRow(rank, playerId, name, countryId, active);
		}
	},
	LOSING_PCT {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new LosingPctRecordRow(rank, playerId, name, countryId, active);
		}
	},
	SEASON_WINNING_PCT {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new SeasonWinningPctRecordRow(rank, playerId, name, countryId, active);
		}
	},
	SEASON_LOSING_PCT {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new SeasonLosingPctRecordRow(rank, playerId, name, countryId, active);
		}
	},
	TOURNAMENT_WINNING_PCT {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new TournamentWinningPctRecordRow(rank, playerId, name, countryId, active);
		}
	},
	TOURNAMENT_LOSING_PCT {
		@Override public RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active) {
			return new TournamentLosingPctRecordRow(rank, playerId, name, countryId, active);
		}
	};

	public abstract RecordRow createRow(int rank, int playerId, String name, String countryId, Boolean active);
}