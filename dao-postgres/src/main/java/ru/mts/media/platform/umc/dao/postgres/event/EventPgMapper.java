package ru.mts.media.platform.umc.dao.postgres.event;

import org.mapstruct.Mapper;
import ru.mts.media.platform.umc.dao.postgres.view.EventView;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = VenuePgMapper.class)
public interface EventPgMapper {
    Event asModel(EventView eventView);
    Event asModel(EventPgEntity eventPgEntity);
    EventPgEntity asEntity(Event event);
}
