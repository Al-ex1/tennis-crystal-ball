package org.strangeforest.tcb.dataload

import static org.strangeforest.tcb.dataload.TennisAbstractTournamentFetcher.*

loadTournaments(new SqlPool())

static loadTournaments(SqlPool sqlPool) {
	def matchLoader = new MatchLoader(sqlPool)
	ATPTennisLoader.loadAdditionalTournament(sqlPool, 'classpath:/tournaments/2016-davis-cup.xml')
	matchLoader.load(fetchTournament(2016, 'Quito', 7161))
	matchLoader.load(fetchTournament(2016, 'Montpellier', 375))
	matchLoader.load(fetchTournament(2016, 'Sofia', 364))
	matchLoader.load(fetchTournament(2016, 'Buenos_Aires', 506))
	matchLoader.load(fetchTournament(2016, 'Rotterdam', 407))
	matchLoader.load(fetchTournament(2016, 'Memphis', 402))
	matchLoader.load(fetchTournament(2016, 'Rio_de_Janeiro', 6932))
	matchLoader.load(fetchTournament(2016, 'Marseille', 496))
	matchLoader.load(fetchTournament(2016, 'Delray_Beach', 499))
	matchLoader.load(fetchTournament(2016, 'Dubai', 495))
	matchLoader.load(fetchTournament(2016, 'Acapulco', 807))
	matchLoader.load(fetchTournament(2016, 'Sao_Paulo', 533))
	matchLoader.load(fetchTournament(2016, 'Indian_Wells_Masters', 404))
	matchLoader.load(fetchTournament(2016, 'Miami_Masters', 403))
	matchLoader.load(fetchTournament(2016, 'Monte_Carlo_Masters', 410))
	matchLoader.load(fetchTournament(2016, 'Barcelona', 425))
	matchLoader.load(fetchTournament(2016, 'Bucharest', 773))
	matchLoader.load(fetchTournament(2016, 'Munich', 308))
	matchLoader.load(fetchTournament(2016, 'Estoril', 7290))
	matchLoader.load(fetchTournament(2016, 'Istanbul', 7163))
	matchLoader.load(fetchTournament(2016, 'Madrid_Masters', 1536))
	matchLoader.load(fetchTournament(2016, 'Rome_Masters', 416))
	matchLoader.load(fetchTournament(2016, 'Nice', 6120))
	matchLoader.load(fetchTournament(2016, 'Geneva', 322))
	matchLoader.load(fetchTournament(2016, 'Roland_Garros', 520))
	matchLoader.load(fetchTournament(2016, 'Stuttgart', 321))
	matchLoader.load(fetchTournament(2016, 's-Hertogenbosch', 440))
	matchLoader.load(fetchTournament(2016, 'London', 311))
	matchLoader.load(fetchTournament(2016, 'Halle', 500))
	matchLoader.load(fetchTournament(2016, 'Nottingham', 741))
	matchLoader.load(fetchTournament(2016, 'Wimbledon', 540))
	matchLoader.load(fetchTournament(2016, 'Newport', 315))
	matchLoader.load(fetchTournament(2016, 'Hamburg', 414))
	matchLoader.load(fetchTournament(2016, 'Bastad', 316))
	matchLoader.load(fetchTournament(2016, 'Umag', 439))
	matchLoader.load(fetchTournament(2016, 'Kitzbuhel', 319))
	matchLoader.load(fetchTournament(2016, 'Gstaad', 314))
	matchLoader.load(fetchTournament(2016, 'Washington', 418))
	matchLoader.load(fetchTournament(2016, 'Canada_Masters', 421))
	matchLoader.load(fetchTournament(2016, 'Atlanta', 6116))
	matchLoader.load(fetchTournament(2016, 'Olympics', 'O'))
	matchLoader.load(fetchTournament(2016, 'Los_Cabos', 7480))
	matchLoader.load(fetchTournament(2016, 'Cincinnati_Masters', 422))
	matchLoader.load(fetchTournament(2016, 'Winston-Salem', 6242))
	matchLoader.load(fetchTournament(2016, 'Us_Open', 560))
	matchLoader.load(fetchTournament(2016, 'Metz', 341))
	matchLoader.load(fetchTournament(2016, 'St.Petersburg', 568))
	matchLoader.load(fetchTournament(2016, 'Chengdu', 7581))
	matchLoader.load(fetchTournament(2016, 'Shenzhen', 6967))
	matchLoader.load(fetchTournament(2016, 'Beijing', 747))
	matchLoader.load(fetchTournament(2016, 'Tokyo', 329))
	matchLoader.load(fetchTournament(2016, 'Shanghai', 5014))
	matchLoader.load(fetchTournament(2016, 'Stockholm', 429))
	matchLoader.load(fetchTournament(2016, 'Antwerp', 7485))
	matchLoader.load(fetchTournament(2016, 'Moscow', 438))
	matchLoader.load(fetchTournament(2016, 'Basel', 328))
	matchLoader.load(fetchTournament(2016, 'Vienna', 337))
	sqlPool.withSql {sql ->
		def atpWorldTourMatchLoader = new ATPWorldTourTournamentLoader(sql)
//		atpWorldTourMatchLoader.loadTournament(2016, 'shanghai', 5014)
	}
}