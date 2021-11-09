package School.main;


import models.Course;
import models.School;
import service.TeacherService;

public class Main {

    public static void main(String[] args) {
        TeacherService teacherService=new TeacherService();
        Course course = new Course("math", 1);
        Course course1 = new Course("computer", 2);
        Course course2 = new Course("physic", 3);
        Course course3 = new Course("history", 4);
        Course course4 = new Course("art", 5);
        teacherService.courses.add(course);
        teacherService.courses.add(course1);
        teacherService.courses.add(course2);
        teacherService.courses.add(course3);
        teacherService.courses.add(course4);
        /////////////////////////////////////////////////////////////////////////////////
        School school =  new School("maktab", 1);
        School school1 = new School("madani", 2);
        School school2 = new School("alavi", 3);
        School school3 = new School("razavi", 3);
        School school4 = new School("jalal", 1);
        School school5 = new School("diba", 1);
        teacherService.schools.add(school);
        teacherService.schools.add(school1);
        teacherService.schools.add(school2);
        teacherService.schools.add(school3);
        teacherService.schools.add(school4);
        teacherService.schools.add(school5);
        /////////////////////////////////////////////////////////////////////////////////
        System.out.println("--------| addSchoolToTeacher |--------");
        System.out.println("Teacher add to School = "+teacherService.addSchoolToTeacher("234567",school1.getName()));
        System.out.println("Teacher add to School = "+teacherService.addSchoolToTeacher("156785",school2.getName()));
        System.out.println("Teacher add to School = "+teacherService.addSchoolToTeacher("234567",school3.getName()));
        System.out.println("Teacher add to School = "+teacherService.addSchoolToTeacher("156785",school1.getName()));
        System.out.println("Teacher add to School = "+teacherService.addSchoolToTeacher("290846",school2.getName()));
        System.out.println();
        System.out.println("******************************************");

        System.out.println("--------| addCourseToTeacher |--------");
        System.out.println("Course add to Teacher = "+teacherService.addCourseToTeacher("234567",course1.getName()));
        System.out.println("Course add to Teacher = "+teacherService.addCourseToTeacher("234567",course2.getName()));
        System.out.println("Course add to Teacher = "+teacherService.addCourseToTeacher("234567",course3.getName()));
        System.out.println();
        System.out.println("******************************************");


        System.out.println("--------| getSalaryMoreThanAvgFullTeacher |--------");
        System.out.println(teacherService.getSalaryMoreThanAvgFullTeacher());
        System.out.println();
        System.out.println("******************************************");

        System.out.println("--------| listTeacherByExperienceYear |--------");
        System.out.println(teacherService.listTeacherByExperienceYear());
        System.out.println();
        System.out.println("******************************************");

        System.out.println("--------| getListTeacherOfSchool |--------");
        teacherService.getListTeacherOfSchool().forEach((i,j)-> System.out.println(i+" : "+j));
        System.out.println();
        System.out.println("******************************************");

        System.out.println("--------| getPartTimeTeacherByDegree |--------");
        System.out.println(teacherService.getPartTimeTeacherByDegree());
        System.out.println();
        System.out.println("******************************************");
        System.out.println("--------| getSchoolByListTeacher |--------");
        System.out.println(teacherService.getNameSchoolsByListTeacher());




    }

}
