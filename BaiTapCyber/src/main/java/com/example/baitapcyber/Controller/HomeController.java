package com.example.baitapcyber.Controller;

import com.example.baitapcyber.Entity.DemoEntity;
import com.example.baitapcyber.Entity.Employees;
import com.example.baitapcyber.Repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.List;

@Controller
@RequestMapping("*")
public class HomeController {

    @Autowired
    private DemoEntity demoEntity;

@Autowired
private EmployeesRepository employeesRepository;

    @GetMapping("")
    public String hello(){
        /*System.out.println("Kiem Tra " + demoEntity.getName() + "Kiem Tra "+ demoEntity.getId());*/
List<Employees> list = employeesRepository.findByEmployeeNameAndSalary("John Doe",60000);
        for (Employees data: list) {
            System.out.println("KiemTra "+ data.getEmployeeName());
        }
        return "greeting";
    }
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("username", "");
        return "greeting";
    }

    @PostMapping("/welcome")
    public String welcome(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "welcome";
    }

}
