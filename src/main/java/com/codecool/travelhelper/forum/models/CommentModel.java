package com.codecool.travelhelper.forum.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentModel {
    private Long id;

    public CommentModel(Long id) {
        this.id = id;
    }
}
