CREATE TABLE app_users (
    id BIGSERIAL PRIMARY KEY,

    email VARCHAR(180) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,

    role VARCHAR(30) NOT NULL,

    active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_app_user_role
        CHECK (role IN ('ADMIN'))
);

CREATE INDEX idx_app_users_email
    ON app_users(email);

CREATE INDEX idx_app_users_active
    ON app_users(active);