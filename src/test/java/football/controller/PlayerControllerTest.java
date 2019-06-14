package football.controller;

import football.FootballApplication;
import football.dto.PlayerWithTeamName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quickperf.jvm.allocation.AllocationUnit;
import org.quickperf.jvm.annotations.HeapSize;
import org.quickperf.spring.junit4.QuickPerfSpringRunner;
import org.quickperf.sql.annotation.ExpectSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(QuickPerfSpringRunner.class)
@SpringBootTest(classes = {FootballApplication.class}
              , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
               )
public class PlayerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @ExpectSelect(1)
    @HeapSize(value = 50, unit = AllocationUnit.MEGA_BYTE)
    @Test
    public void should_find_all_players() {

        // GIVEN
        String getUrl = "http://localhost:" + port + "/players";

        // WHEN
        ResponseEntity<List> playersResponseEntity = restTemplate.getForEntity(getUrl, List.class);
        List<PlayerWithTeamName> players = playersResponseEntity.getBody();

        // THEN
        assertThat(playersResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(players).hasSize(2);

    }

}