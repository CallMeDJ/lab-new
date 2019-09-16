package com.bigbanana.lab.Session8.service;

import com.bigbanana.lab.Session8.request.ViewItemRequest;
import com.bigbanana.lab.base.ResultDTO;

public interface ItemService {

	 ResultDTO<Void> viewItem(ViewItemRequest viewItemRequest);

}
