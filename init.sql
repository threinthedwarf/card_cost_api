CREATE TABLE IF NOT EXISTS clearing_cost (
                                             id SERIAL PRIMARY KEY,
                                             country VARCHAR(255) UNIQUE NOT NULL,
                                             cost DECIMAL(10, 2) NOT NULL
);

-- Insert data only if the table is empty
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM clearing_cost) THEN
        INSERT INTO clearing_cost (country, cost) VALUES
                                                      ('US', '5'),
                                                      ('GR', '15'),
                                                      ('Others', '10');
    END IF;
END $$;