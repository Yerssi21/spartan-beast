CREATE TABLE coaches (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(120) NOT NULL,
    slug VARCHAR(140) NOT NULL UNIQUE,

    role VARCHAR(120) NOT NULL,
    bio VARCHAR(1000),

    image_url VARCHAR(500),
    instagram_url VARCHAR(500),

    active BOOLEAN NOT NULL DEFAULT TRUE,
    display_order INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_coach_display_order
        CHECK (display_order >= 0)
);


CREATE TABLE coach_specialties (
    id BIGSERIAL PRIMARY KEY,

    coach_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,

    display_order INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT fk_coach_specialties_coach
        FOREIGN KEY (coach_id)
        REFERENCES coaches(id)
        ON DELETE CASCADE,

    CONSTRAINT chk_coach_specialty_display_order
        CHECK (display_order >= 0)
);


CREATE INDEX idx_coaches_active
    ON coaches(active);

CREATE INDEX idx_coaches_display_order
    ON coaches(display_order);

CREATE INDEX idx_coach_specialties_coach_id
    ON coach_specialties(coach_id);