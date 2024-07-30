package org.omo.omospringboot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // ----- Common ------
    NotValidRequestError(
            HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."
    ),
    QueryParamTypeMismatchError(
            HttpStatus.BAD_REQUEST, "해당 쿼리 파라미터의 타입이 올바르지 않습니다."
    ),
    MissingQueryParamError(
            HttpStatus.BAD_REQUEST, "해당 파라미터의 값이 존재하지 않습니다.."
    ),
    AccessDeniedError(
            HttpStatus.FORBIDDEN, "접근할 수 없는 권한을 가진 사용자입니다."
    ),
    InternalServerError(
            HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생하였습니다. 문제가 지속되면 관리자에게 문의하세요."
    ),

    // ----- User ------
    DuplicatedNicknameError(
            HttpStatus.CONFLICT, "중복된 닉네임입니다"
    ),
    UserNotFoundError(
            HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다."
    ),
    UserPermissionDeniedError(
            HttpStatus.FORBIDDEN, "권한이 없는 유저입니다."
    ),
    AlreadyExistUserError(
            HttpStatus.CONFLICT, "이미 존재하는 유저입니다."
    ),

    // ----- Kakao ------
    UnauthorizedKakaoError(
            HttpStatus.UNAUTHORIZED, "카카오를 통한 인증에 실패하였습니다."
    ),

    ForbiddenKakaoError(
            HttpStatus.FORBIDDEN, "허가되지 않은 카카오 접근입니다."
    ),

    // ----- Token ------
    NotValidAccessTokenError(
            HttpStatus.UNAUTHORIZED, "유효하지 않은 AccessToken입니다."
    ),
    NotExpiredAccessTokenError(
            HttpStatus.UNAUTHORIZED, "만료되지 않은 AccessToken입니다."
    ),
    ExpiredAccessTokenError(
            HttpStatus.UNAUTHORIZED, "만료된 AccessToken입니다."
    ),
    NoSuchAccessTokenError(
            HttpStatus.UNAUTHORIZED, "존재하지 않은 AccessToken입니다."
    ),
    NotValidRefreshTokenError(
            HttpStatus.UNAUTHORIZED, "유효하지 않은 RefreshToken입니다."
    ),
    NotExpiredRefreshTokenError(
            HttpStatus.UNAUTHORIZED, "만료되지 않은 RefreshToken입니다."
    ),
    ExpiredRefreshTokenError(
            HttpStatus.UNAUTHORIZED, "만료된 RefreshToken입니다."
    ),
    NoSuchRefreshTokenError(
            HttpStatus.UNAUTHORIZED, "존재하지 않은 RefreshToken입니다."
    ),

    // ----- Taste ------
    NotExistInterestCodeError(
            HttpStatus.NOT_FOUND, "존재하지 않는 관심사입니다."
    ),
    AlreadyExistTasteProfileError(
            HttpStatus.CONFLICT, "이미 사용자의 취향 프로필이 존재합니다."
    ),

    TasteProfileNotFoundError(
            HttpStatus.NOT_FOUND, "사용자의 취향 프로필이 존재하지 않습니다."
    ),
    NoSuchFoodTypeError(
            HttpStatus.NOT_FOUND, "해당 음식은 존재하지 않습니다."
    ),
    NoSuchInterestTypeError(
            HttpStatus.NOT_FOUND, "해당 관심사는 존재하지 않습니다."
    ),
    NoSuchTimeBlockError(
            HttpStatus.NOT_FOUND, "해당 타임블럭은 존재하지 않습니다."
    ),
    NoSuchDateStyleTypeError(
            HttpStatus.NOT_FOUND, "해당 데이트 스타일은 존재하지 않습니다."
    ),

    // ----- Travel ------
    NoSuchProvinceTypeError(
            HttpStatus.NOT_FOUND, "해당 광역시/도는 존재하지 않습니다."
    ),
    NoSuchCityTypeError(
            HttpStatus.NOT_FOUND, "해당 시/군은 존재하지 않습니다."
    ),
    InvalidTravelTimeError(
            HttpStatus.BAD_REQUEST, "여행에 대한 시간이 유효하지 않습니다."
    ),
    MismatchedItineraryWithDurationError(
            HttpStatus.BAD_REQUEST, "여행 기간과 여행일정의 수가 일치하지 않습니다."
    ),
    NoSuchPlaceError(
            HttpStatus.NOT_FOUND, "존재하지 않은 장소입니다."
    ),
    FriendNotFoundError(
            HttpStatus.NOT_FOUND, "친구를 찾을 수 없습니다."
    ),
    InvalidItineraryTimeError(
            HttpStatus.BAD_REQUEST, "여행 일정에 대한 시간이 유효하지 않습니다."
    ),
    InvalidVisitTimeError(
            HttpStatus.BAD_REQUEST, "여행 장소 방문 시간이 유효하지 않습니다."
    ),
    TooShortVisitDurationError(
            HttpStatus.BAD_REQUEST, "여행 방문 장소에 머무는 시간이 너무 짧습니다."
    ),
    NoClosestTravelScheduleError(
            HttpStatus.NOT_FOUND, "가장 가까운 여행일정이 없습니다."
    ),
    NoSuchPlaceTypeError(
            HttpStatus.NOT_FOUND, "존재하지 않은 점포 유형입니다."
    );

    private final HttpStatus httpStatus;
    private final String message;

}