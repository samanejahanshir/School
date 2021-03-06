package service;

import models.*;
import School.enums.Degree;
import School.enums.TeacherType;
import School.exception.ItemExistException;

import java.util.*;
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
        return schools.stream().filter(school -> school.getName().equalsIgnoreCase(name)).findAny();

    }

    public Optional<Course> findCourseByName(String name) {
        return courses.stream().filter(course -> course.getName().equalsIgnoreCase(name)).findAny();

    }

    public int addSchoolToTeacher(String personalNum, String name) {
        Optional<Teacher> teacher = findByPersonalCode(personalNum);
        Optional<School> school = findSchoolByName(name);

        if (teacher.isPresent() && school.isPresent()) {
            if (teacher.get().searchSchool(school.get()) != 1) {
                Set<School> schools = teacher.get().getSchool();
                schools.add(school.get());
                teacher.get().setSchool(schools);
                return 1;
            } else {
                throw new ItemExistException("this school exist in teacher's list");
            }

        } else
            return -1;
    }

    public int addCourseToTeacher(String personalNum, String name) {
        Optional<Teacher> teacher = findByPersonalCode(personalNum);
        Optional<Course> course = findCourseByName(name);

        if (teacher.isPresent() && course.isPresent()) {
            if (teacher.get().searchCourse(course.get()) != 1) {
                Set<Course> courses = teacher.get().getCourse();
                courses.add(course.get());
                teacher.get().setCourse(courses);
                return 1;
            } else {
                throw new ItemExistException("this course exist in teacher's list");
            }

        } else
            return -1;
    }


    public List<Teacher> getSalaryMoreThanAvgFullTeacher() {
        double avgSalary = calculateAverageSalary();
        teachers.stream().forEach(i -> i.calculateSalary());
        return teachers.stream().filter(i -> i.getNetSalary() > avgSalary).collect(Collectors.toList());
    }

    public double calculateAverageSalary() {
        teachers.forEach(i -> i.calculateSalary());
        double sumSalary = teachers.stream().filter(i -> i.getType().equals(TeacherType.FULL_TIME))
                .map(i -> i.getNetSalary()).reduce(0D, (i, j) -> i + j);
        long count = teachers.stream().filter(i -> i.getType().equals(TeacherType.FULL_TIME)).count();
        return sumSalary / count;
    }

    public Map<TeacherType, List<Teacher>> listTeacherByExperienceYear() {
        return teachers.stream().filter(teacher -> teacher.getExperienceYear() == 10)
                 .collect(Collectors.groupingBy(i -> i.getType()));
    }

    public List<Teacher> getPartTimeTeacherByDegree() {
        return teachers.stream().filter(teacher -> teacher.getDegree().equals(Degree.BS))
                .filter(teacher -> teacher.existSchoolByDegree(2))
                .filter(teacher -> teacher.getCourse().size() > 2).collect(Collectors.toList());

    }

    public Set<String> getNameSchoolsByListTeacher() {
        Set<School> schools = new HashSet<>();
        Set<String> schoolsName=new HashSet<>();
        for (int i = 0; i < teachers.size(); i++) {
            schools.addAll(teachers.stream().map(teacher -> teacher.getSchool())
                    .collect(Collectors.toList()).get(i));
        }
        schoolsName.addAll(schools.stream().map(school->school.getName()).collect(Collectors.toList()));
        return schoolsName;
    }

    public Map<String, List<Teacher>> getListTeacherOfSchool() {

       /* return teachers.stream().collect(Collectors
                .groupingBy(i -> i.getSchool().stream().map(j -> j.getName()).toString()));
*/Map<String, List<Teacher>> mapSchoolTeachers=new TreeMap<>();
        for (School school : schools) {
           List<Teacher> teachersList= teachers.stream().filter(teacher->teacher.getSchool().contains(school)).collect(Collectors.toList());
            mapSchoolTeachers.put(school.getName(),teachersList);
        }
        return mapSchoolTeachers;
    }


}

