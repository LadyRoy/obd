--liquibase formatted sql
--changeSet s0qva:1

CREATE TABLE "Автор"
(
    "Код_автор"         SERIAL PRIMARY KEY,
    "Фамилия_автор"     VARCHAR(128),
    "Имя_автор"         VARCHAR(128),
    "Дата_рождения"     DATE,
    "Краткая_биография" VARCHAR(1024)
);

CREATE TABLE "Издательство"
(
    "Код_издательство" SERIAL PRIMARY KEY,
    "Наименование"     VARCHAR(128),
    "Местоположение"   VARCHAR(256),
    "Кол-во_выпущено"  INTEGER
);

CREATE TABLE "Книга"
(
    "Код_книга"        SERIAL PRIMARY KEY,
    "Название"         VARCHAR(256),
    "Код_издательства" INTEGER REFERENCES "Издательство" ("Код_издательство") ON DELETE CASCADE ON UPDATE CASCADE,
    "Дата_выпуска"     DATE,
    "Дата_регистрации" DATE
);

CREATE TABLE "Автор_книга"
(
    "Код_автор_книга" SERIAL PRIMARY KEY,
    "Код_автор"       INTEGER REFERENCES "Автор" ("Код_автор") ON DELETE CASCADE ON UPDATE CASCADE,
    "Код_книга"       INTEGER REFERENCES "Книга" ("Код_книга") ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE ("Код_автор", "Код_книга")
);

CREATE TABLE "Место_хранения"
(
    "Код_место_хранения"        SERIAL PRIMARY KEY,
    "Местоположение_библиотека" VARCHAR(256)
);

CREATE TABLE "Читатель"
(
    "Номер_билет"      SERIAL PRIMARY KEY,
    "Имя_читатель"     VARCHAR(128),
    "Фамилия_читатель" VARCHAR(128),
    "Номер_телефона"   VARCHAR(11),
    "Адрес"            VARCHAR(256),
    "Почта"            VARCHAR(128)
);

CREATE TABLE "Экземпляр"
(
    "Код_раздел_книга"   SERIAL PRIMARY KEY,
    "Код_книга"          INTEGER REFERENCES "Книга" ("Код_книга") ON DELETE CASCADE ON UPDATE CASCADE,
    "Код_место_хранение" INTEGER REFERENCES "Место_хранения" ("Код_место_хранения") ON DELETE CASCADE ON UPDATE CASCADE,
    "Раздел"             VARCHAR(128),
    UNIQUE ("Код_книга", "Код_место_хранение")
);

CREATE TABLE "Выдача_книга"
(
    "Код_выдача_книга" SERIAL PRIMARY KEY,
    "Код_раздел_книга" INTEGER REFERENCES "Экземпляр" ("Код_раздел_книга") ON DELETE CASCADE ON UPDATE CASCADE,
    "Дата_выдача"      DATE,
    "Дата_возврат"     DATE,
    "Номер_билет"      INTEGER REFERENCES "Читатель" ("Номер_билет") ON DELETE CASCADE ON UPDATE CASCADE
);
