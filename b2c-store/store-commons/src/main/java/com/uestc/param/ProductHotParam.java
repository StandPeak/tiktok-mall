package com.uestc.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Data
public class ProductHotParam {

    @NotEmpty
    private List<String> categoryName;
}
