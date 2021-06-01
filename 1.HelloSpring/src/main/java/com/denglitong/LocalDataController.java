package com.denglitong;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/1
 */
@RestController
public class LocalDataController {
    @RequestMapping(value = "/date/{localDate}", method = RequestMethod.GET)
    public String get(@PathVariable
                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return localDate.toString();
    }
}
