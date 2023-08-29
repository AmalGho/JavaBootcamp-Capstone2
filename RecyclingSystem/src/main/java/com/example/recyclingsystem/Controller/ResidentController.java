package com.example.recyclingsystem.Controller;

import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Service.ResidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class ResidentController {
    private final ResidentService residentService;

    @GetMapping("/getAll")
    public ResponseEntity getAllResident() {
        return ResponseEntity.status(HttpStatus.OK).body(residentService.getAllResidents());
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid Resident resident) {
        residentService.signUp(resident);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("account created successfully"));
    }

    @PutMapping("/updateEmail/{id}")
    public ResponseEntity updateEmail(@PathVariable Integer id, @RequestBody @Valid Resident resident) {
        residentService.updateEmail(id, resident);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("email updated successfully"));
    }

    @DeleteMapping("/delete/{resident_id}")
    public ResponseEntity deleteAccount(@PathVariable Integer resident_id) {
        residentService.deleteAccount(resident_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("account deleted successfully"));
    }

    @PutMapping("/wallet/{resident_id}")
    public ResponseEntity addMoneyToWallet(@PathVariable Integer resident_id) {
        residentService.addMoneyToWallet(resident_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("money added to your wallet"));
    }

    @PutMapping("/price/{resident_id}")
    public ResponseEntity getPrice(@PathVariable Integer resident_id) {
        residentService.getPrice(resident_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("congrats, you get price"));
    }
}
