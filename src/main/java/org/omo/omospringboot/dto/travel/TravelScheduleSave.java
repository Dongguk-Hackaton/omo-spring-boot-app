package org.omo.omospringboot.dto.travel;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class TravelScheduleSave {
    @Getter
    @Setter
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Request{
        @NotNull(message = "여행 시작일이 필요합니다.")
        private LocalDate startDay; // 여행 시작일

        @NotNull(message = "여행 종료일이 필요합니다.")
        private LocalDate endDay; // 여행 종료일

        @NotNull(message = "여행할 도/광역시가 필요합니다.")
        private String province;

        @NotNull(message = "여행할 시/군이 필요합니다.")
        private String city;

        private Long friendId;

        @Valid
        @NotNull(message = "여행 일정이 하루 이상 필요합니다.")
        List<Itinerary> itineraries;
    }

    @Getter
    @Setter
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    // 하루 동안의 여행 일정
    public static class Itinerary{
        @NotNull(message = "여행 시작 시간이 필요합니다.")
        private LocalDateTime startTime;

        @NotNull(message = "여행 종료 시간이 필요합니다.")
        private LocalDateTime endTime;

        @Valid
        @NotNull(message = "방문장소가 하나 이상 필요합니다.")
        List<Visits> visits;
    }

    @Getter
    @Setter
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Visits {
        @NotNull(message = "방문장소에 머무는 시작 시간이 필요합니다.")
        private LocalDateTime startTime;

        @NotNull(message = "방문장소에 머무는 종료 시간이 필요합니다.")
        private LocalDateTime endTime;

        @NotNull(message = "타임블럭이 필요합니다.")
        private String timeBlockType;

        @NotNull(message = "장소에 대한 아이디가 필요합니다.")
        private Long placeId;
    }

    @Getter
    @Setter
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Response {
        private String message;

        public Response() {
            this.message = "여행 일정이 등록되었습니다.";
        }
    }
}
