package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.security.details.PersonalDetailsImpl;
import com.epam.lab.hospitalspring.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * The controller asks all employees of the EPAM hospital
 * and returns them as part of a web page with a complete list or just filtered ones.
 */
@Controller
public class PersonalController {
    private String filter = null;
    @Autowired
    PersonalService personalService;

    @GetMapping("/personals")
    public String getPersonals(@PageableDefault(size = 5) Pageable pageable,
                               Model model, Authentication authentication) {
        Page<Personal> page = null;
        if (filter == null || filter == "") {
            page = personalService.findAll(pageable);

        } else {
            page = personalService.newFinder(filter, pageable);
        }
        Long totalElements = page.getTotalElements();
        model.addAttribute("page", page);
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        model.addAttribute("currentRole", personalDetailsService.getPersonal().getRole());
        model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
        model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
        model.addAttribute("totalElements", totalElements);
        return "personals";
    }

    /**
     * Find employees by filter
     *
     * The method takes value to search
     *
     * @param model
     * @param authentication @see Authentication - registered employee details source
     * @param search a value to search
     * @param pageable @see Pagable
     * @return web page of employees list
     */
    @PostMapping("/personals")
    public String search(Model model, Authentication authentication, String search, @PageableDefault(size = 5) Pageable pageable) {
        filter = search;
        Page<Personal> page = personalService.newFinder(search, pageable);
        Long totalElements = page.getTotalElements();
        model.addAttribute("page", page);
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        model.addAttribute("currentRole", personalDetailsService.getPersonal().getRole());
        model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
        model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
        model.addAttribute("totalElements", totalElements);
        return "personals";

    }

    @GetMapping("/personals/{id}")
    public String getPersonalPage(Personal personal, @PathVariable("id") Long id, Model model) {
        model.addAttribute("personal", personalService.getById(id));
        return "/personal";
    }

    @PutMapping("/personals/{id}")
    public String updatePersonal(@Valid Personal personal, BindingResult bindingResult,
                                 @PathVariable("id") Long id, PersonalForm personalForm) {
        if (bindingResult.hasErrors()) {
            return "/personal";
        }
        if (personalService.update(personalForm, id)) {
            return "redirect:/personals";
        } else {
            return "redirect:/personals/" + id + "?loginAleadyUsed";
        }
    }

    /**
     * Method delete
     *
     * The method permanently deletes the employee from the database
     *
     * @param id identificator of current employee
     * @return web page of employees list
     */
    @DeleteMapping("/personals/{id}")
    public String delete(@PathVariable("id") Long id) {
        personalService.delete(id);
        return "redirect:/personals";
    }
}
