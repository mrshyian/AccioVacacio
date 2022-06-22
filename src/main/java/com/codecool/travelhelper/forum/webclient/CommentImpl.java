package com.codecool.travelhelper.forum.webclient;

import com.codecool.travelhelper.forum.model.CommentsTable;
import com.codecool.travelhelper.forum.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentImpl {

    @Autowired
    CommentRepository commentRepository;

    public void getComments() {

        String comment = "Ta restauracja to jest jakieś fatalne przejęzyczenie werbalne z odwzorowaniem i konfabulacją w tle, normalnie szkoda gadać. A pozatym sądzę że Polimorfizm i enkapsulacja powinny być wykreślone z filarów OOP.";
        String commentImg = "https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE";
        String country = "Poland";
        String city = "Poznan";


        commentRepository.save(new CommentsTable(
                comment,
                commentImg,
                country,
                city
                )
        );
    }
//        String response = new ResponseEntity<List<CommentsTable>>(commentRepository.findAll(), HttpStatus.OK).getBody().toString();
//        System.out.println(response);
//
//        int begin = response.indexOf("(") + 1;
//        int end = response.lastIndexOf(")");
//        response = response.substring(begin, end);
//        String[] responseList = response.split(", ");
//
//        int i =0;
//        while(responseList[i+1] != null){
//            Long responseIdLong = Long.parseLong(responseList[0].split("=")[1]);
//            UUID responseId = UUID.fromString(responseList[1].split("=")[1]);
//            String responseText = (responseList[2].split("=")[1]);
//            String responseImg = (responseList[3].split("=")[1]);
//            String responseCountry = (responseList[4].split("=")[1]);
//            String responseCity = (responseList[5].split("=")[1]);
//            String responseDatetime = (responseList[6].split("=")[1]);
//            UUID responseUUID = UUID.fromString(responseList[7].split("=")[1]);
//            return CommentsTable.builder()
//                    .id(responseIdLong)
//                    .userId(responseId)
//                    .commentTex(String.valueOf(responseText))
//                    .commentImage(String.valueOf(responseImg))
//                    .country(String.valueOf(responseCountry))
//                    .city(String.valueOf(responseCity))
//                    .commentDateTime(responseDatetime)
//                    .likedByUser(responseUUID)
//                    .build();
//        }
//
//
//    }
//
//    public List<CommentsTable> getAllComments(){
//        List<CommentsTable> commentsList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            commentsList.add(getComments());
//        }
//        return commentsList;
//    }


}
