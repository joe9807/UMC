package ru.mts.media.platform.umc.domain.scalar;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

@DgsScalar(name = "DateTime")
public class DateTimeScalar implements Coercing<OffsetDateTime, String> {
    @Override
    public @Nullable String serialize(@NonNull Object dataFetcherResult, @NonNull GraphQLContext graphQLContext, @NonNull Locale locale) throws CoercingSerializeException {
        return ((OffsetDateTime) dataFetcherResult).format(ISO_OFFSET_DATE_TIME);
    }

    @Override
    public @Nullable OffsetDateTime parseValue(@NonNull Object input, @NonNull GraphQLContext graphQLContext, @NonNull Locale locale) throws CoercingParseValueException {
        return OffsetDateTime.parse((String) input, ISO_OFFSET_DATE_TIME);
    }

    @Override
    public @Nullable OffsetDateTime parseLiteral(@NonNull Value<?> input, @NonNull CoercedVariables variables, @NonNull GraphQLContext graphQLContext, @NonNull Locale locale) throws CoercingParseLiteralException {
        return OffsetDateTime.parse(((StringValue) input).getValue(), ISO_OFFSET_DATE_TIME);
    }
}