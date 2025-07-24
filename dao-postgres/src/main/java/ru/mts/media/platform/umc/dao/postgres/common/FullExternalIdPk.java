package ru.mts.media.platform.umc.dao.postgres.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullExternalIdPk implements Serializable {
    private String brand;
    private String externalId;
    private String provider;
}
