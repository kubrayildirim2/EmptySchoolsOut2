package be.intecbrussel.schoolsout.services;

import be.intecbrussel.schoolsout.data.Course;
import be.intecbrussel.schoolsout.data.Grade;
import be.intecbrussel.schoolsout.data.User;
import be.intecbrussel.schoolsout.repositories.CourseRepository;
import be.intecbrussel.schoolsout.repositories.GradeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CourseService {

    private CourseRepository courseRepository;
    private GradeRepository gradeRepository;

    public CourseService(){
        courseRepository = new CourseRepository();
    }


    //TODO:Maak een Course met de constructor


        public void createCourse(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Give the name of the course");
            String name = scanner.next();
            System.out.println("Give the description of the course");
            String description = scanner.next();
            scanner.nextLine();
            System.out.println("Give the max amount of Points of the course");
            BigDecimal maxValue= scanner.nextBigDecimal();
            courseRepository.createOne(new Course(name,description,maxValue));

        }


    //TODO: Delete een course, en delete alle Grades van die Course
    public void deleteCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the Course Id.");
        int input = scanner.nextInt();
        courseRepository.deleteOne(input);

    }

    //TODO:Update een Course. Je mag enkel de name, description en maxGradeYouCanGet editten
    public void updateCourse(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the Course Id.");
        int input = scanner.nextInt();
        scanner.nextLine();

        Course course = courseRepository.getOneById(input);
        if (course==null){
            System.out.println("Try another number");
        }else {
            System.out.println("Found course: "+ course);

            int choice = 99;
            while(choice!=4 && choice!=5){
                System.out.println("Please select what you want to do:\n1)Update course name\n" +
                        "2)Update description\n3)Update maxGrade\n4)Finish update\n5)Cancel");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice){
                    case 1 :
                        System.out.println("Please enter new course name");
                        String name = scanner.nextLine();
                        course.setName(name);
                        System.out.println("Course name changed");
                        System.out.println("Current course is: "+ course);
                        break;
                    case 2 :
                        System.out.println("Please enter new course description");
                        String description = scanner.nextLine();
                        course.setDescription(description);
                        System.out.println("Course description changed");
                        System.out.println("Current course is: "+ course);
                        break;
                    case 3 :
                        System.out.println("Please enter new course maxGrade");
                        BigDecimal maxGrade = scanner.nextBigDecimal();
                        course.setMaxGradeYouCanGet(maxGrade);
                        System.out.println("Course maxGrade changed");
                        System.out.println("Current course is: "+ course);
                        break;
                    case 4 :
                        courseRepository.updateOne(course);
                        System.out.println("Course is updated successfully!");
                        break;
                    case 5 :
                        System.out.println("Update is cancelled");
                        break;
                }
            }
        }
    }

    //TODO:Toon een course op basis van Id
    public void findOneCourseById(){

        System.out.println("Please enter the course id");

        int id = new Scanner(System.in).nextInt();

        Course course = courseRepository.getOneById(id);
        System.out.println("Here is the course you are looking for: "+ course);

    }

    //TODO: Toon alle Courses
    public void findAllCourses(){
        System.out.println("Here are all courses:");
        for (Course course: courseRepository.getAll()){
            System.out.println(course);
        };

    }




    //TODO: Print alle Grades van een Course (hint: gettermethode)
    public void findAllGradesFromCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the Course Id");
        int input = scanner.nextInt();

        Course course = courseRepository.getOneById(input);
        if(course==null){
            System.out.println("Could not find the course with entered id!");
        }else{
            System.out.println("Here are all the grades of this course:");

            for(Grade grade: course.getGradesOfCourse()){
                System.out.println(grade);
            }
        }
    }
}
