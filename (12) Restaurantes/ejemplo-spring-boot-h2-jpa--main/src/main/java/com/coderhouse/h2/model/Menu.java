package com.coderhouse.h2.model;

import lombok.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
public class Menu {
    private Producto[] productos;
}
