package com.leo.wallet.exception;

import com.leo.wallet.model.CodeConstant;
import com.leo.wallet.model.StandardContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@ControllerAdvice
@RequestMapping(produces = "application/json")
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler({Exception.class})
    public StandardContext defaultExceptionHandler(Exception e) {
        if (e instanceof WalletException) {
            return new StandardContext(((WalletException) e).getMessageCode(), e.getMessage());
        }
        if (e instanceof HttpMessageNotReadableException) {
            return new StandardContext(CodeConstant.GLOBAL_HTTP_MESSAGE_NOT_READABLE, e.getMessage());
        }

        if (e instanceof MissingServletRequestParameterException) {
            return new StandardContext(CodeConstant.GLOBAL_HTTP_MISSING_REQUEST_PARAMETER, e.getMessage());
        }

        if (e instanceof NoHandlerFoundException) {
            return new StandardContext(CodeConstant.GLOBAL_URL_NOT_FOUND, CodeConstant.GLOBAL_URL_NOT_FOUND_MESSAGE);
        }
        if (e instanceof MethodArgumentTypeMismatchException) {
            return new StandardContext(CodeConstant.GLOBAL_HTTP_MISSING_ARGUMENT_TYPE_PARAMETER, CodeConstant.GLOBAL_HTTP_MISSING_ARGUMENT_TYPE_PARAMETER_MESSAGE);
        }
        e.printStackTrace();
        return new StandardContext(CodeConstant.INTERNAL_ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardContext validationError(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        return new StandardContext(CodeConstant.GLOBAL_HTTP_MISSING_REQUEST_PARAMETER, fieldErrors.get(0).getDefaultMessage());
    }

}
