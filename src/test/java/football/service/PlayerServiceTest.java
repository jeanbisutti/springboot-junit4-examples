package football.service;

import football.FootballApplication;
import football.dto.PlayerWithTeamName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quickperf.spring.junit4.QuickPerfSpringRunner;
import org.quickperf.sql.annotation.ExpectSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(QuickPerfSpringRunner.class)
@SpringBootTest(classes = {FootballApplication.class})
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @ExpectSelect(1)
    @Test
    public void should_find_all_players_with_team_name() {

        List<PlayerWithTeamName> playersWithTeamName = playerService.findPlayersWithTeamName();

        assertThat(playersWithTeamName).hasSize(2);

    }

}