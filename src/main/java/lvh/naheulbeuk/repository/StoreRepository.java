package lvh.naheulbeuk.repository;

import lvh.naheulbeuk.model.Store;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
	
}
