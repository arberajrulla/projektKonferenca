--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-09-24 20:19:39

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

--
-- TOC entry 2875 (class 1262 OID 16557)
-- Name: conference_review; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE conference_review WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE conference_review OWNER TO postgres;

\connect conference_review

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
-- TOC entry 203 (class 1259 OID 16649)
-- Name: artikull; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artikull (
    artikull_id integer NOT NULL,
    titulli character varying(255) NOT NULL,
    abstrakti character varying(255),
    dokumenti_elektronik character varying(255),
    kontakt character varying
);


ALTER TABLE public.artikull OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16647)
-- Name: artikull_artikull_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.artikull_artikull_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.artikull_artikull_id_seq OWNER TO postgres;

--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 202
-- Name: artikull_artikull_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.artikull_artikull_id_seq OWNED BY public.artikull.artikull_id;


--
-- TOC entry 206 (class 1259 OID 16753)
-- Name: autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autor (
    email_id character varying(255) NOT NULL,
    emri character varying(50) NOT NULL,
    mbiemri character varying(50) NOT NULL,
    artikull_id integer NOT NULL
);


ALTER TABLE public.autor OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 25036)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 24978)
-- Name: login; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.login (
    emri character varying(50) NOT NULL,
    mbiemri character varying(50) NOT NULL,
    username character varying(50) NOT NULL,
    email character varying(80) NOT NULL,
    password character varying(1000) NOT NULL,
    kategoria character varying NOT NULL,
    nrcel character varying(30) NOT NULL,
    salt bytea
);


ALTER TABLE public.login OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 25071)
-- Name: sequence_vleresimi_id; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_vleresimi_id
    START WITH 10
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_vleresimi_id OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16658)
-- Name: shqyrtues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shqyrtues (
    id_email character varying(255) NOT NULL,
    emri character varying(70) NOT NULL,
    mbiemri character varying(70) NOT NULL,
    institucioni character varying(255) NOT NULL
);


ALTER TABLE public.shqyrtues OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16681)
-- Name: shqyrtues_artikull; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shqyrtues_artikull (
    shqrtid character varying(255) NOT NULL,
    arid integer NOT NULL,
    merita_teknike integer NOT NULL,
    kuptueshmeria integer NOT NULL,
    origjinaliteti integer NOT NULL,
    perkatesi_konference integer NOT NULL,
    rekomandime character varying(255),
    vleresim_id integer NOT NULL
);


ALTER TABLE public.shqyrtues_artikull OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 25010)
-- Name: shqyrtues_tema; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shqyrtues_tema (
    id integer NOT NULL,
    shqyrtues_id character varying(255) NOT NULL,
    temat character varying(255) NOT NULL
);


ALTER TABLE public.shqyrtues_tema OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 25018)
-- Name: shqyrtues_tema_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.shqyrtues_tema ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.shqyrtues_tema_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2718 (class 2604 OID 16652)
-- Name: artikull artikull_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artikull ALTER COLUMN artikull_id SET DEFAULT nextval('public.artikull_artikull_id_seq'::regclass);


--
-- TOC entry 2861 (class 0 OID 16649)
-- Dependencies: 203
-- Data for Name: artikull; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.artikull (artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt) VALUES (1, 'Titulli 1', 'Abstr 1', 'dok elekt 1', '5554422');
INSERT INTO public.artikull (artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt) VALUES (6, 'artikull', 'ytwef', 'wdcwfc', '4444');
INSERT INTO public.artikull (artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt) VALUES (3, 'tttttt', 'tttttt', 'ttttttt', 'ttttttt');
INSERT INTO public.artikull (artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt) VALUES (7, 'twretet', 'erwtwert', 'rtewrtwe', 'ertwertt');
INSERT INTO public.artikull (artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt) VALUES (8, 'wertwwertert', 'wrjutyyu', 'trjyjtyht', '5464565464');
INSERT INTO public.artikull (artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt) VALUES (9, '35463465', '4563456', '3564564', '5463456456');
INSERT INTO public.artikull (artikull_id, titulli, abstrakti, dokumenti_elektronik, kontakt) VALUES (11, 'dfsgfgdfsg', 'dsfgsdfg', 'dsfgsfdgdf', 'sfdgfdsgdfgsfgdsfgdfgg');


