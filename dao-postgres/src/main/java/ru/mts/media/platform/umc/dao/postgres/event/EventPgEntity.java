package ru.mts.media.platform.umc.dao.postgres.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
    private List<VenuePgEntity> venues;
    private String name;
    private String startTime;
    private String endTime;
}
