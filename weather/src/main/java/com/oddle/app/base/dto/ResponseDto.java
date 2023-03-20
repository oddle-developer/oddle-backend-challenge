package com.oddle.app.base.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDto<T> implements Serializable {

	private static final long serialVersionUID = -1882480478130059302L;

	private String code;
	private String message;
	private T data;

	public static <T> ResponseDto<T> ok(T data){
		return new ResponseDtoBuilder<T>().code("SUCCESS").data(data).build();
	}

	public static <T> ResponseDto<T> fail(String message){
		return new ResponseDtoBuilder<T>().code("FAIL").message(message).build();
	}
}
