--create SCHEMA SHORT_URL_APP;

/*
drop sequence SHORT_URL_APP.Url_Seq RESTRICT;

drop table SHORT_URL_APP.URL;
drop table SEQUENCE_TABLE;

Create table SHORT_URL_APP.URL (
    ID                             INT                 NOT NULL    ,
    SHORT_URL                      VARCHAR(10)         NOT NULL    ,
    LONG_URL                       VARCHAR(32672)      NOT NULL    ,
    LAST_MODIFIED_BY               VARCHAR(256)        NOT NULL    ,
    LAST_MODIFIED_TM               DATE                NOT NULL    ,
    CREATED_TM                     DATE                NOT NULL    ,
    VERSION						   INT                 NOT NULL	);



--------------------------------------------------
-- Create Primary Key URL_KEY
--------------------------------------------------
alter table SHORT_URL_APP.URL 
	add constraint URL_KEY 
	Primary Key (ID);


Create table SHORT_URL_APP.SEQUENCE_TABLE (

	SEQ_NAME              Varchar(10)      NOT NULL ,
	SEQ_COUNT             Int              NOT NULL);

)

alter table SHORT_URL_APP.SEQUENCE_TABLE
            add constraint SEQ_KEY
            Primary Key (SEQ_NAME);

	
Insert into SHORT_URL_APP.SEQUENCE_TABLE (SEQ_NAME, SEQ_COUNT) values ('url_seq', 0);


CREATE SEQUENCE SHORT_URL_APP.Url_Seq as INT
START WITH 50
INCREMENT BY 1
CYCLE;
   
   

values NEXT VALUE 
for short_url_app.url_seq
*/  
            
/*	
--------------------------------------------------
-- Create Index SHORT_URL_APP.URL_IDX 
--------------------------------------------------
create  Index SHORT_URL_APP.URL_IDX 
	on SHORT_URL_APP.URL 
	(SHORT_URL);

--------------------------------------------------
-- Create Index SHORT_URL_APP.URK_LONG_IDX
--------------------------------------------------
create  Index SHORT_URL_APP.URK_LONG_IDX 
	on SHORT_URL_APP.URL 
	(LONG_URL);	
	
*/	

/** Test data insertion **/	
--Insert into SHORT_URL_APP.URL (SHORT_URL, LONG_URL, LAST_MODIFIED_BY, LAST_MODIFIED_TM, CREATED_TM,VERSION)
--values ('abc123', 'www.yahoo.com','testData', CURRENT_DATE, CURRENT_DATE, 1);
	
	
	