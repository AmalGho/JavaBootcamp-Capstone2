package com.example.recyclingsystem.Repository;

import com.example.recyclingsystem.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findRequestById(Integer id);


    @Query("select r from Request r where r.resident_id=?1")
    List<Request> findRequestByUser_id(Integer user_id);

    @Query("select r from Request r where r.company_id=?1")
    List<Request> findRequestByCompany_id(Integer company_id);
}
