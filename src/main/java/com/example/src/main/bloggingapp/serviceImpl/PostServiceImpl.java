package com.example.src.main.bloggingapp.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.src.main.bloggingapp.dto.PostDTO;
import com.example.src.main.bloggingapp.dtoconverter.PostDTOConverter;
import com.example.src.main.bloggingapp.entity.Category;
import com.example.src.main.bloggingapp.entity.Post;
import com.example.src.main.bloggingapp.entity.PostsPageRequest;
import com.example.src.main.bloggingapp.entity.User;
import com.example.src.main.bloggingapp.exception.ResourceAlreadyPresentException;
import com.example.src.main.bloggingapp.exception.ResourceNotFoundException;
import com.example.src.main.bloggingapp.repository.CategoryRepository;
import com.example.src.main.bloggingapp.repository.PostRepository;
import com.example.src.main.bloggingapp.repository.UserRepository;
import com.example.src.main.bloggingapp.service.PostService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	PostDTOConverter postDTOConverter;

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Override
	public PostDTO addNewPost(HashMap<String, Object> input) {
		PostDTO dto = OBJECT_MAPPER.convertValue(input.get("post"), PostDTO.class);

		Integer userId = OBJECT_MAPPER.convertValue(input.get("userId"), Integer.class);
		Integer categoryId = (Integer) input.get("categoryId");
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post = postDTOConverter.objFromDTO(dto);
		post.setUser(user).setCategory(cat).setInsertDate(new Date());
		if (postRepo.findByTitle(dto.getTitle()).isPresent())
			throw new ResourceAlreadyPresentException("Post", "id", dto.getId());

		Post savedPost = this.postRepo.save(post);
		return postDTOConverter.objToDTO(savedPost);

	}

	@Override
	public PostDTO updatePost(PostDTO dto) {
		// TODO Auto-generated method stub
		Post post=postRepo.findByTitle(dto.getTitle()).orElseThrow(()-> new ResourceNotFoundException("Post", "title", dto.getTitle()));
		post.setImage(dto.getImage()).setContent(dto.getContent());
		Post savedPost= postRepo.save(post); 
		return postDTOConverter.objToDTO(savedPost);
	}

	@Override
	public PostDTO getPostById(Integer id) {
		Post post= postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		return postDTOConverter.objToDTO(post);
	}

	@Override
	public List<PostDTO> getPostsByCategory(String category) {
		Category cat= categoryRepo.findByTitle(category).orElseThrow(()->new ResourceNotFoundException("Category", "title", category));
		List<Post> post= postRepo.findByCategory(cat.getId());
		List<PostDTO> dtoList= post.stream().map(p-> postDTOConverter.objToDTO(p)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public List<PostDTO> getPostsByUsername(String username) {
		User user = Optional.ofNullable(userRepo.findByUsername(username)).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		List<Post> post= postRepo.findByUserId(user.getId());
		List<PostDTO> dtoList= post.stream().map(p-> postDTOConverter.objToDTO(p)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public List<PostDTO> getPostsByUsernameAndCategory(String username, String category) {
		Category cat= categoryRepo.findByTitle(category).orElseThrow(()->new ResourceNotFoundException("Category", "title", category));
		User user = Optional.ofNullable(userRepo.findByUsername(username)).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		List<Post> post= postRepo.findByUsernameAndCategory(user.getId(), cat.getId());
		List<PostDTO> dtoList= post.stream().map(p-> postDTOConverter.objToDTO(p)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public PostsPageRequest getAllPosts(Integer pageNo, Integer pageSize, String sortByEntity, String order) {
	
		Sort sortTech = order.equalsIgnoreCase("asc") ? Sort.by(sortByEntity).ascending() : Sort.by(sortByEntity).descending();
		
		Pageable page= PageRequest.of(pageNo, pageSize, sortTech);
		Page<Post> pageOfPost=postRepo.findAll(page);
		List<Post> posts= pageOfPost.getContent();
		List<PostDTO> dtoList= posts.stream().map(p-> postDTOConverter.objToDTO(p)).collect(Collectors.toList());
		PostsPageRequest request= new PostsPageRequest();
		request.setContents(dtoList)
			   .setPageNo(pageNo)
			   .setPageSize(pageSize)
			   .setTotalPages(pageOfPost.getTotalPages())
			   .setTotalRecords(pageOfPost.getNumberOfElements())
			   .setLastPage(pageOfPost.isLast());
				
		return request;
	}

	@Override
	public String deletePost(Integer id) {
		postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		postRepo.deleteById(id);
		return "Post deleted successfully";
	}

	@Override
	public List<PostDTO> searchPost(String param) {
		List<Post> result = postRepo.searchPostByParamTitle(param);
		List<PostDTO> resultDTOList = result.stream().map(p -> postDTOConverter.objToDTO(p))
				.collect(Collectors.toList());
		return resultDTOList;
	}

}
