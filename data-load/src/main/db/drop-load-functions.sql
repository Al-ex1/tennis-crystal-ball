DROP FUNCTION extract_first_name(TEXT) CASCADE;
DROP FUNCTION extract_last_name(TEXT) CASCADE;
DROP FUNCTION find_player(TEXT, DATE) CASCADE;
DROP FUNCTION map_ext_player(INTEGER) CASCADE;
DROP FUNCTION map_ext_tournament(TEXT) CASCADE;
DROP FUNCTION merge_tournament(TEXT, TEXT, TEXT, TEXT, BOOLEAN) CASCADE;
DROP FUNCTION merge_tournament_event(TEXT, SMALLINT, DATE, TEXT, TEXT, TEXT, TEXT, BOOLEAN, TEXT, SMALLINT, INTEGER) CASCADE;
DROP FUNCTION merge_player(INTEGER, TEXT, TEXT, SMALLINT, TEXT) CASCADE;
DROP FUNCTION load_player(INTEGER, TEXT, TEXT, DATE, TEXT, TEXT) CASCADE;
DROP FUNCTION load_ranking(DATE, INTEGER, INTEGER, INTEGER) CASCADE;
DROP FUNCTION load_ranking(DATE, TEXT, INTEGER, INTEGER) CASCADE;
DROP FUNCTION load_match(
	TEXT, SMALLINT, DATE, TEXT, TEXT, TEXT, TEXT, BOOLEAN, TEXT, SMALLINT, INTEGER, SMALLINT, DATE, TEXT, SMALLINT,
	INTEGER, SMALLINT, TEXT, INTEGER, INTEGER, REAL, TEXT, TEXT, SMALLINT, TEXT,
	INTEGER, SMALLINT, TEXT, INTEGER, INTEGER, REAL, TEXT, TEXT, SMALLINT, TEXT,
	TEXT, TEXT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT[], SMALLINT[], SMALLINT[], SMALLINT[], SMALLINT[], SMALLINT[], SMALLINT,
	SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT,
	SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT, SMALLINT
) CASCADE;
DROP FUNCTION find_players(TEXT, DATE) CASCADE;
DROP FUNCTION merge_match_prices(BIGINT, TEXT, REAL, REAL) CASCADE;
DROP FUNCTION load_match_prices(SMALLINT, TEXT, TEXT, DATE, TEXT, TEXT, TEXT, TEXT,
	REAL, REAL, REAL, REAL, REAL, REAL, REAL, REAL) CASCADE;
DROP FUNCTION load_in_progress_event(TEXT, DATE, TEXT, TEXT, TEXT, BOOLEAN, TEXT, SMALLINT);
DROP FUNCTION find_in_progress_event(TEXT);
DROP FUNCTION find_in_progress_match(INTEGER, SMALLINT);
DROP FUNCTION load_in_progress_match(TEXT, SMALLINT, SMALLINT, SMALLINT, DATE, TEXT, BOOLEAN, TEXT, SMALLINT,
	TEXT, TEXT, SMALLINT, TEXT, TEXT, TEXT, SMALLINT, TEXT, SMALLINT, TEXT, TEXT, SMALLINT, SMALLINT[], SMALLINT[], SMALLINT, SMALLINT[], SMALLINT[]);
DROP FUNCTION load_player_in_progress_result(INTEGER, INTEGER, TEXT, TEXT, REAL);
DROP FUNCTION create_player(TEXT, TEXT, DATE, TEXT);
DROP FUNCTION set_tournament_map_properties(TEXT, INTEGER, INTEGER, INTEGER[], JSON) CASCADE;
DROP FUNCTION set_tournament_event_surface(INTEGER, TEXT, TEXT, BOOLEAN) CASCADE;
DROP FUNCTION link_tournament(TEXT, TEXT) CASCADE;