package com.api.gamerating.externalapi;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class ExternalApiException implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        System.out.println("Random");

        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}
