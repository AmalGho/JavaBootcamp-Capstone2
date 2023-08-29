package com.example.recyclingsystem.Service;

import com.example.recyclingsystem.Api.ApiException;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.RequestDetail;
import com.example.recyclingsystem.Repository.RequestDetailRepository;
import com.example.recyclingsystem.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestDetailService {
    private final RequestDetailRepository requestDetailRepository;
    private final RequestRepository requestRepository;

    public List<RequestDetail> getAllRequestDetail() {
        return requestDetailRepository.findAll();
    }

    public RequestDetail getRequestDetail(Integer request_id){
        Request request = requestRepository.findRequestById(request_id);
        if (request == null) throw new ApiException("not exist");
        RequestDetail requestDetail = requestDetailRepository.findRequestDetailByRequest_code(request.getRequest_code());
        if (requestDetail == null) throw new ApiException("no details");
        return requestDetail;
    }

    public void addRequestDetail(Integer request_id, RequestDetail requestDetail){
        Request request = requestRepository.findRequestById(request_id);
        if (request == null) throw new ApiException("no such this request");

        if (request.getRequest_code().equalsIgnoreCase(requestDetail.getRequest_code())) {
            requestDetailRepository.save(requestDetail);
        }else throw new ApiException("request not exist to add its detail.");

    }

    public void deleteRequestDetail(Integer detail_id) {
        RequestDetail requestDetail = requestDetailRepository.findRequestDetailById(detail_id);
        if (requestDetail == null) throw new ApiException("no requests detail to delete");
        requestDetailRepository.delete(requestDetail);
    }

}
