package com.oddle.app.weather.api.handler;

import com.oddle.app.weather.api.exception.ErrorApp;
import com.oddle.app.weather.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.remoting.RemoteConnectFailureException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import static com.oddle.app.weather.dto.ResponseDTO.ERROR;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    ResponseDTO handleAllException(Exception ex) {
        String logErrorMessage = ex.getMessage();
        ResponseDTO errorObject;

        if(ex instanceof ErrorApp){
            ErrorApp errorData = (ErrorApp) ex;

            int code = Integer.parseInt(errorData.getCode());
            errorObject = ERROR(code, errorData.getMessage());
        }else
            errorObject = ERROR(500, ex.getMessage());

        return errorObject;
    }
}
