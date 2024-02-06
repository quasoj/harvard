
set @UN = 'undergraduate';
set @G = 'graduate';

insert into certification(name, fullname, degree_level)
values ('A.B.', 'Bachelor of Arts', @UN),
       ('S.B.', 'Bachelor of Science', @UN),
       ('A.L.B.', 'Bachelor of Liberal Arts', @UN)
;

insert into certification(name, fullname, degree_level)
values ('A.L.M.', 'Master of Liberal Arts', @G),
       ('M.A.', 'Master of Arts', @G),
       ('S.M.', 'Master of Science', @G),
       ('LL.M.', 'Master of Law', @G),
       ('Ed.M.', 'Master in Education', @G),
       ('M.E.', 'Master in Engineering', @G),
       ('M.Arch.', 'Master of Architecture', @G),
       ('M.A.D.', 'Master of Academic Discipline', @G),
       ('M.B.A.', 'Master in Business Administration', @G),
       ('M.P.A.', 'Master of Public Administration', @G),
       ('M.P.P.', 'Master of Public Policy', @G),
       ('M.P.H.', 'Master of Public Health', @G),
       ('M.P.H.-GEN.', 'Master of Public Health Generalist', @G),
       ('M.P.H.-Epi.', 'Master of Public Health in Epidemiology', @G),
       ('M.S.-Epi.', 'Master of Public Science in Epidemiology', @G),
       ('M.T.S.', 'Master of Theological Studies', @G),
       ('Th.M.', 'Master of Theology', @G),
       ('M.Div.', 'Master of Divinity', @G),
       ('M.Des.', 'Master in Design Studies', @G),
       ('M.D.E.', 'Master in Design Engineering', @G),
       ('M.R.E.', 'Master in Real Estate', @G),
       ('M.R.P.L.', 'Master of Religion and Public Life', @G),
       ('M.M.Sc.', 'Master of Medical Science', @G),
       ('M.H.C.M.', 'Master in Health Care Management', @G),
       ('M.U.P.', 'Master in Urban Planning', @G),
       ('M.A.U.D.', 'Master of Architecture in Urban Design', @G),
       ('M.L.A.U.D.', 'Master of Landscape Architecture in Urban Design', @G)
;

insert into certification(name, fullname, degree_level)
values ('Ph.D.', 'Doctor of Philosophy', @G),
       ('Ed.L.D.', 'Doctor of Education Leadership', @G),
       ('D.M.Sc.', 'Doctor of Medical Science', @G),
       ('M.D.', 'Doctor of Medicine', @G),
       ('J.D.', 'Juris Doctor', @G),
       ('S.J.D.', 'Doctor of Juridical Science', @G),
       ('Dr.P.H.', 'Doctor of Public Health', @G),
       ('D.M.D.', 'Doctor of Medicine in Dentistry', @G),
       ('D.Des.', 'Doctor of Design', @G)
;

insert into harvard_school(name, abbr)
values ('Harvard College', 'hcol'),
       ('Harvard Business College', 'hbs'),
       ('Division of Continue Education', 'dce'),
       ('Harvard Divinity School', 'hds'),
       ('Harvard Kenneth C. Griffin Graduate School of Arts and Science', 'gsas'),
       ('Harvard Graduate School of Design', 'hgsd'),
       ('Harvard Graduate School of Education', 'hgse'),
       ('Harvard John A. Paulson School of Engineering and Applied Sciences', 'seas'),
       ('Harvard Kennedy School', 'hks'),
       ('Harvard Law School', 'hls'),
       ('Harvard Medical School', 'hms'),
       ('Harvard Radcliffe Institute', 'hri'),
       ('Harvard School of Dental Medicine', 'hsdm'),
       ('Harvard T.H. Chan School of Public Health', 'sph')
;

-- functions of get id by name

DELIMITER //
drop function if exists get_certification_id;
create function get_certification_id(name varchar(128)) returns int
    deterministic
begin
    declare result int;
    set result = (select id from certification where certification.name = name);
    return result;
end; //
DELIMITER ;

DELIMITER //
drop function if exists get_school_id;
create function get_school_id(name varchar(128)) returns int
    deterministic
begin
    declare result int;
    set result = (select id from harvard_school where harvard_school.name = name);
    return result;
end; //
DELIMITER ;

-- global variable

set @AB = 'A.B.';
set @HC = 'Harvard College';

-- CRUD example

delete
from degree_program
where name = 'African and African American Studies';

-- programs of Harvard College

insert into degree_program(name, certification, harvard_school)
values ('African and African American Studies',
        get_certification_id(@AB),
        get_school_id(@HC));

update degree_program
set description = 'The Department of African and African American Studies brings together scholars and scholarship from many disciplines to explore the histories, societies, and cultures of African and African-descended people. The field of African and African American studies is not only interdisciplinary but also comparative and cross-cultural. The department offers two distinct courses of study: the African track and the African American track.'
where name = 'African and African American Studies'
  and degree_program.certification = (select id
                                      from certification
                                      where certification.name = @AB)
  and degree_program.harvard_school = (select id
                                       from harvard_school
                                       where harvard_school.name = @HC)
;

insert into degree_program(name, certification, harvard_school, description)
values ('Anthropology',
        get_certification_id(@AB),
        get_school_id(@HC),
        'Anthropology brings global, comparative, and holistic views to the study of the human condition, exploring the enormous range of similarities and differences across time and space. It includes the study of how human behavior has evolved as well as how language, culture, and society have shaped and continue to shape the human experience. At Harvard, the Anthropology Department is divided into two programs: Archaeology and Social Anthropology.');

insert into degree_program(name, certification, harvard_school, description)
values ('Art, Film, and Visual Studies',
        get_certification_id(@AB),
        get_school_id(@HC),
        'The concentration in Art, Film, and Visual Studies (AFVS) cultivates skills in both the practice and the critical study of the visual arts. The modes of teaching combine the intensity of conservatory programs with the broad intellectual aims of a liberal arts college. Within AFVS, there are three different areas of focus—1) studio arts, 2) film/video making, and 3) film and visual studies—and each have slightly different requirements.');

insert into degree_program(name, certification, harvard_school, description)
values ('Astrophysics',
        get_certification_id(@AB),
        get_school_id(@HC),
        'The science of astrophysics involves the study of matter and radiation in the universe as understood through the laws of physics. Students are introduced to a broad range of phenomena through a program of both observational and theoretical courses. This program builds from a foundation of modern physics to a general account of the known contents of the universe. Astrophysics offers joint concentrations with other departments.');

insert into degree_program(name, certification, harvard_school, description)
values ('Biomedical Engineering',
        get_certification_id(@AB),
        get_school_id(@HC),
        'Biomedical Engineering lies at the intersection of the physical and life sciences, incorporating principles from physics and chemistry to understand the operation of living systems. As in other engineering fields, the approach is highly quantitative: mathematical analysis and modeling are used to capture the function of systems from subcellular to organism scales. The objectives of this concentration include providing students a solid foundation in engineering, particularly as applied to the life sciences, within the setting of a liberal arts education.');

select degree_program.name as degree_program_name,
       certification.name as certification_name,
       harvard_school.name as harvard_school_name,
       degree_program.description as description
from degree_program
         left join certification
                   on degree_program.certification = certification.id
         left join harvard_school
                   on degree_program.harvard_school = harvard_school.id
where harvard_school.name = 'Harvard College'
;

insert into user(username, password, email)
values ('ace', '8964', 'wow.itsme.ace@gmail.com');
