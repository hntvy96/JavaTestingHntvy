package com.example.demo.controller;

import com.example.demo.exception.DataNotFoundException;
import com.example.demo.model.Work;
import com.example.demo.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping("/api")
public class WworkController {
    @Autowired
    private WorkService workService;

    /**
     * Get data
     */
    @GetMapping("/getData")
    public ResponseEntity getWorkData( @RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(workService.getData(pageNo, pageSize, sortBy), HttpStatus.OK);
    }

    /**
     * Add data
     */
    @PostMapping("/createData")
    public ResponseEntity createWorkData(@RequestBody Work work){
        return new ResponseEntity<>(workService.createWorkData(work), HttpStatus.OK);
    }

    /**
     * Edit data
     */
    @PutMapping("/updateData")
    public ResponseEntity updateWorkData(@RequestBody Work work){
        return new ResponseEntity<>(workService.updateWorkData(work), HttpStatus.OK);
    }

    /**
     * Delete data
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteWorkData(@RequestBody int workId) throws DataNotFoundException {
        return new ResponseEntity<>(workService.deleteWorkData(workId), HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity testData(){
        int id = 20;
        return new  ResponseEntity<>(id, HttpStatus.OK);
    }

}
