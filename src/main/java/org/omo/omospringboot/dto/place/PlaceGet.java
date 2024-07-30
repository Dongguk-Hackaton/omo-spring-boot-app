package org.omo.omospringboot.dto.place;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.omo.omospringboot.constant.PlaceType;
import org.omo.omospringboot.entity.place.Place;

import java.time.LocalDateTime;

public class PlaceGet {
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Response {
        private Long id;

        private String name;

        private String address;

        private Double latitude;

        private Double longitude;

        private String overview;

        private String contactNumber; // 문의 및 안내

        private PlaceType placeType;
        
        private CultureFacility cultureFacility;
        
        private Leisure leisure;
        
        private TouristSpot touristSpot;
        
        private UniqueExperience uniqueExperience;

        private Restaurant restaurant;

        private static ResponseBuilder getResponseBuilder(Place place) {
            return Response.builder()
                    .id(place.getId())
                    .name(place.getName())
                    .address(place.getAddress())
                    .latitude(place.getLatitude())
                    .longitude(place.getLongitude())
                    .overview(place.getOverview())
                    .contactNumber(place.getContactNumber());
        }

        public static Response fromEntity(org.omo.omospringboot.entity.place.CultureFacility cultureFacility) {
            return getResponseBuilder(cultureFacility)
                    .placeType(PlaceType.CULTURE_FACILITY)
                    .cultureFacility(CultureFacility.fromEntity(cultureFacility))
                    .build();
        }

        public static Response fromEntity(org.omo.omospringboot.entity.place.Leisure leisure) {
            return getResponseBuilder(leisure)
                    .placeType(PlaceType.LEISURE)
                    .leisure(Leisure.fromEntity(leisure))
                    .build();
        }

        public static Response fromEntity(org.omo.omospringboot.entity.place.TouristSpot touristSpot) {
            return getResponseBuilder(touristSpot)
                    .placeType(PlaceType.TOURIST_SPOT)
                    .touristSpot(TouristSpot.fromEntity(touristSpot))
                    .build();
        }

        public static Response fromEntity(org.omo.omospringboot.entity.place.UniqueExperience uniqueExperience) {
            return getResponseBuilder(uniqueExperience)
                    .placeType(PlaceType.UNIQUE_EXPERIENCE)
                    .uniqueExperience(UniqueExperience.fromEntity(uniqueExperience))
                    .build();
        }

        public static Response fromEntity(org.omo.omospringboot.entity.place.Restaurant restaurant) {
            return getResponseBuilder(restaurant)
                    .placeType(PlaceType.RESTAURANT)
                    .restaurant(Restaurant.fromEntity(restaurant))
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CultureFacility{
        private String fee;

        private String discountInfo;

        private String pet;

        private String detailInformation; // 상세 정보

        private String parking;

        public static CultureFacility fromEntity(org.omo.omospringboot.entity.place.CultureFacility cultureFacility) {
            return CultureFacility.builder()
                    .fee(cultureFacility.getFee())
                    .discountInfo(cultureFacility.getDiscountInfo())
                    .pet(cultureFacility.getPet())
                    .detailInformation(cultureFacility.getDetailInformation())
                    .parking(cultureFacility.getParking())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Leisure {
        private String duration; // 개장기간

        private String fee;

        private String pet;

        private String detailInformation;

        @Column(nullable = false)
        private String parking;

        public static Leisure fromEntity(org.omo.omospringboot.entity.place.Leisure leisure) {
            return Leisure.builder()
                    .duration(leisure.getFee())
                    .fee(leisure.getFee())
                    .pet(leisure.getPet())
                    .detailInformation(leisure.getDetailInformation())
                    .parking(leisure.getParking())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class TouristSpot {
        private LocalDateTime startDay;

        private String experienceInformation;

        private String pet;

        private String detailInformation;

        private String parking;

        public static TouristSpot fromEntity(org.omo.omospringboot.entity.place.TouristSpot touristSpot) {
            return TouristSpot.builder()
                    .startDay(touristSpot.getStartDay())
                    .pet(touristSpot.getPet())
                    .detailInformation(touristSpot.getDetailInformation())
                    .parking(touristSpot.getParking())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UniqueExperience{
        private String type;

        public static UniqueExperience fromEntity(org.omo.omospringboot.entity.place.UniqueExperience uniqueExperience) {
            return UniqueExperience.builder()
                    .type(uniqueExperience.getType())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Restaurant{
        private String firstMenu;

        private String subMenu;

        private String reservation;

        private String parking;

        public static Restaurant fromEntity(org.omo.omospringboot.entity.place.Restaurant restaurant) {
            return Restaurant.builder()
                    .firstMenu(restaurant.getFirstMenu())
                    .subMenu(restaurant.getSubMenu())
                    .reservation(restaurant.getReservation())
                    .parking(restaurant.getParking())
                    .build();
        }
    }
}