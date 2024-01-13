
-- insert into usertable(id, name, birth_date)
-- values (596, 'mustafa as default in usertable', DATE '1989-01-01');


--insert into students(id, classno, name)
--values (447, 12, 'mustafa senyuz as default'); 


-- Insert mustafa as default in usertable if the ID does not exist
INSERT INTO usertable(id, name, birth_date)
SELECT 596, 'mustafa as default in usertable', DATE '1989-01-01'
WHERE NOT EXISTS (SELECT 1 FROM usertable WHERE id = 596);

-- Insert mustafa senyuz as default in students if the ID does not exist
INSERT INTO students(id, classno, name)
SELECT 447, 12, 'mustafa senyuz as default'
WHERE NOT EXISTS (SELECT 1 FROM students WHERE id = 447);

