package com.uestc.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Data
public class ProductCollectParam {

    @NotEmpty
    private List<Integer> productIds;
}
