CREATE TABLE gallery_items (
    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(150),

    media_type VARCHAR(20) NOT NULL,

    media_url VARCHAR(600) NOT NULL,
    thumbnail_url VARCHAR(600),

    alt_text VARCHAR(250),

    featured BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    display_order INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_gallery_media_type
        CHECK (media_type IN ('IMAGE', 'VIDEO')),

    CONSTRAINT chk_gallery_display_order
        CHECK (display_order >= 0)
);

CREATE INDEX idx_gallery_items_active
    ON gallery_items(active);

CREATE INDEX idx_gallery_items_featured
    ON gallery_items(featured);

CREATE INDEX idx_gallery_items_display_order
    ON gallery_items(display_order);