package ru.mts.media.platform.umc.domain.scalar;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.schema.Coercing;

import java.time.OffsetDateTime;

@DgsScalar(name = "DateTime")
public class DateTimeScalar implements Coercing<OffsetDateTime, String> {

}