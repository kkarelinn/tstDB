-- Table: public.QUESTIONS

-- DROP TABLE IF EXISTS public."QUESTIONS";

CREATE TABLE IF NOT EXISTS questions
(
    id integer NOT NULL generated always as identity,
    question character varying COLLATE pg_catalog."default" NOT NULL,
    answer character varying COLLATE pg_catalog."default"  NOT NULL,
    points integer NOT NULL,
    CONSTRAINT "questions_pkey" PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS questions
    OWNER to postgres;


-- Table: public.USERS

-- DROP TABLE IF EXISTS public."USERS";

CREATE TABLE IF NOT EXISTS users
(
    id integer NOT NULL generated always as identity,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    max_total_points integer NOT NULL,
    CONSTRAINT "users_pkey" PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS users
    OWNER to postgres;;