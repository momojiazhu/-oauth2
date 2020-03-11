package com.gaobo.oss.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class FileProperties {

    @Value("${savepath}")
    private String savepath;

}
