alter table ALGORITHMS add column section_id int null;
alter table ALGORITHMS
    add foreign key (section_id) references SECTIONS (id);
