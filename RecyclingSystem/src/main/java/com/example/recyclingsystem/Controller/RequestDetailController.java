package com.example.recyclingsystem.Controller;


import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.Model.RequestDetail;
import com.example.recyclingsystem.Service.RequestDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/detail")
public class RequestDetailController {

    private final RequestDetailService requestDetailService;

    @GetMapping("/getAll")
    public ResponseEntity getAllRequestsDetail() {
        return ResponseEntity.status(HttpStatus.OK).body(requestDetailService.getAllRequestDetail());
    }

    @GetMapping("/getDetail/{request_id}")
    public ResponseEntity getRequestDetail(@PathVariable Integer request_id) {
        return ResponseEntity.status(HttpStatus.OK).body(requestDetailService.getRequestDetail(request_id));
    }

    @PostMapping("/add/{request_id}")
    public ResponseEntity addRequestDetail(@PathVariable Integer request_id, @RequestBody @Valid RequestDetail requestDetail) {
        requestDetailService.addRequestDetail(request_id, requestDetail);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request detail Added"));
    }

    @DeleteMapping("/delete/{detail_id}")
    public ResponseEntity deleteDetails(@PathVariable Integer detail_id) {
        requestDetailService.deleteRequestDetail(detail_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("detail deleted"));
    }

}