--
-- TOC entry 2864 (class 0 OID 16753)
-- Dependencies: 206
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.autor (email_id, emri, mbiemri, artikull_id) VALUES ('DASDFASDF', 'ppppppp', 'asDADSFSF', 9);
INSERT INTO public.autor (email_id, emri, mbiemri, artikull_id) VALUES ('rrrrrr', 'ppppppp', 'oooooo', 1);


--
-- TOC entry 2865 (class 0 OID 24978)
-- Dependencies: 207
-- Data for Name: login; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('Arbri', 'A', 'admin', 'aaa@tim.com', '123', 'admin', '0678756453', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A18', 'B18', 'sss', 'cj', '123', 'shqyrtues', '0555', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A19', 'B19', 'ttt', 'ce', '123', 'autor', '0555', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A20', 'B20', 'uuu', 'cr', '123', 'shqyrtues', '0555', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A21', 'B21', 'vvv', 'ce', '123', 'autor', '0555', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A22', 'B22', 'bbb', 'cw', '123', 'shqyrtues', '0555', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A23', 'B23', 'yyy', 'ck', '123', 'autor', '0555', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A24', 'B24', 'zzz', 'cy', '123', 'shqyrtues', '0555', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('oooooooooo', 'oooooooooo', 'ooooooooooo', 'oooooooooooo', '098', 'autor', 'ooooooooo', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('were', 'werw', 'asdsd', 'sda', '555', 'autor', 'sdasd', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('Arbri', 'A', 'add', 'beni37tr@gmail.com', '123', 'autor', '0678756453', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('asadsadsdssd', 'sdcs', 'sdsd', 'sdsds', '123', 'shqyrtues', '34343', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('d', 'd', 'ddddd', 'd', '12344', 'erw', '52345', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('sdgfsdgsdgff', 'sdgfsdfdsfsd', 'sdfsdfsdf', 'sdfsdfsdf', 'B37D0257215899B9F0A11549B711B887398B092286B289E8E1077680D3936F0007C0721369370499385A29ED2825258CB95904E35DBB24CBDFED5ED3B9E4AB07', 'autor', '32354235', '\xc9c434a863988012ce8e5ff1496271d3435b0719');
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('New', 'Encrypted', 'admcypt', 'aaa@timyy.com', '13717359990395E9BAD57D895F93034BF28A0173A0E9007991E2D9ACA1EB073AD3A29930650CA4A7B409BA20B80BC2F0154B793442075559ADACC9D5DBA8C82E', 'autor', '0678756453', '\x4517d73b1b500cd17aa8f4707ca4d6a96bedd482');
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('An', 'An', 'adm', 'an@gm.com', 'D0D9B2BE241BAE2BC60C49E44FC82F7C03AD1C114B6920FF2459FA4183F2135B09D997B95D7EB9A14DC3F468583AA689B75D0A98DAF6CB745E488681D4D2AF2D', 'autor', '0678756453', '\x63e64c81113462929dd6f0a9e96670ad9f80cd58');
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A16', 'B16', 'qqq', 'bk', '123', 'autor', '0555', NULL);
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A20202020', '1', 'dma', '1', 'BFA8DD297C3577910673B27E668B1E1AB4B86DAE411EC967906B8A02D20BC8256BB9520D11D404C2C89C521F2A065E05F18C86877B4B47E0283781260EB543E0', 'admin', '235243', '\x14105998e05f20a4c4eb533a833e70b82b951fef');
INSERT INTO public.login (emri, mbiemri, username, email, password, kategoria, nrcel, salt) VALUES ('A17171717', 'B17', 'rrr', 'by', '123', 'shqyrtues', '0555', NULL);


--
-- TOC entry 2862 (class 0 OID 16658)
-- Dependencies: 204
-- Data for Name: shqyrtues; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.shqyrtues (id_email, emri, mbiemri, institucioni) VALUES ('lk@vz.com', 'Sh 2', 'Mb Sh 2', 'Institucioni 2');
INSERT INTO public.shqyrtues (id_email, emri, mbiemri, institucioni) VALUES ('eu@vz.com', 'Sh 3', 'Mb Sh 3', 'Institucioni 3');


--
-- TOC entry 2863 (class 0 OID 16681)
-- Dependencies: 205
-- Data for Name: shqyrtues_artikull; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.shqyrtues_artikull (shqrtid, arid, merita_teknike, kuptueshmeria, origjinaliteti, perkatesi_konference, rekomandime, vleresim_id) VALUES ('eu@vz.com', 1, 3, 2, 0, 3, 'Test1', 61);


--
-- TOC entry 2866 (class 0 OID 25010)
-- Dependencies: 208
-- Data for Name: shqyrtues_tema; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.shqyrtues_tema (id, shqyrtues_id, temat) OVERRIDING SYSTEM VALUE VALUES (1, 'pl@vz.com', 'Ekonomi');
INSERT INTO public.shqyrtues_tema (id, shqyrtues_id, temat) OVERRIDING SYSTEM VALUE VALUES (2, 'eu@vz.com', 'Investime');
INSERT INTO public.shqyrtues_tema (id, shqyrtues_id, temat) OVERRIDING SYSTEM VALUE VALUES (3, 'usd@vz.com', 'Marketing');
INSERT INTO public.shqyrtues_tema (id, shqyrtues_id, temat) OVERRIDING SYSTEM VALUE VALUES (4, 'cad@vz.com', 'Sistemi fiskal');
INSERT INTO public.shqyrtues_tema (id, shqyrtues_id, temat) OVERRIDING SYSTEM VALUE VALUES (5, 'eu@vz.com', 'Sipermarrje');


--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 202
-- Name: artikull_artikull_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.artikull_artikull_id_seq', 6, true);


--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 210
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 11, true);


