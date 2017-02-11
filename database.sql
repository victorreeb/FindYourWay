--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Drop databases
--





--
-- Drop roles
--

DROP ROLE postgres;


--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md5db12fc1266c2e9d8dd2af2a708eaef3b';






--
-- Database creation
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


\connect postgres

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: destination; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE destination (
    id character varying(255) NOT NULL,
    description character varying(255),
    indices bytea,
    lat double precision NOT NULL,
    lieu character varying(255),
    lng double precision NOT NULL,
    partie_id character varying(255)
);


ALTER TABLE destination OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: partie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE partie (
    id character varying(255) NOT NULL,
    description character varying(255),
    nom character varying(255),
    utilisateur_id character varying(255)
);


ALTER TABLE partie OWNER TO postgres;

--
-- Name: point; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE point (
    id character varying(255) NOT NULL,
    appellation character varying(255),
    lat double precision NOT NULL,
    lng double precision NOT NULL,
    partie_id character varying(255)
);


ALTER TABLE point OWNER TO postgres;

--
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE utilisateur (
    id character varying(255) NOT NULL,
    email character varying(255),
    fullname character varying(255),
    password character varying(255),
    user_type integer NOT NULL
);


ALTER TABLE utilisateur OWNER TO postgres;

--
-- Data for Name: destination; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY destination (id, description, indices, lat, lieu, lng, partie_id) FROM stdin;
ff8081815a2c1720015a2c5908210060	Nancy	\\xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000005770400000005740007696e6469636531740007696e6469636532740007696e6469636533740007696e6469636534740007696e646963653578	-0.120524400000000004	descr1	51.5129189999999966	\N
ff8081815a2c1720015a2c591ae90067	descr1	\\xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000005770400000005740007696e6469636531740007696e6469636532740007696e6469636533740007696e6469636534740007696e646963653578	-0.120524400000000004	Nancy	51.5129189999999966	ff8081815a2c1720015a2c591ae10061
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- Data for Name: partie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY partie (id, description, nom, utilisateur_id) FROM stdin;
ff8081815a2c1720015a2c591ae10061	une partie pour tester	partie de test	\N
\.


--
-- Data for Name: point; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY point (id, appellation, lat, lng, partie_id) FROM stdin;
ff8081815a2c1720015a2c55d9b0005b	test1	-0.135068700000000014	51.5030178000000021	\N
ff8081815a2c1720015a2c567e0b005c	test2	-0.143991000000000008	51.5039202000000031	\N
ff8081815a2c1720015a2c570718005d	test3	-0.204609299999999994	51.5029396999999989	\N
ff8081815a2c1720015a2c578f1c005e	test4	-0.126850999999999992	51.5137004999999988	\N
ff8081815a2c1720015a2c58390a005f	test5	-0.122457499999999997	51.512988	\N
ff8081815a2c1720015a2c591ae30062	test2	-0.143991000000000008	51.5039202000000031	ff8081815a2c1720015a2c591ae10061
ff8081815a2c1720015a2c591ae40063	test3	-0.204609299999999994	51.5029396999999989	ff8081815a2c1720015a2c591ae10061
ff8081815a2c1720015a2c591ae50064	test5	-0.122457499999999997	51.512988	ff8081815a2c1720015a2c591ae10061
ff8081815a2c1720015a2c591ae60065	test1	-0.135068700000000014	51.5030178000000021	ff8081815a2c1720015a2c591ae10061
ff8081815a2c1720015a2c591ae70066	test4	-0.126850999999999992	51.5137004999999988	ff8081815a2c1720015a2c591ae10061
\.


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY utilisateur (id, email, fullname, password, user_type) FROM stdin;
\.


--
-- Name: destination destination_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY destination
    ADD CONSTRAINT destination_pkey PRIMARY KEY (id);


--
-- Name: partie partie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY partie
    ADD CONSTRAINT partie_pkey PRIMARY KEY (id);


--
-- Name: point point_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY point
    ADD CONSTRAINT point_pkey PRIMARY KEY (id);


--
-- Name: utilisateur uk_35ysk0sh9ruwixrld3nc0weut; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT uk_35ysk0sh9ruwixrld3nc0weut UNIQUE (email);


--
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- Name: destination fk72n8jp0yx7pwdk4q1glvgfta8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY destination
    ADD CONSTRAINT fk72n8jp0yx7pwdk4q1glvgfta8 FOREIGN KEY (partie_id) REFERENCES partie(id);


--
-- Name: point fkcoxronxdaqoiv96n24whi448n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY point
    ADD CONSTRAINT fkcoxronxdaqoiv96n24whi448n FOREIGN KEY (partie_id) REFERENCES partie(id);


--
-- Name: partie fkntsiv8uw4j88k2d2bjho9d0qi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY partie
    ADD CONSTRAINT fkntsiv8uw4j88k2d2bjho9d0qi FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id);


--
-- PostgreSQL database dump complete
--

\connect template1

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

