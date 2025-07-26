package ru.mts.media.platform.umc.api.gql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.media.platform.umc.Application;
import ru.mts.media.platform.umc.api.gql.venue.VenueDgsMutation;
import ru.mts.media.platform.umc.api.gql.venue.VenueDgsQuery;
import ru.mts.media.platform.umc.dao.postgres.event.EventPgEntity;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgDao;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapper;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgRepository;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.SaveVenueInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueDomainService;
import ru.mts.media.platform.umc.domain.venue.VenueDomainServiceMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "dgs.graphql.schema-locations=classpath*:graphql/schema/**/*.graphqls"
        },
        classes = {Application.class,
                VenueDgsMutation.class,
                VenueDomainService.class,
                VenueDomainServiceMapper.class,
                VenuePgRepository.class,
                VenuePgDao.class,
                VenuePgMapper.class,
                VenueDgsQuery.class,
                EventPgEntity.class,
                VenuePgEntity.class})
public class VenueDgsTest {

    @Autowired
    private VenueDgsMutation venueDgsMutation;

    @Autowired
    private VenueDgsQuery venueDgsQuery;

    @Test
    public void createVenue(){
        Venue venue1 = venueDgsMutation.createVenue(FullExternalId.newBuilder().externalId("234234").brandId("sdfcd").providerId("psdf").build(), SaveVenueInput.newBuilder().name("name1").build());
        Venue venue2 = venueDgsMutation.createVenue(FullExternalId.newBuilder().externalId("1111").brandId("fff").providerId("pszzzdf").build(), SaveVenueInput.newBuilder().name("name2").build());

        List<Venue> venues = venueDgsQuery.findAllVenues();
        assertThat(venues).isNotNull();
        assertThat(venues.size()).isEqualTo(2);

        assertThat(venues.get(0)).isEqualTo(venue1);
        assertThat(venues.get(1)).isEqualTo(venue2);
    }
}
