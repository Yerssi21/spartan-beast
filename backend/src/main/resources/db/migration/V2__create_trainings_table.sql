CREATE TABLE trainings (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(120) NOT NULL,
    slug VARCHAR(140) NOT NULL UNIQUE,

    short_description VARCHAR(250) NOT NULL,
    description TEXT,

    image_url VARCHAR(500),
    icon VARCHAR(80),

    active BOOLEAN NOT NULL DEFAULT TRUE,
    display_order INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_training_display_order
        CHECK (display_order >= 0)
);

CREATE INDEX idx_trainings_active
    ON trainings(active);

CREATE INDEX idx_trainings_display_order
    ON trainings(display_order);