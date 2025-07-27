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

import java.time.OffsetDateTime;
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
        Event event1 = eventDgsMutation.createEvent(null, "name123", OffsetDateTime.now().minusDays(10), OffsetDateTime.now());
        Event event2 = eventDgsMutation.createEvent(null, "name456", OffsetDateTime.now().minusDays(2), OffsetDateTime.now().minusDays(1));

        List<Event> events = eventDgsQuery.findAll();
        assertThat(events).isNotNull();
        assertThat(events.size()).isEqualTo(2);

        assertThat(events.get(0)).isEqualTo(event1);
        assertThat(events.get(1)).isEqualTo(event2);
    }
}
