CREATE TABLE schedules (
    id BIGSERIAL PRIMARY KEY,

    day_of_week VARCHAR(15) NOT NULL UNIQUE,

    opening_time TIME,
    closing_time TIME,

    closed BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    display_order INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_schedule_display_order
        CHECK (display_order >= 0),

    CONSTRAINT chk_schedule_times
        CHECK (
            closed = TRUE
            OR (
                opening_time IS NOT NULL
                AND closing_time IS NOT NULL
                AND closing_time > opening_time
            )
        )
);

CREATE INDEX idx_schedules_active
    ON schedules(active);

CREATE INDEX idx_schedules_display_order
    ON schedules(display_order);