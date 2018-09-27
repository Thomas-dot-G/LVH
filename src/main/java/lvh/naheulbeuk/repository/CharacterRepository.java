package lvh.naheulbeuk.repository;

import lvh.naheulbeuk.model.Character;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepository extends MongoRepository<Character, String> {
	

}
