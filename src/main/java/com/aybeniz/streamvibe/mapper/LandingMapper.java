package com.aybeniz.streamvibe.mapper;

import com.aybeniz.streamvibe.dto.response.*;
import com.aybeniz.streamvibe.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public PlanResponse toPlanResponse(
            PricingPlan plan,
            boolean yearly,
            List<PlanFeatureResponse> features
    ) {
        return new PlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                yearly ? plan.getPriceYearly() : plan.getPriceMonthly(),
                plan.getIsPopular(),
                yearly ? "yearly" : "monthly",
                features
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