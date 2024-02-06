select *
from certification;

select *
from harvard_school;

select *
from degree_program;

select degree_program.name,
       certification.name,
       harvard_school.name,
       degree_program.description,
       degree_program.image
from degree_program
         left join certification
                   on degree_program.certification = certification.id
         left join harvard_school
                   on degree_program.harvard_school = harvard_school.id
where harvard_school.name = 'Harvard College'
  and certification.degree_level = 'undergraduate'
  and (
    degree_program.name like concat('%', 'am', '%')
        or degree_program.description like concat('%', 'am', '%')
    )
;

select name, abbr, phone, email, address from harvard_school;

show function status where db = 'harvard';
