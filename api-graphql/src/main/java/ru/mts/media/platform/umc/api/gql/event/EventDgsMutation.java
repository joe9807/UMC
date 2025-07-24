package ru.mts.media.platform.umc.api.gql.event;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventDomainService;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveVenueInput;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class EventDgsMutation {
    private final EventDomainService domainService;

    @DgsMutation
    public Event createEvent(@InputArgument String id,
                             @InputArgument List<SaveVenueInput> venues,
                             @InputArgument String name,
                             @InputArgument String startTime,
                             @InputArgument String endTime) {
        return domainService.create(id, venues, name, startTime, endTime).getEntity();
    }
}
