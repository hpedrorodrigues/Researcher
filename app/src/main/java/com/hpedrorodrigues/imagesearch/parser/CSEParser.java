package com.hpedrorodrigues.imagesearch.parser;

import com.hpedrorodrigues.imagesearch.entity.Image;
import com.hpedrorodrigues.imagesearch.network.api.Api;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class CSEParser extends BaseParser {

    @Inject
    public CSEParser() {
    }

    @Override
    protected List<Map> getImages(Map data) {
        return (List<Map>) data.get("results");
    }

    @Override
    protected Image asImage(Map info) {
        Image image = new Image();

        image.setTitle(asString(info.get("titleNoFormatting")));
        image.setDescription(asString(info.get("contentNoFormatting")));
        image.setWidth(asInteger(info.get("width")));
        image.setHeight(asInteger(info.get("height")));
        image.setWebSiteUrl(asString(info.get("originalContextUrl")));
        image.setThumbnailUrl(asString(info.get("tbUrl")));
        image.setImageUrl(asString(info.get("unescapedUrl")));

        return image;
    }

    @Override
    protected boolean conditionToAdd(Map info) {
        return true;
    }

    @Override
    protected Api getApi() {
        return Api.CSE;
    }
}