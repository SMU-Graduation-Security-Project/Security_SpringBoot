package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessages {
    ERROR_UNDEFINED("정의되지 않은 에러"),

    ERROR_MEMBER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    ERROR_MEMBER_NOT_FOUND_ENG("Not Found"),
    ERROR_MEMBER_EXIST("이미 있는 사용자 입니다."),
    ERROR_MEMBER_CREATE_FORM_HAS_NULL("회원가입의 항목중 비어있는 항목이 있습니다"),
    ERROR_MEMBER_LENGTH_LIMIT("회원가입 항목 성별 또는 전화번호의 길이가 옳지 않습니다."),
    ERROR_MEMBER_ID_DUPLICATE("이미 사용중인 아이디 입니다."),
    ERROR_MEMBER_EMAIL_DUPLICATE("이미 사용중인 이메일입니다."),
    ERROR_MEMBER_PHONENUMBER_DUPLICATE("이미 사용중인 전화번호입니다."),
    ERROR_MEMBER_ID_FORMAT("아이디는 영문자로 시작하고 영문자,숫자로 5~12자 이하로 구성되어야합니다."),
    ERROR_MEMBER_PASSWORD_FORMAT("비밀번호는 영문자, 특수문자를 포함한 8~20자로 구성되어야 합니다."),
    ERROR_MEMBER_PASSWORD("사용자의 비밀번호가 일치하지 않습니다."),
    ERROR_MEMBER_QUESTION_NOT_MATCH("사용자의 질문이 맞지 않습니다."),
    ERROR_MEMBER_ANSWORD_NOT_MATCH("사용자의 질문에 대한 대답이 맞지 않습니다."),

    ERROR_ACCOUNT_NOT_FOUND("사용자의 계좌를 찾을 수 없습니다."),
    ERROR_ACCOUNT_CURRING("이체를 하는 사람과 받는 사람이 동일합니다."),
    ERROR_ACCOUNT_PASSWORD_FORMAT("계좌비밀번호는 숫자로 6자리로 구성되어야 합니다."),
    ERROR_ACCOUNT_PASSWORD_NOT_MATCH("계좌비밀번호가 동일하지 않습니다."),
    ERROR_ACCOUNT_BALANCE("이체하기 충분한 잔액을 가지고 있지않습니다."),

    ERROR_COUNSEL_NOT_EXIST("해당 아이디를 가진 상담글이 없습니다"),
    ERROR_COUNSEL_UNAUTHORIZED_ACCESS("상담글에 접근할 권한이 없습니다."),

    ERROR_EVENT_EXIST("해당 제목을 가진 이벤트가 이미 있습니다."),
    ERROR_EVENT_NOT_EXIST("해당 아이디를 가진 이벤트가 없습니다"),

    ERROR_WINNER_EXIST("해당 이벤트 당첨자 리스트가 이미 있습니다."),
    ERROR_WINNER_NOT_EXIST("해당 이벤트 당첨자 리스트가 없습니다"),

    ERROR_NEWS_EXIST("해당 제목을 가진 새소식이 이미 있습니다."),
    ERROR_NEWS_NOT_EXIST("해당 아이디를 가진 새소식이 없습니다"),

    ERROR_CARD_EXIST("해당 카드번호를 가진 카드가 이미 있습니다."),
    ERROR_CARD_NOT_EXIST("해당 카드번호를 가진 카드가 없습니다"),

    ERROR_CARDLIST_EXIST("해당 제목을 가진 카드목록이 이미 있습니다."),
    ERROR_CARDLIST_NOT_EXIST("해당 아이디를 가진 카드목록이 없습니다"),

    ERROR_LOANLIST_EXIST("해당 제목을 가진 대출목록이 이미 있습니다."),
    ERROR_LOANLIST_NOT_EXIST("해당 아이디를 가진 대출목록이 없습니다"),

    ERROR_LOAN_EXIST("해당 대출이 이미 있습니다."),
    ERROR_LOAN_AMOUNT_EXCESS("대출 한도를 초과했습니다."),

    ERROR_OTP_EXIST("OTP를 이미 보유하고 있습니다."),
    ERROR_OTP_NOT_EXIST("OTP가 발급되어있지 않습니다."),
    ERROR_OTP_PK_NOT_MATCH("OTP PK값이 일치하지 않습니다."),
    ERROR_OTP_NOT_MATCH("입력한 OTP번호가 옳지 않습니다."),

    ERROR_SECURITYNOTICE_EXIST("해당 제목을 가진 보안공지가 이미 있습니다."),
    ERROR_SECURITYNOTICE_NOT_EXIST("해당 아이디를 가진 보안공지가 없습니다"),

    ERROR_JWT_NEED_LOGIN("다시 로그인 해주세요."),
    ERROR_JWT_REFRESH_TOKEN("REFRESH JWT 토큰이 잘못되었습니다."),
    ERROR_JWT_FORMAT("JWT 토큰이 잘못되었습니다."),
    ERROR_JWT_EXPIRED("JWT 토큰이 만료되었습니다."),
    ERROR_JWT_ACCESS_DENIED("접근이 거부되었습니다.");

    private final String message;
}
