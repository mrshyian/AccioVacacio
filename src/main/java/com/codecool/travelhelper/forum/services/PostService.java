package com.codecool.travelhelper.forum.services;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private  PostRepository postRepository;


    public List<PostTable> getUserPostsByComments(List<CommentsTable> likedComments){
        List<PostTable> posts= new ArrayList<>();
        for (CommentsTable comment: likedComments) {
            if(postRepository.findById(comment.getPost().getId()).isPresent()){
                if(!posts.contains(comment.getPost())){
                    posts.add(comment.getPost());
                }

            }
        }
        return posts;
    }

    public List<PostTable> findAll(){
        return this.postRepository.findAll();
    }

}
