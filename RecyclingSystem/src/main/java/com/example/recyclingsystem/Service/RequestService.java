package com.example.recyclingsystem.Service;

import com.example.recyclingsystem.Api.ApiException;
import com.example.recyclingsystem.Model.Company;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.RequestDetail;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Repository.CompanyRepository;
import com.example.recyclingsystem.Repository.RequestDetailRepository;
import com.example.recyclingsystem.Repository.RequestRepository;
import com.example.recyclingsystem.Repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final RequestDetailRepository requestDetailRepository;
    private final ResidentRepository residentRepository;
    private final CompanyRepository companyRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request> getUserRequests(Integer resident_id) {
        List<Request> requests = requestRepository.findRequestByUser_id(resident_id);
        if (requests == null || requests.isEmpty()) throw new ApiException("no requests yet");
        return requests;
    }

    public void addRequest(Request request) {
        Resident resident = residentRepository.findUserById(request.getResident_id());
        if (resident == null) throw new ApiException("something went wrong");
        requestRepository.save(request);
        resident.setPoints(resident.getPoints()+5);
        residentRepository.save(resident);
    }

    public void updateRequest(Integer request_id, Request request) {
        Request oldRequest = requestRepository.findRequestById(request_id);
        if (oldRequest == null) {
            throw new ApiException("this request not exist");
        }
        RequestDetail requestDetail = requestDetailRepository.findRequestDetailByRequest_code(oldRequest.getRequest_code());
        if (requestDetail == null) {
            throw new ApiException("no details to check availability to modify");
        }

        if (requestDetail.getStatus().equalsIgnoreCase("pending")) {
            oldRequest.setWeight(request.getWeight());
            oldRequest.setWaste_type(request.getWaste_type());
            requestRepository.save(oldRequest);
        }else throw new ApiException("you cannot modify confirmed request");
    }

    public void deleteRequest(Integer request_id) {
        Request oldRequest = requestRepository.findRequestById(request_id);
        if (oldRequest == null) {
            throw new ApiException("this request not exist");
        }
        RequestDetail requestDetail = requestDetailRepository.findRequestDetailByRequest_code(oldRequest.getRequest_code());
        if (requestDetail == null) {
            throw new ApiException("this request detail not exist");
        }

        if (requestDetail.getStatus().equalsIgnoreCase("pending")) {
            requestRepository.delete(oldRequest);
            requestDetailRepository.delete(requestDetail);
        }else throw new ApiException("you cannot delete confirmed request");

    }

    public void companyBuyRequest(Integer request_id, Integer company_id) {
        Request request = requestRepository.findRequestById(request_id);
        if (request == null) throw new ApiException("request not found");

        RequestDetail requestDetail = requestDetailRepository.findRequestDetailByRequest_code(request.getRequest_code());
        if (requestDetail == null) throw new ApiException("no details to check availability to purchase");

        Company company = companyRepository.findCompanyById(company_id);
        if (company == null) throw new ApiException("company not exist");

        if (requestDetail.getStatus().equalsIgnoreCase("pending")) {
            request.setCompany_id(company.getId());
            requestRepository.save(request);
            requestDetail.setStatus("confirmed");
            requestDetailRepository.save(requestDetail);
        }else throw new ApiException("this request not available to purchase");

    }

    public List<Request> getCompanyPurchases(Integer company_id) {
        List<Request> requests = requestRepository.findRequestByCompany_id(company_id);
        if (requests == null || requests.isEmpty()) throw new ApiException("no purchases yet");
        return requests;
    }



}
