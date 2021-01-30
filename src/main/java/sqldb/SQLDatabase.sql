CREATE SEQUENCE base_sequence START WITH 1;

CREATE TABLE street
(
    id   serial PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE person
(
    id         serial PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL,
    age        SMALLINT,
    street_id  serial REFERENCES street (id)
);

INSERT INTO street(id, name)
VALUES (nextval('base_sequence'), 'проспект Победы'),
       (nextval('base_sequence'), 'Отакара Яроша'),
       (nextval('base_sequence'), 'Сумская'),
       (nextval('base_sequence'), 'Дарвина'),
       (nextval('base_sequence'), 'проспект Правды'),
       (nextval('base_sequence'), 'Мирная'),
       (nextval('base_sequence'), 'Пушкинская'),
       (nextval('base_sequence'), 'Рудика'),
       (nextval('base_sequence'), 'Асхарова'),
       (nextval('base_sequence'), null);

INSERT INTO person(id, first_name, last_name, age, street_id)
VALUES (nextval('base_sequence'), 'Тони', 'Грин', '25', (SELECT id FROM street WHERE name = 'проспект Победы')),
       (nextval('base_sequence'), 'Гавриил', 'Иванович', '12', (SELECT id FROM street WHERE name IS NULL)),
       (nextval('base_sequence'), 'Григорий', 'Иванович', '60', (SELECT id FROM street WHERE name = 'Отакара Яроша')),
       (nextval('base_sequence'), 'Кринж', 'Андреев', '56', (SELECT id FROM street WHERE name = 'Отакара Яроша')),
       (nextval('base_sequence'), 'Гена', 'Букин', '93', (SELECT id FROM street WHERE name = 'Сумская')),
       (nextval('base_sequence'), 'Дмитрий', 'Аркебузов', '1', (SELECT id FROM street WHERE name = 'проспект Правды')),
       (nextval('base_sequence'), 'Александр', 'Иванович', '18', (SELECT id FROM street WHERE name = 'Сумская')),
       (nextval('base_sequence'), 'Василий', 'Маханенко', '43', (SELECT id FROM street WHERE name = 'Сумская')),
       (nextval('base_sequence'), 'Анатолий', 'Кабан', '21', (SELECT id FROM street WHERE name = 'проспект Победы')),
       (nextval('base_sequence'), 'Виктория', 'Арбузова', '21', (SELECT id FROM street WHERE name = 'Дарвина')),
       (nextval('base_sequence'), 'Константин', 'Бабкин', '8', (SELECT id FROM street WHERE name = 'проспект Победы')),
       (nextval('base_sequence'), 'Гарри', 'Поттер', '30', (SELECT id FROM street WHERE name IS NULL)),
       (nextval('base_sequence'), 'Арнольд', 'Арбузов', '17', (SELECT id FROM street WHERE name = 'проспект Правды')),
       (nextval('base_sequence'), 'Лара', 'Крофт', '50', (SELECT id FROM street WHERE name IS NULL)),
       (nextval('base_sequence'), 'Альбус', 'Дамблдор', '23', (SELECT id FROM street WHERE name = 'Мирная')),
       (nextval('base_sequence'), 'Северус', 'Снейп', '42', (SELECT id FROM street WHERE name = 'Асхарова')),
       (nextval('base_sequence'), 'Джордж', 'Уизли', '93', (SELECT id FROM street WHERE name = 'проспект Победы')),
       (nextval('base_sequence'), 'Леонаро', 'Уизли', '1', (SELECT id FROM street WHERE name = 'проспект Правды')),
       (nextval('base_sequence'), 'Александр', 'Иванович', '18', (SELECT id FROM street WHERE name = 'Пушкинская')),
       (nextval('base_sequence'), 'Бред', 'Питт', '43', (SELECT id FROM street WHERE name = 'Пушкинская')),
       (nextval('base_sequence'), 'Джонни', 'Депп', '21', (SELECT id FROM street WHERE name IS NULL)),
       (nextval('base_sequence'), 'Анджелина', 'Джоли', '21', (SELECT id FROM street WHERE name = 'проспект Победы')),
       (nextval('base_sequence'), 'Киану', 'Ривз', '8', (SELECT id FROM street WHERE name = 'Рудика')),
       (nextval('base_sequence'), 'Уилл', 'Смит', '30', (SELECT id FROM street WHERE name = 'Рудика')),
       (nextval('base_sequence'), 'Хоакин', 'Феникс', '17', (SELECT id FROM street WHERE name IS NULL)),
       (nextval('base_sequence'), 'Николас', 'Кейдж', '50', (SELECT id FROM street WHERE name = 'Пушкинская')),
       (nextval('base_sequence'), 'Джеймс', 'Кемерон', '23', (SELECT id FROM street WHERE name IS NULL)),
       (nextval('base_sequence'), 'Арнольд', 'Шварцнегер', '42', (SELECT id FROM street WHERE name IS NULL));

--1.	Вывести общее число жителей
SELECT COUNT(first_name) AS Number_of_citizens
FROM person;
-- 2.	Вывести средний возраст жителей
SELECT ROUND(AVG(age), 2) AS Average_citizens_age
FROM person;
-- 3.	Вывести отсортированный по алфавиту список фамилий без повторений
SELECT DISTINCT last_name AS Sorted_Unic_citizens
FROM person
ORDER BY last_name;
-- 4.	Вывести список фамилий, с указанием количества повторений этих фамилий в общем списке
SELECT DISTINCT last_name, COUNT(last_name)
FROM person
GROUP BY last_name;
-- 5.	Вывести фамилии, которые содержат в середине букву «б»
SELECT last_name AS middle_b
FROM person p
WHERE SUBSTRING(last_name, 2, LENGTH(last_name) - 1) LIKE '%б%';
-- 6.	Вывести список «бомжей»
SELECT first_name, last_name, age, s.name
FROM person p
         INNER JOIN street s ON s.id = p.street_id
WHERE s.name IS NULL
ORDER BY last_name;
-- 7.	Вывести список несовершеннолетних, проживающих на проспекте Правды
SELECT first_name, last_name, age, s.name AS street_name
FROM person p
         INNER JOIN street s ON s.id = p.street_id
WHERE age < 18
  AND s.name = 'проспект Правды'
ORDER BY last_name;
-- 8.	Вывести упорядоченный по алфавиту список всех улиц с указанием, сколько жильцов живёт на улице
SELECT name, COUNT(first_name) AS number_of_citizens
FROM street
         INNER JOIN person p on street.id = p.street_id
WHERE name IS NOT NULL
GROUP BY name
ORDER BY name;
-- 9.	Вывести список улиц, название которых состоит из 6-ти букв
SELECT name AS Street
FROM street
WHERE length(name) = 6
ORDER BY name;
-- 10.	Вывести список улиц с количеством жильцов на них меньше 3.
SELECT name AS Street
FROM street
         INNER JOIN person p on street.id = p.street_id
GROUP BY name
HAVING COUNT(first_name) < 3
ORDER BY name;