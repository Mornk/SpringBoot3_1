package com.project.homework.controller;

import com.project.homework.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private List<Student> students = new ArrayList<>();
    private Long id = 1L;

    @GetMapping(value = "/")
    public String mainPage(Model model){
        model.addAttribute("students", students);
        return "home";
    }
    @GetMapping(value = "/addStudentPage")
    public String addStudentPage(){
        return "addstudent";
    }
    @PostMapping(value = "/addStudent")
    public String addStudent(Model model, @RequestParam(name = "student_name") String name,
                             @RequestParam(name = "surname") String surname,
                             @RequestParam(name = "exam") int exam){
        Student student = new Student();
        student.setId(id);
        id++;
        student.setName(name);
        student.setSurname(surname);
        student.setExam(exam);
        addStudent(student);
        model.addAttribute("students", students);
        return "redirect:/";
    }

    private void addStudent(Student student){
        if(student.getExam()>=90){
            student.setMark("A");
        } else if(student.getExam()>=75){
            student.setMark("B");
        } else if(student.getExam()>=60){
            student.setMark("C");
        } else if(student.getExam()>=50){
            student.setMark("D");
        } else {
            student.setMark("F");
        }
        students.add(student);
    }
    private List<Student> getStudents(){
        return students;
    }
}
