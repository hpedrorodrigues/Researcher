package com.hpedrorodrigues.imagesearch.api.parser;

import com.hpedrorodrigues.imagesearch.api.entity.Image;
import com.hpedrorodrigues.imagesearch.api.network.api.Api;
import com.hpedrorodrigues.imagesearch.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseParser {

    protected abstract List<Map> getImages(Map data);

    protected abstract Image asImage(Map info);

    protected abstract boolean conditionToAdd(Map info);

    protected abstract Api getApi();

    protected String asString(Object object) {
        String value = String.valueOf(object);
        return isInvalid(value) ? null : value;
    }

    protected Double asDouble(Object object) {
        String value = asString(object);
        return isInvalid(value) ? null : Double.valueOf(value);
    }

    protected Integer asInteger(Object object) {
        Double value = asDouble(object);
        return isInvalid(value) ? null : value.intValue();
    }

    private boolean isInvalid(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof String) {

            String value = String.valueOf(object);
            return StringUtil.isEmpty(value) || value.equals("null");
        } else {

            return false;
        }
    }

    public List<Image> parse(Map data) {
        List<Image> images = new ArrayList<>();

        for (Map info : getImages(data)) {

            if (conditionToAdd(info)) {
                Image image = asImage(info);
                image.setApi(getApi());

                images.add(image);
            }
        }

        return images;
    }
}