package com.example.recyclingsystem.Service;

import com.example.recyclingsystem.Api.ApiException;
import com.example.recyclingsystem.Model.Company;
import com.example.recyclingsystem.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public void signUp(Company company) {
        companyRepository.save(company);
    }

    public void updateEmail(Integer id, Company company) {
        Company oldCompany = companyRepository.findCompanyById(id);
        if (oldCompany == null) {
            throw new ApiException("something went wrong!");
        }
        oldCompany.setEmail(company.getEmail());
        companyRepository.save(oldCompany);
    }

    public void deleteAccount(Integer id) {
        Company company = companyRepository.findCompanyById(id);
        if (company == null) {
            throw new ApiException("something went wrong!");
        }
        companyRepository.delete(company);
    }
}
