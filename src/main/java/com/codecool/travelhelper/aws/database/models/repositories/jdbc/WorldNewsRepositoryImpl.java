package com.codecool.travelhelper.aws.database.models.repositories.jdbc;

import com.codecool.travelhelper.aws.database.models.repositories.WorldNewsRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
public class WorldNewsRepositoryImpl {

    @Autowired
    WorldNewsRepository worldNewsRepository;


}
