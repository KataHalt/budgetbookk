ALTER TABLE transactions ADD COLUMN username VARCHAR(255);
UPDATE transactions SET username = 'alice' WHERE username IS NULL;
ALTER TABLE transactions ALTER COLUMN username SET NOT NULL;