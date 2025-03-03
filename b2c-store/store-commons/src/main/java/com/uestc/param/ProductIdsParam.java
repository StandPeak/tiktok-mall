package com.uestc.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Data
public class ProductIdsParam extends PageParam{

    @NotNull
    private List<Integer> categoryID;

}
