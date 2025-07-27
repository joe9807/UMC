package ru.mts.media.platform.umc.domain;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.media.platform.umc.domain.event.EventDomainService;
import ru.mts.media.platform.umc.domain.event.EventSave;
import ru.mts.media.platform.umc.domain.gql.types.RefVenueInput;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
        EventDomainService.class
})
public class EventServiceTest {

    @Autowired
    private EventDomainService eventDomainService;

    @Test
    public void eventsTest(){
        List<RefVenueInput> refVenues = EnhancedRandom.randomListOf(2, RefVenueInput.class);
        String name = EnhancedRandom.random(String.class);
        OffsetDateTime startTime = EnhancedRandom.random(OffsetDateTime.class);
        OffsetDateTime endTime = EnhancedRandom.random(OffsetDateTime.class);

        EventSave eventSave = eventDomainService.create(refVenues, name, startTime, endTime);
        eventSave.unwrap().ifPresent(event->{
            assertThat(event.getName()).isEqualTo(name);
            assertThat(event.getStartTime()).isEqualTo(startTime);
            assertThat(event.getEndTime()).isEqualTo(endTime);

            event.getVenues().forEach(venue-> assertThat(refVenues.stream().map(refVenueInput ->
                    event.getVenues().stream().anyMatch(v ->
                            Objects.equals(v.getExternalId().getExternalId(), refVenueInput.getExternalId().getExternalId()) &&
                            Objects.equals(v.getExternalId().getBrandId(), refVenueInput.getExternalId().getBrandId()) &&
                            Objects.equals(v.getExternalId().getProviderId(), refVenueInput.getExternalId().getProviderId())))
                    .anyMatch(v->v == Boolean.TRUE)).isTrue());
        });
    }

}
