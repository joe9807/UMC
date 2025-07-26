package ru.mts.media.platform.umc.domain.venue;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.SaveVenueInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class VenueDomainService {
    private final ApplicationEventPublisher eventPublisher;
    private final VenueSot sot;
    private final VenueDomainServiceMapper mapper;

    public VenueSave save(FullExternalId id, SaveVenueInput input) {
        VenueSave evt = sot.getVenueById(id)
                .map(applyPatch(input))
                .map(VenueSave::new)
                .orElse(null);

        eventPublisher.publishEvent(evt);

        return evt;
    }

    public VenueSave create(FullExternalId id, SaveVenueInput input) {
        Venue newVenue = Venue.newBuilder().id(UUID.randomUUID().toString()).externalId(FullExternalId.newBuilder().externalId(id.getExternalId()).brandId(id.getBrandId()).providerId(id.getProviderId()).build())
                .name(input.getName()).build();
        newVenue.setEvents(new ArrayList<>());
        VenueSave evt = Optional.of(newVenue)
                .map(applyPatch(input))
                .map(VenueSave::new)
                .orElse(null);

        eventPublisher.publishEvent(evt);

        return evt;
    }

    private Function<Venue, Venue> applyPatch(SaveVenueInput updates) {
        return x -> mapper.patch(x, updates);
    }
}
