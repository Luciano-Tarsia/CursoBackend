package com.coderhouse.observerpattern.services;

import com.coderhouse.observerpattern.utils.ConfigObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ConfigSubject {

    private static final Logger logger = LoggerFactory.getLogger(ConfigSubject.class);

    private final Set<ConfigObserver> observers;

    public ConfigSubject() {
        observers = Collections.synchronizedSet(new HashSet<>());
    }

    public void addObserver(ConfigObserver observer) {
        logger.info("Se agrega un observador");
        observers.add(observer);
    }

    public void removeObserver(ConfigObserver observer) {
        logger.info("Se elimina un observador");
        observers.remove(observer);
    }

    public void notifyObservers(Object event) {
        synchronized (observers) {
            logger.info("Se notifica a los observadores");
            observers.forEach(observer -> observer.updateConfig(event));
        }
    }
}
