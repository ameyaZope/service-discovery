package com.ameya.servicediscovery.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    @JsonProperty
    private String host;
    @JsonProperty
    private int port;
}
