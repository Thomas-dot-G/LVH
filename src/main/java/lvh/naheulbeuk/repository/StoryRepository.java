package lvh.naheulbeuk.repository;

import java.util.List;

import lvh.naheulbeuk.model.Story;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {
	
	public List<Story> findByUserId(final String id);

}
