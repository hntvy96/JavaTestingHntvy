package com.example.demo.entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work")
public class WorkEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "work_name")
    private String workName;

    @Basic
    @Column(name = "start_date")
    private Date startDate;

    @Basic
    @Column(name = "end_date")
    private Date endDate;

    @Basic
    @Column(name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
