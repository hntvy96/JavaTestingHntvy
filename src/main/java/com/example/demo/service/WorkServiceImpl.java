package com.example.demo.service;

import com.example.demo.constant.HTTPCodeResponse;
import com.example.demo.entity.WorkEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.model.Work;
import com.example.demo.repository.WorkRepository;
import com.example.demo.service.response.CodeResponse;
import com.example.demo.service.response.MessageResponse;
import com.example.demo.service.response.WorkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    WorkRepository workRepository;

    @Override
    public WorkResponse getData(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        WorkResponse workResponse = new WorkResponse();

        Page<WorkEntity> pagedResult = workRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            List<Work> works = convertListWorkEntityToListWorkModel(pagedResult.getContent());
            MessageResponse messageResponse = new MessageResponse(HTTPCodeResponse.SUCCESS,"Success");
            return new WorkResponse(works,messageResponse);
        } else {
            MessageResponse messageResponse = new MessageResponse(
                    HTTPCodeResponse.OBJECT_IS_EMPTY,"The list is empty");
            return new WorkResponse(new ArrayList<Work>(), messageResponse);
        }
    }

    @Override
    public WorkResponse createWorkData(Work work) {
        WorkResponse workResponse = new WorkResponse();
        WorkEntity workEntity = convertWorkModelToWorkEntity(work);
        workRepository.save(workEntity);
        List<Work> works = new ArrayList<>();
        works.add(convertWorkEntityToWorkModel(workEntity));

        return new WorkResponse(works,new MessageResponse(HTTPCodeResponse.SUCCESS, "success"));
    }

    @Override
    public CodeResponse deleteWorkData(int workId) throws DataNotFoundException {
        WorkEntity workEntity = workRepository.findWorkEntitiesById(workId);
        CodeResponse codeResponse = new CodeResponse();
        if(workEntity != null)
        {
            workRepository.deleteById(workId);
            codeResponse.setError(new MessageResponse(HTTPCodeResponse.SUCCESS,"Success"));
        }else {
            codeResponse.setError(new MessageResponse(HTTPCodeResponse.OBJECT_NOT_FOUND,"No work record exist for given id"));
        }

        return codeResponse;
    }

    @Override
    public CodeResponse updateWorkData(Work work) {
        CodeResponse codeResponse = new CodeResponse();
        WorkEntity workEntity = workRepository.findWorkEntitiesById(work.getId());
        if (workEntity == null){
            codeResponse.setError(new MessageResponse(HTTPCodeResponse.OBJECT_NOT_FOUND,"No work record exist for given id"));
        }else {
            workEntity.setWorkName(work.getWorkName());
            workEntity.setStartDate(work.getStartDate());
            workEntity.setEndDate(work.getEndDate());
            workEntity.setStatus(work.getStatus());
            workRepository.save(workEntity);
            codeResponse.setError(new MessageResponse(HTTPCodeResponse.SUCCESS,"Success"));
        }

        return codeResponse;
    }

    public static Work convertWorkEntityToWorkModel(WorkEntity workEntity){
        Work work = new Work();
        work.setId(workEntity.getId());
        work.setWorkName(workEntity.getWorkName());
        work.setStartDate(workEntity.getStartDate());
        work.setEndDate(workEntity.getEndDate());
        work.setStatus(workEntity.getStatus());
        
        return work;
    }

    public static WorkEntity convertWorkModelToWorkEntity(Work work){
       WorkEntity workEntity = new WorkEntity();
       workEntity.setId(work.getId());
       workEntity.setWorkName(work.getWorkName());
       workEntity.setStartDate(work.getStartDate());
       workEntity.setEndDate(work.getEndDate());
       workEntity.setStatus(work.getStatus());

       return workEntity;
    }
    
    public static List<Work> convertListWorkEntityToListWorkModel(List<WorkEntity> workEntityList){
        List<Work> workList = new ArrayList<Work>();

        for (WorkEntity workEntity : workEntityList) {
            Work work = convertWorkEntityToWorkModel(workEntity);
            workList.add(work);
        }

        return workList;
    }
}
