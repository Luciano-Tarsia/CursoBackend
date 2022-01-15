package com.coderhouse.observerpattern.services;

import com.coderhouse.observerpattern.utils.ConfigObserver;
import com.coderhouse.observerpattern.utils.ConfigType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConfigConcrete implements InitializingBean, ConfigObserver {

    private static final Logger logger = LoggerFactory.getLogger(UserConfigConcrete.class);

    @Autowired
    ConfigSubject configSubject;
    @Autowired
    ConfigService configService;

    private int id;
    private String name;
    private String rol;
    private String email;
    private String phone;

    @Override
    public void updateConfig(Object event) {
        logger.info("Actualiza el config");
        if (event.equals(ConfigType.USER)) {
            this.rol = configService.getUserRol();
            logger.info("Rol nuevo {}", this.rol);
            this.email = configService.getUserEmail();
            logger.info("Email nuevo {}", this.email);
            this.phone = configService.getUserPhone();
            logger.info("Telefono nuevo {}", this.phone);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        configSubject.addObserver(this);
    }


}
