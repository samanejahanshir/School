package service;

import School.Course;
import School.School;
import School.Teacher;
import School.enums.Degree;
import School.*;
import School.enums.TeacherType;
import School.exception.ItemExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TeacherService {
    public List<Teacher> teachers = new ArrayList<>();
    public List<Course> courses = new ArrayList<>();
    public List<School> schools = new ArrayList<>();

    public TeacherService() {

        Teacher teacher1 = new PartTimeTeacher("zahra", "zahraei", "123456", 120_000, 10);
        teacher1.setDegree(Degree.PHD);
        teacher1.setType(TeacherType.PART_TIME);
        teacher1.setExperienceYear(10);
        Teacher teacher2 = new PartTimeTeacher("ali", "aliyani", "234567", 20_000, 50);
        teacher2.setDegree(Degree.BS);
        teacher2.setType(TeacherType.PART_TIME);
        teacher2.setExperienceYear(12);
        Teacher teacher3 = new PartTimeTeacher("zahra", "zahraei", "345678", 120_000, 5);
        teacher3.setDegree(Degree.PHD);
        teacher3.setType(TeacherType.PART_TIME);
        teacher3.setExperienceYear(9);
        Teacher teacher4 = new PartTimeTeacher("fateme zahra", "zahraeian", "567890", 20_00, 5);
        teacher4.setDegree(Degree.MA);
        teacher4.setType(TeacherType.PART_TIME);
        teacher4.setExperienceYear(10);
        Teacher teacher5 = new FullTimeTeacher("ali", "taheri", "156785", 100_000);
        teacher5.setDegree(Degree.MA);
        teacher5.setType(TeacherType.FULL_TIME);
        teacher5.setExperienceYear(10);
        Teacher teacher6 = new FullTimeTeacher("farhad", "babaei", "823514", 90_000);
        teacher6.setDegree(Degree.BS);
        teacher6.setType(TeacherType.FULL_TIME);
        teacher6.setExperienceYear(6);
        Teacher teacher7 = new FullTimeTeacher("taghi", "taghipoor", "290846", 110_000);
        teacher7.setDegree(Degree.PHD);
        teacher7.setType(TeacherType.FULL_TIME);
        teacher7.setExperienceYear(14);
        Teacher teacher8 = new FullTimeTeacher("sogol", "rahmani", "398176", 220_00);
        teacher8.setDegree(Degree.BS);
        teacher8.setType(TeacherType.FULL_TIME);
        teacher8.setExperienceYear(7);
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
        teachers.add(teacher4);
        teachers.add(teacher5);
        teachers.add(teacher6);
        teachers.add(teacher7);
        teachers.add(teacher8);
    }

    public Optional<Teacher> findByPersonalCode(String personalCode) {
        return teachers.stream().filter(i -> i.getPersonalNumber().equalsIgnoreCase(personalCode)).findAny();

    }
    public Optional<School> findSchoolByName(String name) {
        return schools.stream().filter(i -> i.getName().equalsIgnoreCase(name)).findAny();

    }

    public Optional<Course> findCourseByName(String name) {
        return courses.stream().filter(i -> i.getName().equalsIgnoreCase(name)).findAny();

    }

    public int addSchoolToTeacher(String personalNum,String name){
      Optional<Teacher> teacher=findByPersonalCode(personalNum);
      Optional<School> school=findSchoolByName(name);

        if (teacher.isPresent() && school.isPresent()) {
            if(teacher.get().searchSchool(school.get())!=1){
                Set<School> schools=teacher.get().getSchool();
                schools.add(school.get());
                teacher.get().setSchool(schools);
                return 1;
            }else {
                throw new ItemExistException("this school exist in teacher's list");
            }

        } else
            return -1;
    }



}
