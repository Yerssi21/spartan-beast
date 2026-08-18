CREATE TABLE plans (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(100) NOT NULL,
    slug VARCHAR(120) NOT NULL UNIQUE,
    description VARCHAR(500),

    price NUMERIC(12, 2) NOT NULL,

    duration VARCHAR(30) NOT NULL,

    highlighted BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    display_order INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_plan_price
        CHECK (price >= 0),

    CONSTRAINT chk_plan_display_order
        CHECK (display_order >= 0)
);


CREATE TABLE plan_features (
    id BIGSERIAL PRIMARY KEY,

    plan_id BIGINT NOT NULL,

    description VARCHAR(255) NOT NULL,

    display_order INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT fk_plan_features_plan
        FOREIGN KEY (plan_id)
        REFERENCES plans(id)
        ON DELETE CASCADE,

    CONSTRAINT chk_feature_display_order
        CHECK (display_order >= 0)
);


CREATE INDEX idx_plans_active
    ON plans(active);

CREATE INDEX idx_plans_display_order
    ON plans(display_order);

CREATE INDEX idx_plan_features_plan_id
    ON plan_features(plan_id);