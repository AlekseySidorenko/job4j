          CREATE TABLE rules (
            id          SERIAL PRIMARY KEY,
            name        VARCHAR(50),
            description VARCHAR(200)
          );

          CREATE TABLE roles (
            id          SERIAL PRIMARY KEY,
            name        VARCHAR(50),
            description VARCHAR(200),
            rule_id     INT REFERENCES rules(id)
          );

          CREATE TABLE users (
            id          SERIAL PRIMARY KEY,
            name        VARCHAR(120),
            login       VARCHAR(30),
            password    VARCHAR(30),
            role_id     INT REFERENCES roles(id)
          );

          CREATE TABLE requests_states (
            id          SERIAL PRIMARY KEY,
            name        VARCHAR(50),
            description VARCHAR(200)
          );

          CREATE TABLE requests_categories (
            id          SERIAL PRIMARY KEY,
            name        VARCHAR(50),
            description VARCHAR(200)
          );

          CREATE TABLE requests (
            id            SERIAL PRIMARY KEY,
            name          VARCHAR(100),
            user_id       INT REFERENCES users(id),
            state_id      INT REFERENCES requests_states(id),
            category_id   INT REFERENCES requests_categories
          );

CREATE TABLE requests_files (
  id           SERIAL PRIMARY KEY,
  link_to_file VARCHAR(200),
  request_id   INT REFERENCES requests(id)
);

CREATE TABLE requests_comments (
  id         SERIAL PRIMARY KEY,
  comment    VARCHAR,
  request_id INT REFERENCES requests(id)
);


INSERT INTO rules (name, description) VALUES
  ('rwx', 'full access'),
  ('r', 'read-only access');

INSERT INTO roles (name, description, rule_id) VALUES
  ('root', 'god of the system', 1),
  ('user', 'standard user account', 2);

INSERT INTO users (name, login, password, role_id) VALUES
  ('Fedor Ivanov', 'ivanov_f', 'passw0rd', 1),
  ('Oleg Petrov', 'petrov_o', 'qwerty', 2);

INSERT INTO requests_states (name, description) VALUES
  ('open', 'Request is open'),
  ('closed', 'Request is closed');

INSERT INTO requests_categories (name, description) VALUES
  ('fix', 'Request for fix something'),
  ('access', 'Request for change or add access to system');

INSERT INTO requests (name, user_id, state_id, category_id) VALUES
  ('Fix the logs problem', 2, 1, 1),
  ('Get full access to system', 2, 2, 2);

INSERT INTO requests_files (link_to_file, request_id) VALUES
  ('link1', 1),
  ('link2', 1);

INSERT INTO requests_comments (comment, request_id) VALUES
  ('System log does not work yet', 1),
  ('Access was granted', 2);








