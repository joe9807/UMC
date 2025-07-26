package ru.mts.media.platform.umc.dao.postgres.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.media.platform.umc.dao.postgres.view.EventView;

import java.util.List;

@Repository
public interface EventPgRepository extends CrudRepository<EventPgEntity, String> {
    @Query(value = "SELECT e from EventPgEntity e LEFT JOIN FETCH e.venues")
    List<EventView> findAllEvents();
}
