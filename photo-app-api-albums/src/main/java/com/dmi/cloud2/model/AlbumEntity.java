package com.dmi.cloud2.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlbumEntity {
    private long id;
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
