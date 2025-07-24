package ru.mts.media.platform.umc.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.*;
import ru.mts.media.platform.umc.domain.venue.VenueDomainServiceMapper;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventDomainService {
    private final ApplicationEventPublisher eventPublisher;
    private final VenueDomainServiceMapper mapper;

    public EventSave create(String id,
                            List<SaveVenueInput> venues,
                            String name,
                            String startTime,
                            String endTime){
        Venue newVenue = Venue.newBuilder().externalId(FullExternalId.newBuilder().externalId("adfc").brandId("456t").providerId("245r").build())
                .build();
        Event newEvent = Event.newBuilder()
                .id(id)
                .venues(List.of(newVenue))
                .name(name)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        EventSave evt = Optional.of(newEvent)
                .map(EventSave::new)
                .orElse(null);

        eventPublisher.publishEvent(evt);

        return evt;
    }

    private Venue applyPatch(Venue venue, SaveVenueInput updates) {
        return mapper.patch(venue, updates);
    }
}
