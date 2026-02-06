package com.ifmg.server.apiServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class SystemPathSearchDTO{
    @NonNull
    private Long id;
    @NonNull
    private String name;
}
