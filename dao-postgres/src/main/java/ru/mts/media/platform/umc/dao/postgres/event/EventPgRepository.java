package ru.mts.media.platform.umc.dao.postgres.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EventPgRepository extends CrudRepository<EventPgEntity, String> {
}
