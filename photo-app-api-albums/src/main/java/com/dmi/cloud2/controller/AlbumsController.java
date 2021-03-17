package com.dmi.cloud2.controller;

import com.dmi.cloud2.model.AlbumEntity;
import com.dmi.cloud2.model.AlbumResponseModel;
import com.dmi.cloud2.service.AlbumsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/{id}/albums")
@Slf4j
public class AlbumsController {

    private final AlbumsService albumsService;
    private final ModelMapper modelMapper;

    @Autowired
    public AlbumsController(AlbumsService albumsService, ModelMapper modelMapper) {
        this.albumsService = albumsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            })
    public List<AlbumResponseModel> userAlbums(@PathVariable String id) {

        List<AlbumResponseModel> returnValue = new ArrayList<>();

        List<AlbumEntity> albumsEntities = albumsService.getAlbums(id);

        if(albumsEntities == null || albumsEntities.isEmpty())
        {
            return returnValue;
        }

        Type listType = new TypeToken<List<AlbumResponseModel>>(){}.getType();

        returnValue = modelMapper.map(albumsEntities, listType);
        log.info("Returning " + returnValue.size() + " albums");
        return returnValue;
    }

}
