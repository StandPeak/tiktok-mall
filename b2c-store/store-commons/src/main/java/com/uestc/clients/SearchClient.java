package com.uestc.clients;

import com.uestc.param.ProductSearchParam;
import com.uestc.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author llf
 * @school uestc
 */
@FeignClient("search-service")
public interface SearchClient {

    @PostMapping("/search/product")
    R search(@RequestBody ProductSearchParam productSearchParam);
}
