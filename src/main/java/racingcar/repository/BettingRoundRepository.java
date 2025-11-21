package racingcar.repository;

import java.util.ArrayList;
import java.util.List;
import racingcar.dto.BettingRoundDto;

public class BettingRoundRepository {

    private final List<BettingRoundDto> rounds = new ArrayList<>();

    public void save(BettingRoundDto round) {
        rounds.add(round);
    }
}
