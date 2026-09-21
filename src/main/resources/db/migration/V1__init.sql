CREATE TABLE category (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          type VARCHAR(255) NOT NULL,
                          is_active BOOLEAN NOT NULL
);

CREATE TABLE transactions (
                              id BIGSERIAL PRIMARY KEY,
                              amount NUMERIC(38, 2) NOT NULL,
                              booking_date DATE NOT NULL,
                              created_at DATE NOT NULL,
                              description VARCHAR(200),
                              type VARCHAR(255) NOT NULL,
                              category_id BIGINT NOT NULL,
                              CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO category (name, type, is_active) VALUES ('Gehalt', 'INCOME', true);
INSERT INTO category (name, type, is_active) VALUES ('Lebensmittel', 'EXPENSE', true);
INSERT INTO category (name, type, is_active) VALUES ('Miete', 'EXPENSE', true);
INSERT INTO category (name, type, is_active) VALUES ('Freizeit', 'EXPENSE', true);
INSERT INTO transactions (amount, booking_date, type, description, category_id, created_at)
VALUES (2500.00, '2026-03-01', 'INCOME', 'März Gehalt', 1, CURRENT_DATE);
INSERT INTO transactions (amount, booking_date, type, description, category_id, created_at)
VALUES (55.40, '2026-03-15', 'EXPENSE', 'Wocheneinkauf', 2, CURRENT_DATE);