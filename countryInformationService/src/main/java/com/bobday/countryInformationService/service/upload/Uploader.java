package com.bobday.countryInformationService.service.upload;

public interface Uploader<S> {

    void upload();
    void upload(S source);
}
