package ru.mts.media.platform.umc.dao.postgres.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapperImpl;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapper;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = VenuePgMapper.class)
public interface EventPgMapper {
    Event asModelNoEvents(EventView eventView);
    Event asModel(EventPgEntity eventPgEntity);
    EventPgEntity asEntity(Event event);
}
