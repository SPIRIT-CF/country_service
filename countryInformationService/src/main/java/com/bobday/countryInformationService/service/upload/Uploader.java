package com.bobday.countryInformationService.service.upload;

public interface Uploader<S> {

    boolean upload();
    boolean upload(S source);
}
