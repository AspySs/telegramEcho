--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: settings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.settings (
    name character varying(255) NOT NULL,
    parameter bigint NOT NULL
);


ALTER TABLE public.settings OWNER TO postgres;

--
-- Name: user_counter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_counter (
    id integer NOT NULL,
    username character varying(255) NOT NULL,
    counter integer DEFAULT 1,
    lastmsg character varying(255)
);


ALTER TABLE public.user_counter OWNER TO postgres;

--
-- Name: user_counter_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.user_counter ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_counter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.settings (name, parameter) FROM stdin;
delay	10000
\.


--
-- Data for Name: user_counter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_counter (id, username, counter, lastmsg) FROM stdin;
1	wuglys	25	123
\.


--
-- Name: user_counter_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_counter_id_seq', 1, true);


--
-- Name: user_counter name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_counter
    ADD CONSTRAINT name UNIQUE (username);


--
-- Name: settings settings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (name);


--
-- Name: user_counter user_counter_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_counter
    ADD CONSTRAINT user_counter_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

