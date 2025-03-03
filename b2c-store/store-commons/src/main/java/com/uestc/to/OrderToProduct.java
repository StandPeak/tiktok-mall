package com.uestc.to;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author llf
 * @school uestc
 */
@Data
public class OrderToProduct implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Integer productId;
    private Integer num;
}
