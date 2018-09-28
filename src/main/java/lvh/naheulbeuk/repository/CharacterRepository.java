package lvh.naheulbeuk.repository;

import java.util.List;

import lvh.naheulbeuk.model.Character;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepository extends MongoRepository<Character, String> {
	
	public List<Character> findByUserId(final String id);

}
