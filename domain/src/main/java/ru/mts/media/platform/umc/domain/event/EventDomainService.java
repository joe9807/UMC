package ru.mts.media.platform.umc.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.*;
import ru.mts.media.platform.umc.domain.venue.VenueDomainServiceMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventDomainService {
    private final ApplicationEventPublisher eventPublisher;
    private final VenueDomainServiceMapper mapper;

    public EventSave create(String id,
                            List<RefVenueInput> venues,
                            String name,
                            String startTime,
                            String endTime){

        List<Venue> listVenues = venues.stream().map(ref-> Venue.newBuilder().externalId(FullExternalId.newBuilder().externalId(ref.getExternalId().getExternalId())
                        .brandId(ref.getExternalId().getBrandId()).providerId(ref.getExternalId().getProviderId()).build())
                .build()).toList();
        Event newEvent = Event.newBuilder()
                .id(id)
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
