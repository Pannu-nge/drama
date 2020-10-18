package com.exercise.drama.drama.controller;

import com.exercise.drama.drama.model.Company;
import com.exercise.drama.drama.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public List<Company> getAll(){ return companyRepository.findAll(); }
    @GetMapping("{companyId}")
    public Company getByCompanyId(@PathVariable Long companyId){
        return companyRepository.getOne(companyId);
    }
    @PostMapping
    public Company save(@RequestBody Company company){
        return companyRepository.save(company);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Company update(@RequestBody Company company){
        Company oldCompany = companyRepository.getOne(company.getCompanyId());
        BeanUtils.copyProperties(company, oldCompany, "companyId");
        return companyRepository.saveAndFlush(oldCompany);
    }
    @RequestMapping(value = "{companyId}", method = RequestMethod.DELETE)
    public void deleteByCompanyId(@PathVariable Long companyId){
        companyRepository.deleteById(companyId);
    }
}
