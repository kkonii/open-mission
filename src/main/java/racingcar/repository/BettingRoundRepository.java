package racingcar.repository;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.BettingRound;

public class BettingRoundRepository implements BettingRepository {

    private final List<BettingRound> rounds = new ArrayList<>();

    @Override
    public void save(BettingRound round) {
        rounds.add(round);
    }

    @Override
    public List<BettingRound> findAll() {
        return List.copyOf(rounds);
    }
}