--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 211
-- Name: sequence_vleresimi_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_vleresimi_id', 110, true);


--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 209
-- Name: shqyrtues_tema_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shqyrtues_tema_id_seq', 5, true);


--
-- TOC entry 2720 (class 2606 OID 16657)
-- Name: artikull artikull_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artikull
    ADD CONSTRAINT artikull_pkey PRIMARY KEY (artikull_id);


--
-- TOC entry 2726 (class 2606 OID 16757)
-- Name: autor autor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (email_id);


--
-- TOC entry 2728 (class 2606 OID 24982)
-- Name: login login_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login
    ADD CONSTRAINT login_pkey PRIMARY KEY (username, email, nrcel);


--
-- TOC entry 2722 (class 2606 OID 16665)
-- Name: shqyrtues shqyrtues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shqyrtues
    ADD CONSTRAINT shqyrtues_pkey PRIMARY KEY (id_email);


--
-- TOC entry 2730 (class 2606 OID 25017)
-- Name: shqyrtues_tema shqyrtues_tema_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shqyrtues_tema
    ADD CONSTRAINT shqyrtues_tema_pkey PRIMARY KEY (id, temat);


--
-- TOC entry 2724 (class 2606 OID 25070)
-- Name: shqyrtues_artikull vleresim_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shqyrtues_artikull
    ADD CONSTRAINT vleresim_id_pk PRIMARY KEY (vleresim_id);


--
-- TOC entry 2733 (class 2606 OID 16758)
-- Name: autor autor_artikull_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_artikull_id_fkey FOREIGN KEY (artikull_id) REFERENCES public.artikull(artikull_id);


--
-- TOC entry 2732 (class 2606 OID 16691)
-- Name: shqyrtues_artikull shqyrtues_artikull_arid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shqyrtues_artikull
    ADD CONSTRAINT shqyrtues_artikull_arid_fkey FOREIGN KEY (arid) REFERENCES public.artikull(artikull_id);


--
-- TOC entry 2731 (class 2606 OID 16686)
-- Name: shqyrtues_artikull shqyrtues_artikull_shqrtid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shqyrtues_artikull
    ADD CONSTRAINT shqyrtues_artikull_shqrtid_fkey FOREIGN KEY (shqrtid) REFERENCES public.shqyrtues(id_email);


-- Completed on 2020-09-24 20:19:39

--
-- PostgreSQL database dump complete
--

