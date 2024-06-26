package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ClientPhoto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientPhotoRepository extends MongoRepository<ClientPhoto, String> {}
