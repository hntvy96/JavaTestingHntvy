package com.example.demo.service.response;

import com.example.demo.model.Work;

import java.util.List;

public class WorkResponse {
    private List<Work> works;
    private MessageResponse messageResponse;

    public WorkResponse(List<Work> works, MessageResponse messageResponse) {
        this.works = works;
        this.messageResponse = messageResponse;
    }

    public WorkResponse() {
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }
}
