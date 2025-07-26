package ru.mts.media.platform.umc.dao.postgres.view;

import java.util.List;

public interface VenueView {
    String getBrand();
    String getExternalId();
    String getProvider();
    String getReferenceId();
    String getName();
    List<EventViewNoVenues> getEvents();
}
