package racingcar.repository;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.BettingRound;

public class BettingRoundRepository {

    private final List<BettingRound> rounds = new ArrayList<>();

    public void save(BettingRound round) {
        rounds.add(round);
    }
}
