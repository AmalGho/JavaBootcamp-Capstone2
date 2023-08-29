package com.example.recyclingsystem.Repository;

import com.example.recyclingsystem.Model.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDetailRepository extends JpaRepository<RequestDetail, Integer> {
    RequestDetail findRequestDetailById(Integer id);

    @Query("select r from RequestDetail r where r.request_code=?1")
    RequestDetail findRequestDetailByRequest_code(String request_code);

    @Query("select r from RequestDetail r where r.request_code=?1 and r.status=\"pending\"")
    RequestDetail findRequestDetailsByStatus(String request_code);

    @Query("select r from RequestDetail r where r.request_code=?1 and r.status=\"confirmed\"")
    RequestDetail findRequestDetailsByConfirmedStatus(String request_code);
}
