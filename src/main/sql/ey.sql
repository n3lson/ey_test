DELIMITER //
CREATE PROCEDURE GET_INT_SUM()
BEGIN
    SELECT SUM(r_int) AS int_sum FROM random_data;
END
//

CREATE PROCEDURE GET_FLOAT_MEDIAN()
BEGIN
    DECLARE median INTEGER;
    SET median = (SELECT FLOOR(COUNT(r_float) / 2) FROM random_data);

    IF (SELECT MOD(COUNT(r_float), 2) FROM random_data) = 1 THEN
        SELECT r_float AS median
        FROM random_data ORDER BY r_float LIMIT median, 1;
    ELSE
        SELECT SUM(r_float) / 2 AS median
        FROM (SELECT r_float FROM random_data ORDER BY r_float LIMIT median, 2) t;
    END IF;
END
//

CALL GET_INT_SUM();
CALL GET_FLOAT_MEDIAN();
