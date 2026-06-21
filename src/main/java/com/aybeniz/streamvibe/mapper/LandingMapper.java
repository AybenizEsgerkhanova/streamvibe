package com.aybeniz.streamvibe.mapper;

import com.aybeniz.streamvibe.dto.response.DeviceResponse;
import com.aybeniz.streamvibe.dto.response.FaqResponse;
import com.aybeniz.streamvibe.dto.response.GenreResponse;
import com.aybeniz.streamvibe.dto.response.PlanResponse;
import com.aybeniz.streamvibe.entity.*;
import org.springframework.stereotype.Component;

@Component
public class LandingMapper {

    public GenreResponse toGenreResponse(Genre genre) {
        return new GenreResponse(
                genre.getId(),
                genre.getName(),
                genre.getSlug(),
                genre.getType(),
                genre.getCoverImage1(),
                genre.getCoverImage2(),
                genre.getCoverImage3(),
                genre.getCoverImage4()
        );
    }

    public FaqResponse toFaqResponse(Faq faq) {
        return new FaqResponse(
                faq.getId(),
                faq.getQuestion(),
                faq.getAnswer(),
                faq.getOrderNumber()
        );
    }

    public PlanResponse toPlanResponse(PricingPlan plan, boolean yearly) {
        return new PlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                yearly ? plan.getPriceYearly() : plan.getPriceMonthly(),
                plan.getIsPopular()
        );
    }

    public DeviceResponse toDeviceResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getName(),
                device.getDescription(),
                device.getIconName()
        );
    }
}