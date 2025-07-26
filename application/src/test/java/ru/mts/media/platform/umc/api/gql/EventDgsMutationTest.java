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
public class EventDgsMutationTest {

    @Autowired
    private EventDgsMutation eventDgsMutation;

    @Autowired
    private EventDgsQuery eventDgsQuery;

    @Test
    public void createEvent(){
        eventDgsMutation.createEvent("123", null, "name123", "24-07-2025 12:14:15", "24-07-2025 12:12:12");
        eventDgsMutation.createEvent("456", null, "name456", "24-07-2025 12:14:15", "21-07-2025 12:12:12");

        List<Event> events = eventDgsQuery.findAll();
        assertThat(events).isNotNull();
        assertThat(events.size()).isEqualTo(2);
    }
}
