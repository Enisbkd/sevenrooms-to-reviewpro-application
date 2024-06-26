package com.sbm.mc.sevenroomstoreviewpro.service;

public interface SevenroomsTokenService {
    public String generateToken();

    public String extractTokenFromJson(String json);
}
