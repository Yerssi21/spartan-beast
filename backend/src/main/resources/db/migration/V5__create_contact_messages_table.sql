CREATE TABLE contact_messages (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(120) NOT NULL,
    email VARCHAR(180) NOT NULL,
    phone VARCHAR(30),

    interest VARCHAR(150),

    message VARCHAR(1500) NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'NEW',

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_contact_messages_status
    ON contact_messages(status);

CREATE INDEX idx_contact_messages_created_at
    ON contact_messages(created_at);