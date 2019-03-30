CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    type_id INT REFERENCES type(id),
    expired_date DATE,
    price INT,
    amount INT
);

INSERT INTO type (name) VALUES
  ('СЫР'),
  ('МОЛОКО'),
  ('МАСЛО'),
  ('ТВОРОГ'),
  ('МОРОЖЕНОЕ');

INSERT INTO product (name, type_id, expired_date, price, amount) VALUES
  ('Ламбер', 1, '2019-04-12', 550, 20),
  ('Российский', 1, '2019-04-15', 300, 40),
  ('Мороженое Пингвин', 5, '2019-04-29', 120, 15),
  ('Мороженое Василёк', 5, '2019-06-04', 110, 9),
  ('Молоко Фермерское', 2, '2019-04-04', 80, 40);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT p.*, t.name FROM product as p
INNER JOIN type as t on p.type_id = t.id
WHERE t.name = 'СЫР';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT * FROM product
WHERE name like '%Мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product
WHERE expired_date BETWEEN '01.04.2019' and '30.04.2019';

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product 
WHERE price = (SELECT MAX(price) FROM product);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT p.name, p.amount FROM product as p
INNER JOIN type as t on p.type_id = t.id
WHERE t.name = 'СЫР';

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.*, t.name FROM product as p
INNER JOIN type as t on p.type_id = t.id
WHERE t.name IN ('СЫР', 'МОЛОКО');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
SELECT * FROM product
WHERE amount < 10;

-- 8. Вывести все продукты и их тип.
SELECT p.*, t.name FROM product as p
INNER JOIN type as t on p.type_id = t.id;