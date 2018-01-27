package org.strangeforest.tcb.stats.model;

import org.junit.*;
import org.strangeforest.tcb.stats.model.core.*;

import static org.assertj.core.api.Assertions.*;

public class RankCategoryTest {

	@Test
	public void rankCategoryIsLoaded() {
		assertThat(RankCategory.values()).isNotEmpty();
		for (RankCategory category : RankCategory.values())
			assertThat(category.getRankTypes()).isNotEmpty();
	}
}
