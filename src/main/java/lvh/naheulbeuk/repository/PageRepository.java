package lvh.naheulbeuk.repository;

import java.util.List;
import java.util.Optional;

import lvh.naheulbeuk.model.Page;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<Page, String> {
	
	public List<Page> findByStoryId(final String id);
	
	public Optional<Page> findByStoryIdAndEntryPointTrue(final String id);
	
	public Optional<Page> findByStoryIdAndPageNumber(final String id, final String pageNumber);

	public void deleteByStoryId(final String id);
}
