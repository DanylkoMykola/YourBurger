package com.danylko.yourburger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class YourBurgerExceptionHandler extends ResponseEntityExceptionHandler {

    private final static String ERROR_VIEW = "inform/error";
    private final static String ADMIN_ERROR_VIEW = "inform/admin-error";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MailSendException.class)
    public ModelAndView mailSendExceptionHandler(HttpServletRequest req, MailSendException e) throws MailSendException {
        return getModelWithParam(ERROR_VIEW, req, e);
    }

    @ExceptionHandler(value = StorageException.class)
    public ModelAndView storageExceptionHandler(HttpServletRequest req, StorageException e) throws StorageException {
        return getModelWithParam(ADMIN_ERROR_VIEW, req, e);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return getModelWithParam(ERROR_VIEW, req, e);
    }


    private ModelAndView getModelWithParam(String errorPage, HttpServletRequest req, Exception e) {
        ModelAndView model = new ModelAndView(errorPage);
        model.addObject("exception", e);
        model.addObject("url", req.getRequestURL());
        logger.info("YourBurgerExceptionHandler handled Exception: " + e);
        e.printStackTrace();
        return model;
    }
}
