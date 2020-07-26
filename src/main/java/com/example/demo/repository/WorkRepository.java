package com.example.demo.repository;

import com.example.demo.entity.WorkEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

public interface WorkRepository extends PagingAndSortingRepository<WorkEntity, Long> {

    WorkEntity findWorkEntitiesById(Integer id);
    @Transactional
    void deleteById(Integer id);
}
