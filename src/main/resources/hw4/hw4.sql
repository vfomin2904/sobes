use hw4;

CREATE TABLE duration(id int PRIMARY KEY AUTO_INCREMENT, value int);

CREATE TABLE film (id int PRIMARY KEY AUTO_INCREMENT,
 name varchar(50) NOT NULL,
 duration_id int NOT NULL,
 FOREIGN KEY (duration_id) REFERENCES duration(id));

CREATE TABLE seans (id int PRIMARY KEY AUTO_INCREMENT,
 film_id int NOT NULL,
 date datetime NOT NULL, 
 price float NOT NULL, 
 FOREIGN KEY (film_id) REFERENCES film(id));
 
CREATE TABLE bilet (id int PRIMARY KEY AUTO_INCREMENT,
 seans_id int NOT NULL,
 number varchar(30) UNIQUE NOT NULL,  
 FOREIGN KEY (seans_id) REFERENCES seans(id));
 
 
INSERT INTO duration(value) VALUES(60);
INSERT INTO duration(value) VALUES(90);
INSERT INTO duration(value) VALUES(120);

INSERT INTO film(name, duration_id) VALUES("film1", 1);
INSERT INTO film(name, duration_id) VALUES("film2", 2);
INSERT INTO film(name, duration_id) VALUES("film3", 3);
INSERT INTO film(name, duration_id) VALUES("film4", 1);
INSERT INTO film(name, duration_id) VALUES("film5", 2);

INSERT INTO seans(film_id, date, price) VALUES(1, "2022_02_01 18:50:00", 2000);
INSERT INTO seans(film_id, date, price) VALUES(2, "2022_02_01 10:20:00", 3000);
INSERT INTO seans(film_id, date, price) VALUES(3, "2022_02_01 11:30:00", 4000);
INSERT INTO seans(film_id, date, price) VALUES(4, "2022_02_01 18:50:00", 2000);
INSERT INTO seans(film_id, date, price) VALUES(5, "2022_02_01 13:50:00", 4400);
INSERT INTO seans(film_id, date, price) VALUES(1, "2022_02_01 16:50:00", 3400);
INSERT INTO seans(film_id, date, price) VALUES(2, "2022_02_01 12:50:00", 5400);
INSERT INTO seans(film_id, date, price) VALUES(3, "2022_02_01 14:50:00", 2300);

INSERT INTO bilet(seans_id, number) VALUES(1, "bil543223");
INSERT INTO bilet(seans_id, number) VALUES(2, "bil543234");
INSERT INTO bilet(seans_id, number) VALUES(3, "bil543343");
INSERT INTO bilet(seans_id, number) VALUES(4, "bil54353");
INSERT INTO bilet(seans_id, number) VALUES(5, "bil543hg3");
INSERT INTO bilet(seans_id, number) VALUES(6, "bil54334333");


SELECT f1.name, s1.date, d1.value, f2.name, s2.date, d2.value FROM seans s1 
inner join film f1 on s1.film_id = f1.id 
inner join duration d1 on f1.duration_id = d1.id 
inner join seans s2 on (s1.id <> s2.id)
inner join film f2 on s2.film_id = f2.id 
inner join duration d2 on f2.duration_id = d2.id 
WHERE TIMESTAMPDIFF(MINUTE, s1.date, s2.date) BETWEEN 1 AND d1.value
ORDER BY f1.name, f2.name;

SELECT f1.name, s1.date, d1.value, f2.name, s2.date, d2.value, TIMESTAMPDIFF(MINUTE, s1.date, s2.date) - d1.value as delta  FROM seans s1 
inner join film f1 on s1.film_id = f1.id 
inner join duration d1 on f1.duration_id = d1.id 
inner join seans s2 on (s1.id <> s2.id)
inner join film f2 on s2.film_id = f2.id 
inner join duration d2 on f2.duration_id = d2.id 
WHERE TIMESTAMPDIFF(MINUTE, s1.date, s2.date) - d1.value >= 30
AND (TIMESTAMPDIFF(MINUTE, s1.date, s2.date) - d1.value) in (SELECT min(TIMESTAMPDIFF(MINUTE, s3.date, s4.date) - d3.value)  FROM seans s3 
inner join film f3 on s3.film_id = f3.id 
inner join duration d3 on f3.duration_id = d3.id 
inner join seans s4 on (s3.id <> s4.id)
inner join film f4 on s4.film_id = f4.id 
inner join duration d4 on f4.duration_id = d4.id 
WHERE s3.id = s1.id AND TIMESTAMPDIFF(MINUTE, s3.date, s4.date) - d3.value >= 30)
ORDER BY delta DESC