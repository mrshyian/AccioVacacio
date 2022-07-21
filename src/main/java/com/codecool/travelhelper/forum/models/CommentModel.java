package com.codecool.travelhelper.forum.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentModel {
    private Long id;
    private String topic;

    public CommentModel(Long id, String topic) {
        this.id = id;
        this.topic = topic;
    }
}
