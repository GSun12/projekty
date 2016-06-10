--drop function zwieksz_cene() CASCADE
CREATE FUNCTION zwieksz_cene()
RETURNS TRIGGER AS $$
BEGIN
 IF OLD.rabat<>NEW.rabat THEN
 UPDATE ksiazka SET cena=old.cena+10 where id=old.id;
END IF;
RETURN NEW;
END;
$$LANGUAGE 'plpgsql';

CREATE TRIGGER cena
AFTER UPDATE ON ksiazka
FOR EACH ROW EXECUTE PROCEDURE zwieksz_cene();
