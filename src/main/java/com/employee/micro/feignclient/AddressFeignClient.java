package com.employee.micro.feignclient;

import com.employee.micro.view.AddressView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address", url = "http://localhost:8802", path = "/address-app/api/")
public interface AddressFeignClient {

    @GetMapping("/address/{id}")
    AddressView getAddressByEmployeeId(@PathVariable("id") Integer id);
}
