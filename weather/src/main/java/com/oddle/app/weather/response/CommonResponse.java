package com.oddle.app.weather.response;

import com.oddle.app.weather.component.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {
    private String date;
    private String message;
    private T data;

    public void successResponse(String message, T data){
        this.date = Utils.dateLongFormat(new Date());
        this.message=message;
        this.data=data;
    }

    public void failedResponse(String message){
        this.date = Utils.dateLongFormat(new Date());
        this.message=message;
    }
}
