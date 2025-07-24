package ru.mts.media.platform.umc.dao.postgres.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.mts.media.platform.umc.domain.event.EventSave;

@Component
@RequiredArgsConstructor
class EventPgDao {
    private final EventPgRepository repository;
    private final EventPgMapper mapper;

    @EventListener
    public void handleVenueCreatedEvent(EventSave evt) {
        evt.unwrap()
                .map(mapper::asEntity)
                .ifPresent(repository::save);
    }
}
