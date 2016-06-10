--DROP function pochodzenie() CASCADE;
CREATE OR REPLACE FUNCTION pochodzenie() RETURNS TRIGGER AS $example_table$
    BEGIN
	CREATE TABLE IF NOT EXISTS narodowosc(id_nar SERIAL PRIMARY KEY UNIQUE,text DATE);
        INSERT INTO narodowosc(text) VALUES (current_timestamp);
        RETURN NEW;
    END;
$example_table$ LANGUAGE plpgsql;

CREATE TRIGGER pochodzenie AFTER INSERT ON autor
FOR EACH ROW EXECUTE PROCEDURE pochodzenie();