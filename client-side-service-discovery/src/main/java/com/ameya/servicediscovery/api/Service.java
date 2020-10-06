package com.ameya.servicediscovery.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @JsonProperty
    private String name;
    @JsonProperty
    private List<Server> serverList;
}
