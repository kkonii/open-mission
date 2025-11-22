package racingcar.repository;

import java.util.List;
import racingcar.domain.BettingRound;

public interface BettingRepository {

    void save(BettingRound round);

    List<BettingRound> findAll();
}
