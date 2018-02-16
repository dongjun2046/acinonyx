create database acinonyx_int;
\c acinonyx_int
CREATE TABLE allrequests (
    ticket_id character varying(10) NOT NULL,
    ticket_type character varying(20),
    requestor character varying(15),
    req_start timestamp without time zone,
    req_end timestamp without time zone,
    ticket_text text,
    ticket_status character varying(10)
);

CREATE SEQUENCE ticket_sequence
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE users (
    shortid character varying(14) NOT NULL,
    type integer NOT NULL,
    password character varying(12)
);

ALTER TABLE ONLY allrequests    ADD CONSTRAINT allrequests_pkey PRIMARY KEY (ticket_id);

ALTER TABLE ONLY users    ADD CONSTRAINT users_pkey PRIMARY KEY (shortid);
	
create user acinonyx_int with password 'acinonyx';

GRANT ALL ON SEQUENCE ticket_sequence TO acinonyx_int;

GRANT ALL ON TABLE allrequests TO acinonyx_int;

GRANT ALL ON TABLE users TO acinonyx_int;
