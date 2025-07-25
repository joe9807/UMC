package ru.mts.media.platform.umc.dao.postgres.view;

import java.util.List;

public interface EventView {
    String getId();
    List<VenueViewNoEvents> getVenues();
    String getName();
    String getStartTime();
    String getEndTime();
}
