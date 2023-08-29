package com.example.recyclingsystem.Service;

import com.example.recyclingsystem.Api.ApiException;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Repository.RequestRepository;
import com.example.recyclingsystem.Repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final RequestRepository requestRepository;

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    public void signUp(Resident resident) {
        resident.setPoints(0);
        resident.setWallet(0.0);
        residentRepository.save(resident);
    }

    public void updateEmail(Integer resident_id, Resident resident) {
        Resident oldResident = residentRepository.findUserById(resident_id);
        if (oldResident == null) {
            throw new ApiException("something went wrong!");
        }
        oldResident.setEmail(resident.getEmail());
        residentRepository.save(oldResident);
    }

    public void deleteAccount(Integer resident_id) {
        Resident oldResident = residentRepository.findUserById(resident_id);
        if (oldResident == null) {
            throw new ApiException("something went wrong!");
        }
        residentRepository.delete(oldResident);
    }

    public void addMoneyToWallet(Integer resident_id) {
        Resident resident = residentRepository.findUserById(resident_id);
        if (resident == null) throw new ApiException("resident not exist");
        if (resident.getPoints() == 20) {
            resident.setWallet(resident.getWallet() + 100);
            resident.setPoints(0);
            residentRepository.save(resident);
        }else throw new ApiException("your points still less than 20");
    }

    public void getPrice(Integer resident_id) {
        Resident resident = residentRepository.findUserById(resident_id);
        if (resident == null) throw new ApiException("resident not exist");

        List<Request> requests = requestRepository.findRequestByUser_id(resident.getId());

        if (requests.size() >= 10) {
            resident.setWallet(resident.getWallet() + 50);
        } else if (requests.size() == 5) {
            resident.setPoints(resident.getPoints() + 5);
        }

        throw new ApiException("sorry, there is no price for you yet :(");
    }
}
