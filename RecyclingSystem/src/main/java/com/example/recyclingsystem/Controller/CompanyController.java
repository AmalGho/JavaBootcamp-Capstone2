package com.example.recyclingsystem.Controller;


import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.Model.Company;
import com.example.recyclingsystem.Service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/getAll")
    public ResponseEntity getAllCompanies() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies());
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid Company company) {
        companyService.signUp(company);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("account created successfully"));
    }

    @PutMapping("/updateEmail/{id}")
    public ResponseEntity updateEmail(@PathVariable Integer id, @RequestBody @Valid Company company) {
        companyService.updateEmail(id, company);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("email updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAccount(@PathVariable Integer id) {
        companyService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("account deleted successfully"));
    }
}
