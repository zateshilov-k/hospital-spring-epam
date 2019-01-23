package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees")
    public String getEmployees(@PageableDefault(size = 5) Pageable pageable,
                               Model model) {
        Page<Personal> page = employeeService.getAll(pageable);
        model.addAttribute("page", page);
        return "employee-page";
    }


//    @GetMapping("/employees")
//    public String getEmployees(@PageableDefault(size = 5, page = 0) Pageable pageable,
//                               Model model) {
//
////        pageable = PageRequest.of(pageable.getPageNumber(), 5, Sort.by("id").ascending());
////        Page<Post> page = postService.getPostsPage(pageable);
//
//        Page<Personal> page = employeeService.getAll(pageable);
//        model.addAttribute("page", page);
//        return "employee-page";
//    }
//
//    @RequestMapping
//    public String showPersonals(Model model, Pageable pageable) {
//
//        model.addAttribute("personals", employeeService.getAll(pageable));
//        return "employee-page";
//    }

//    @RequestMapping(value = "personalsPage", params = {"page", "size"}, method = GET)
//    @ResponseBody
//    public String findPaginated(
//            @RequestParam("page") int pageNumber, @RequestParam("size") int size,
//            Model model, Pageable pageable) {
//        Page<Personal> page = employeeService.getAll(new PageRequest(pageNumber, size));
//
////
////        Page<Personal> resultPage =employeeService.getAll(pageable);
////        if (pageNumber > resultPage.getTotalPages()) {
////            throw new ResourceNotFoundException();
////        }
////        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Personal>
////                (Personal.class, uriBuilder, response, page, resultPage.getTotalPages(), size));
////
////        return resultPage.getContent();
//        model.addAttribute("page", page);
//        return "employee-page";
//
//    }
}

