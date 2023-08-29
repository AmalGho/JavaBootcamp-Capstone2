package com.example.recyclingsystem.Controller;

import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/getAll")
    public ResponseEntity getAllRequests() {
        return ResponseEntity.status(HttpStatus.OK).body(requestService.getAllRequests());
    }

    @GetMapping("/getUserRequests/{resident_id}")
    public ResponseEntity getUserRequests(@PathVariable Integer resident_id) {
        return ResponseEntity.status(HttpStatus.OK).body(requestService.getUserRequests(resident_id));
    }

    @PostMapping("/add")
    public ResponseEntity addRequest(@RequestBody @Valid Request request) {
        requestService.addRequest(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request added"));
    }

    @PutMapping("/update/{request_id}")
    public ResponseEntity updateRequest(@PathVariable Integer request_id, @RequestBody @Valid Request request) {
        requestService.updateRequest(request_id, request);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request updated"));
    }

    @DeleteMapping("/delete/{request_id}")
    public ResponseEntity deleteRequest(@PathVariable Integer request_id) {
        requestService.deleteRequest(request_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request deleted"));
    }

    @PutMapping("/buyRequest/{request_id}/{company_id}")
    public ResponseEntity companyBuyRequest(@PathVariable Integer request_id, @PathVariable Integer company_id){
        requestService.companyBuyRequest(request_id, company_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request purchased"));
    }

    @GetMapping("/getCompanyPurchases/{company_id}")
    public ResponseEntity getCompanyPurchases(@PathVariable Integer company_id) {
        return ResponseEntity.status(HttpStatus.OK).body(requestService.getCompanyPurchases(company_id));
    }
}
