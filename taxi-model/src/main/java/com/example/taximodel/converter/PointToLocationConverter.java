package com.example.taximodel.converter;


import com.example.taximodel.dto.request.LocationDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.geo.Point;
import org.springframework.lang.Nullable;

public class PointToLocationConverter implements Converter<Point, LocationDTO> {

    @Nullable
    @Override
    public LocationDTO convert(Point point) {
        if (point == null) {
            return null;
        }
        return new LocationDTO(point.getY(), point.getX(), null);
    }

}
