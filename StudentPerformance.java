package studentdemo;

import java.util.*;
import java.util.stream.Collectors;


public class StudentPerformance {
    public static void main(String[] args) {
        List<StudentReport> studentList = new ArrayList<StudentReport>();
        StudentReport Stu1 = new StudentReport(101, "Anu", "Java", 82, true);
        studentList.add(Stu1);
        StudentReport Stu2 = new StudentReport(102, "Bala", "Java", 45, false);
        studentList.add(Stu2);
        StudentReport Stu3 = new StudentReport(103, "Charan", "Python", 91, true);
        studentList.add(Stu3);
        StudentReport Stu4 = new StudentReport(104, "Divya", "Java", 67, true);
        studentList.add(Stu4);
        StudentReport Stu5 = new StudentReport(105, "Esha", "Python", 38, false);
        studentList.add(Stu5);
        StudentReport Stu6 = new StudentReport(106, "Farhan", "DevOps", 74, true);
        studentList.add(Stu6);
        StudentReport Stu7 = new StudentReport(107, "Gokul", "DevOps", 88, true);
        studentList.add(Stu7);

        StudentReport Stu8 = new StudentReport(108, "Hari", "Java", 53, true);
        studentList.add(Stu8);
        StudentReport Stu9 = new StudentReport(109, "Isha", "Python", 79, true);
        studentList.add(Stu9);
        StudentReport Stu10 = new StudentReport(110, "John", "DevOps", 62, true);
        studentList.add(Stu10);
        StudentReport Stu11 = new StudentReport(111, "Kavya", "Java", 95, true);
        studentList.add(Stu11);
        StudentReport Stu12 = new StudentReport(112, "Lokesh", "Python", 49, false);
        studentList.add(Stu12);
        //System.out.println(studentList);
        for (StudentReport s : studentList) {
            System.out.println("ID="+s.studentId+"\n\t"+"Name="+s.studentName+"\n\t"+"Course="+s.course+"\n\t"+"Mark="+s.mark+"\n\t"+"Passed="+s.result);
        }
        Set<String> stuCourse = new HashSet<>();
        for (StudentReport s : studentList) {
            stuCourse.add(s.getCourse());
        }
        System.out.println("Unique Courses are:");
        System.out.println(stuCourse);
        Map<String, List<StudentReport>> courseGroup =
                studentList.stream()
                        .collect(Collectors.groupingBy(StudentReport::getCourse));

        System.out.println("\n"+"Students Grouped by Course (Map)");

        for (String course : courseGroup.keySet()) {

            List<String> names = courseGroup.get(course)
                    .stream()
                    .map(StudentReport::getStudentName)
                    .collect(Collectors.toList());

            System.out.println(course + " → " + names);
        }

        List<StudentReport> passedStudent = studentList.stream()
                .filter(s -> s.mark > 50)
                .collect(Collectors.toList());

        System.out.println("\n\n"+"Passed Students (Filtered)");

        String names = passedStudent.stream()
                .map(StudentReport::getStudentName)   // or s -> s.name
                .collect(Collectors.joining(", "));

        System.out.println(names);
        long noOfPassed = passedStudent.stream().count();
        System.out.println("\n"+"No.of Students Passes=" + noOfPassed);
        int TotalMarks = passedStudent.stream().map(s -> s.mark).reduce(0, Integer::sum);
        System.out.println("TotalMarks of Passed Students=" + TotalMarks);
        double averageMarks = passedStudent.stream().mapToInt(s1 -> s1.mark).average().orElse(0);
        System.out.println("Average Marks=" + averageMarks);
        StudentReport HighestScorer = passedStudent.stream().max(Comparator.comparingInt(s2 -> s2.mark)).orElse(null);
        System.out.println("Highest Scorer=" + HighestScorer.getStudentName()+"-"+HighestScorer.getMark());

        List<StudentReport> sortedPassed=passedStudent.stream().sorted(Comparator.comparing(StudentReport::getMark)).toList().reversed();
        System.out.println("\n"+"Student Passed : Sorted By Mark- Descending");
        for(StudentReport s:sortedPassed) {
            System.out.println(s.getStudentName()+"-"+s.getMark());
        }
        List<StudentReport> sortedByCourse=sortedPassed.stream().sorted(Comparator.comparing(StudentReport::getStudentName)).toList();
        System.out.println("\n"+"Sorted by Name Ascending");
        for(StudentReport s:sortedByCourse){
            System.out.println(s.getStudentName()+" "+s.getMark());
        }
        Map<String, Integer> courseWiseTotal =
                studentList.stream()
                        .collect(Collectors.groupingBy(
                                StudentReport::getCourse,
                                Collectors.summingInt(StudentReport::getMark)));

        System.out.println("\n"+"Course-wise Total Marks (Map)");

        courseWiseTotal.forEach((course, total) ->
                System.out.println(course + " → " + total));
        int targetId=101;
        Optional<StudentReport> studentSearch =
                studentList.stream()
                        .filter(s -> s.studentId == targetId)
                        .findFirst();

        System.out.println("\n"+"Optional Search Result (studentId = " + targetId + ")");

        if (studentSearch.isPresent()) {
            StudentReport s = studentSearch.get();
            System.out.println("Student found: "
                    + s.getStudentName() + " – "
                    + s.getCourse() + " – "
                    + s.getMark());
        } else {
            System.out.println("Student not found");
        }
        StudentCheck filter1 = s -> s.mark >= 75;

        System.out.println("Students with Marks ≥ 75 (Functional Interface Filter)");

        studentList.stream()
                .filter(filter1::check)
                .forEach(s -> System.out.println(s.studentName + " – " + s.mark));

        System.out.println("Final Report Printed Using Method Reference");
        System.out.println("(Displays the sorted passed student list)");

        List<StudentReport> passedStudent1 = studentList.stream()
                .filter(s -> s.mark > 50)
                .sorted(Comparator.comparing(StudentReport::getStudentName))
                .collect(Collectors.toList());


        System.out.println("Final Report Printed Using Method Reference");
        System.out.println("(Displays the sorted passed student list)");

        passedStudent1.forEach(System.out::println);


        }

    }

