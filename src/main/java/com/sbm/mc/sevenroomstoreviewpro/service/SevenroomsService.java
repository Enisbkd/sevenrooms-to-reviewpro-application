package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.Client;

public interface SevenroomsService {
    String getClientFromSevenRoomsApi(String client_id);

    Client extractClientFields(String clientPayload);
}
