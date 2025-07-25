package ru.mts.media.platform.umc.dao.postgres.venue;

public interface VenueView {
    String getBrand();
    String getExternalId();
    String getProvider();
    String getReferenceId();
    String getName();
}
