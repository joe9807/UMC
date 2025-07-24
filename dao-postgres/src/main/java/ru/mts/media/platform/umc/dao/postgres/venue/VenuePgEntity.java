package ru.mts.media.platform.umc.dao.postgres.venue;

import jakarta.persistence.*;
import lombok.Data;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;
import ru.mts.media.platform.umc.dao.postgres.event.EventPgEntity;
import ru.mts.media.platform.umc.domain.gql.types.Event;

import java.util.List;

@Entity
@Data
@IdClass(FullExternalIdPk.class)
@Table(name = "venue",
        indexes = {
                @Index(name = "idx_venue_referenceId",
                        columnList = "referenceId",
                        unique = true)
        })
public class VenuePgEntity {
    @Id
    @Column(name = "brand")
    private String brand;

    @Id
    @Column(name = "external_id")
    private String externalId;

    @Id
    @Column(name = "provider")
    private String provider;

    private String referenceId;

    private String name;

    @ManyToMany(mappedBy = "venues")
    private List<EventPgEntity> events;
}
