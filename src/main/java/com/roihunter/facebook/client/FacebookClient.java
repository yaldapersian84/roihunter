package com.roihunter.facebook.client;

import com.roihunter.facebook.model.response.UserDataResponse;
import com.roihunter.facebook.model.response.UserPhotosResponse;
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

	@RequestMapping(method = RequestMethod.GET, path = "/{fbId}/photos?fields=likes.summary(true),link,album,id,name,images&type=uploaded")
	UserPhotosResponse getPhotos(@PathVariable("fbId") String fbId, @RequestParam("access_token") String token, @RequestParam("limit") int size);
}
