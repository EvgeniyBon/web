package com.web.project.firstWeb.repository;

import com.web.project.firstWeb.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {

}
