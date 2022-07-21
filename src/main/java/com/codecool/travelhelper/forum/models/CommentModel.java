package com.codecool.travelhelper.forum.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentModel {
    private Long id;
    private int size;

    public CommentModel(Long id, int size) {
        this.id = id;
        this.size = size;
    }
}
