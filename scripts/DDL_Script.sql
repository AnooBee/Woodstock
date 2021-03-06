--<ScriptOptions statementTerminator=";"/>

ALTER TABLE "SHORT_URL_APP"."URL" DROP CONSTRAINT "URL_KEY";

DROP INDEX "SHORT_URL_APP"."SQL150421103625380";

DROP TABLE "SHORT_URL_APP"."URL";

CREATE TABLE "SHORT_URL_APP"."URL" (
		"SHORT_URL" VARCHAR(10) NOT NULL,
		"LONG_URL" VARCHAR(32672) NOT NULL,
		"LAST_MODIFIED_BY" VARCHAR(256) NOT NULL,
		"LAST_MODIFIED_TM" DATE NOT NULL,
		"CREATED_TM" DATE NOT NULL,
		"VERSION" INTEGER NOT NULL
	);

CREATE UNIQUE INDEX "SHORT_URL_APP"."SQL150421103625380" ON "SHORT_URL_APP"."URL" ("SHORT_URL" ASC);

ALTER TABLE "SHORT_URL_APP"."URL" ADD CONSTRAINT "URL_KEY" PRIMARY KEY ("SHORT_URL");

