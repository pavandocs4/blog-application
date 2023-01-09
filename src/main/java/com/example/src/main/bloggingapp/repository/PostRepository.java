package com.example.src.main.bloggingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.src.main.bloggingapp.entity.Post;
import com.example.src.main.bloggingapp.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query(value = "select * from posts p where p.title=:title", nativeQuery = true)
	Optional<Post> findByTitle(@RequestParam("title") String title);

	@Query(value = "select * from posts p where p.category_id=:category_id", nativeQuery = true)
	List<Post> findByCategory(@RequestParam("category_id") Integer category_id);

	@Query(value= "select * from posts p where p.user_id=:user_id", nativeQuery= true)
	List<Post> findByUserId(@RequestParam("user_id") Integer user_id);

	@Query(value="select * from posts p where p.user_id=:userId and p.category_id=:catId", nativeQuery=true)
	List<Post> findByUsernameAndCategory(@RequestParam("user_id") int userId, @RequestParam("cat_Id") Integer catId);

	@Query(value="select * from posts p where p.title like :param", nativeQuery=true)
	List<Post> searchPostByParamTitle(@RequestParam("param") String param); 

}
