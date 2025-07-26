create table event_venues (event_id varchar(255) not null, brand varchar(255) not null, external_id varchar(255) not null, provider varchar(255) not null);

ALTER TABLE event_venues
    ADD CONSTRAINT fk_event_venues_venue
        FOREIGN KEY (brand, provider, external_id)
            REFERENCES venue(brand, provider, external_id);