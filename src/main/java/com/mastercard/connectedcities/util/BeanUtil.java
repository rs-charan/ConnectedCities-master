package com.mastercard.connectedcities.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext appContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    public static <T> T getClass(Class<T> clazz) {
        return appContext.getBean(clazz);
    }
}
