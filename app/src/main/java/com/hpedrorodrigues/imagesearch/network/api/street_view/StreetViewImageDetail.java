package com.hpedrorodrigues.imagesearch.network.api.street_view;

import lombok.Data;

@Data
public class StreetViewImageDetail {

    private Integer width;

    private Integer height;

    private Integer scale;

    private Double latitude;

    private Double longitude;

    private Double heading;

    private Double pitch;
}