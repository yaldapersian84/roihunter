package com.roihunter.facebook;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "fbClient", url = "${facebook.graph.base.url}")
public interface FacebookClient {

	@RequestMapping(method = RequestMethod.GET, path = "/{fbId}?fields=id,name,gender,picture.width(400).height(400)")
	UserDataResponse getData(@PathVariable("fbId") String fbId, @RequestParam("access_token") String token);

	@RequestMapping(method = RequestMethod.GET, path = "/{fbId}/photos?access_token={accessToken}")
	UserPhotosResponse getPhotos(@PathVariable("fbId") String fbId, @PathVariable("accessToken") String token);
}
