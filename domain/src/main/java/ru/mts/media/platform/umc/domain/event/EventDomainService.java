package ru.mts.media.platform.umc.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.RefVenueInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventDomainService {
    private final ApplicationEventPublisher eventPublisher;

    public EventSave create(List<RefVenueInput> venues,
                            String name,
                            String startTime,
                            String endTime){

        List<Venue> listVenues = Optional.ofNullable(venues).orElse(new ArrayList<>()).stream().map(ref-> Venue.newBuilder().externalId(FullExternalId.newBuilder().externalId(ref.getExternalId().getExternalId())
                        .brandId(ref.getExternalId().getBrandId()).providerId(ref.getExternalId().getProviderId()).build())
                .build()).toList();
        Event newEvent = Event.newBuilder()
                .id(UUID.randomUUID().toString())
                .venues(listVenues)
                .name(name)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        EventSave evt = Optional.of(newEvent).map(EventSave::new).orElse(null);
        eventPublisher.publishEvent(evt);
        return evt;
    }
}
