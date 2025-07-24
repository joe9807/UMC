package ru.mts.media.platform.umc.dao.postgres.event;

import jakarta.persistence.*;
import lombok.Data;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;

@Entity
@Data
@Table(name = "event")
public class EventPgEntity {
    @Id
    private String id;

    @ManyToMany
    @JoinTable(
            name = "event_venues",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "brand", referencedColumnName = "brand"),
                    @JoinColumn(name = "external_id", referencedColumnName = "external_id"),
                    @JoinColumn(name = "provider", referencedColumnName = "provider")
            }
    )
    private List<VenuePgEntity> venues;
    private String name;
    private String startTime;
    private String endTime;
}
