package org.strangeforest.tcb.stats.controler;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.web.bind.annotation.*;
import org.strangeforest.tcb.stats.model.*;

@RestController
public class AutocompleteController {

	@Autowired private JdbcTemplate jdbcTemplate;

	@RequestMapping("/autocompletePlayer")
	public List<AutocompleteOption> autocompletePlayer(@RequestParam(value="term") String term) {
		return jdbcTemplate.query(
			"SELECT player_id, first_name, last_name FROM atp_players WHERE lower(first_name) || ' ' || lower(last_name) LIKE ? ORDER BY player_id",
			(rs, rowNum) -> {
				String id = rs.getString("player_id");
				String name = rs.getString("first_name") + ' ' + rs.getString("last_name");
				return new AutocompleteOption(id, name, name);
			},
			"%" + term.trim().toLowerCase() + "%"
		);
	}
}
