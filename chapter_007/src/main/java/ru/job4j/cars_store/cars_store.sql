CREATE TABLE body (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE engine (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE transmission (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE cars (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(50),
    body_id         INT REFERENCES body(id),
    engine_id       INT REFERENCES engine(id),
    transmission_id INT REFERENCES transmission(id)
);

INSERT INTO body (name) VALUES
  ('Sedan'),
  ('Hatchback'),
  ('SUV'),
  ('Pickup'),
  ('Minibus');

INSERT INTO engine (name) VALUES
  ('Gas V8 247 h.p.'),
  ('Gas V6 turbo 280 h.p.'),
  ('Gas V4 100 h.p.'),
  ('Gas V4 turbo 150 h.p.'),
  ('Diesel V8 turbo 250 h.p.'),
  ('Diesel V6 150 h.p.'),
  ('Diesel V6 turbo 190 h.p.');

INSERT INTO transmission (name) VALUES
  ('Manual'),
  ('Automatic'),
  ('Variable');

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
  ('Toyota Land Cruiser', 3, 1, 2),
  ('Nissan Patrol'      , 3, 5, 1),
  ('Subaru Forester'    , 3, 2, 3),
  ('Toyota Hilux'       , 4, 6, 1),
  ('Honda Accord'       , 1, 2, 2);

-- Select cars with details
SELECT c.name as car, b.name as body, e.name as engine, t.name as transmission FROM cars as c
LEFT JOIN body as b on c.body_id = b.id
LEFT JOIN engine as e on c.engine_id = e.id
LEFT JOIN transmission as t on c.transmission_id = t.id
ORDER BY c.name;

-- Select cars with unused bodies
SELECT cars.name, body.name as unused_bodies FROM cars
LEFT JOIN body on cars.body_id != body.id
ORDER BY cars.name;

-- Select cars with unused engines
SELECT cars.name, engine.name as unused_engines FROM cars
LEFT JOIN engine on cars.engine_id != engine.id
ORDER BY cars.name;

-- Select cars with unused transmission
SELECT cars.name, transmission.name as unused_transmissions FROM cars
LEFT JOIN transmission on cars.transmission_id != transmission.id
ORDER BY cars.name;