CREATE TABLE venue
(
    brand        VARCHAR(255) NOT NULL,
    provider     VARCHAR(255) NOT NULL,
    external_id  VARCHAR(255) NOT NULL,
    reference_id VARCHAR(255) NOT NULL,
    name         VARCHAR(255) NOT NULL,
    PRIMARY KEY (brand, provider, external_id)
);

CREATE UNIQUE INDEX idx_venue_reference_id ON venue(reference_id);

create table event_venues (event_id varchar(255) not null, brand varchar(255) not null, external_id varchar(255) not null, provider varchar(255) not null);

ALTER TABLE event_venues
    ADD CONSTRAINT fk_event_venues_venue
        FOREIGN KEY (brand, provider, external_id)
            REFERENCES venue(brand, provider, external_id);