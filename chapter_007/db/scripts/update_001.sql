CREATE TABLE items (id SERIAL PRIMARY KEY NOT NULL,
                    item_id BIGINT NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    description VARCHAR(200),
                    create_date BIGINT NOT NULL);