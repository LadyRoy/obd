--liquibase formatted sql
--changeSet s0qva:2

ALTER TABLE "Выдача_книга"
    ADD COLUMN "Должник" VARCHAR(1);
