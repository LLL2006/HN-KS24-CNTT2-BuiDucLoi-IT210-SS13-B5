package com.re.ss13b5.controller;

import com.re.ss13b5.model.entity.Prescription;
import com.re.ss13b5.model.entity.PrescriptionDetail;
import com.re.ss13b5.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionRepository repo;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String kw, Model model) {
        model.addAttribute("list", (kw != null) ? repo.search(kw) : repo.findAll());
        return "prescription/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Prescription p = new Prescription();
        p.setCreatedDate(LocalDate.now());
        // Giả lập thêm 1 dòng detail trống để nhập liệu
        p.getDetails().add(new PrescriptionDetail());
        model.addAttribute("prescription", p);
        return "prescription/form";
    }

    @PostMapping("/save")
    public String save( @ModelAttribute Prescription p, BindingResult res) {
        if (res.hasErrors()) return "prescription/form";
        repo.save(p);
        return "redirect:/prescription/list";
    }
}
