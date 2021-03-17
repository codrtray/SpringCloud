package com.dmi.cloud2.service;

import com.dmi.cloud2.model.AlbumEntity;

import java.util.List;

public interface AlbumsService {
    List<AlbumEntity> getAlbums(String userId);
}
