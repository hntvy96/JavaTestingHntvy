package com.example.demo.service;

import com.example.demo.exception.DataNotFoundException;
import com.example.demo.model.Work;
import com.example.demo.service.response.CodeResponse;
import com.example.demo.service.response.WorkResponse;

public interface WorkService {
    WorkResponse getData(Integer pageNo, Integer pageSize, String sortBy);
    WorkResponse createWorkData(Work work);
    CodeResponse deleteWorkData(int workId) throws DataNotFoundException;
    CodeResponse updateWorkData(Work work);
}
