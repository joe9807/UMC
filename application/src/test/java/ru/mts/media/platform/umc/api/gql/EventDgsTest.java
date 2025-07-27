package ru.mts.media.platform.umc.api.gql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.media.platform.umc.Application;
import ru.mts.media.platform.umc.api.gql.event.EventDgsMutation;
import ru.mts.media.platform.umc.api.gql.event.EventDgsQuery;
import ru.mts.media.platform.umc.dao.postgres.event.EventPgDao;
import ru.mts.media.platform.umc.dao.postgres.event.EventPgEntity;
import ru.mts.media.platform.umc.dao.postgres.event.EventPgMapper;
import ru.mts.media.platform.umc.dao.postgres.event.EventPgRepository;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;
import ru.mts.media.platform.umc.domain.event.EventDomainService;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.venue.VenueDomainServiceMapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "dgs.graphql.schema-locations=classpath*:graphql/schema/**/*.graphqls"
        },
        classes = {Application.class,
                EventDgsMutation.class,
                EventDomainService.class,
                VenueDomainServiceMapper.class,
                EventPgRepository.class,
                EventPgDao.class,
                EventPgMapper.class,
                EventDgsQuery.class,
                EventPgEntity.class,
                VenuePgEntity.class})
public class EventDgsTest {

    @Autowired
    private EventDgsMutation eventDgsMutation;

    @Autowired
    private EventDgsQuery eventDgsQuery;

    @Test
    public void createEvent(){
        Event event1 = eventDgsMutation.createEvent(null, "name123", LocalDateTime.now().minusDays(10).atOffset(ZoneOffset.ofHours(3)).truncatedTo(ChronoUnit.SECONDS)
                , LocalDateTime.now().atOffset(ZoneOffset.ofHours(3)).truncatedTo(ChronoUnit.SECONDS));

        Event event2 = eventDgsMutation.createEvent(null, "name456", LocalDateTime.now().minusDays(10).atOffset(ZoneOffset.ofHours(3)).truncatedTo(ChronoUnit.SECONDS)
                , LocalDateTime.now().minusDays(1).atOffset(ZoneOffset.ofHours(3)).truncatedTo(ChronoUnit.SECONDS));

        List<Event> events = eventDgsQuery.findAll();
        assertThat(events).isNotNull();
        assertThat(events.size()).isEqualTo(2);

        assertThat(events.stream().anyMatch(v->v.equals(event1))).isTrue();
        assertThat(events.stream().anyMatch(v->v.equals(event2))).isTrue();
    }
}
