package ru.mts.media.platform.umc.dao.postgres.event;

import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;
import ru.mts.media.platform.umc.dao.postgres.venue.VenueView;

import java.util.List;

public interface EventView {
    String getId();
    List<VenueView> getVenues();
    String getName();
    String getStartTime();
    String getEndTime();
}
