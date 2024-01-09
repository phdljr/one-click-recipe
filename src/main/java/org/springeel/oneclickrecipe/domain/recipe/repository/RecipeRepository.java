package org.springeel.oneclickrecipe.domain.recipe.repository;

import java.util.List;
import org.springeel.oneclickrecipe.domain.recipe.entity.Recipe;
import org.springeel.oneclickrecipe.sample.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Test> findByName(String name);
}
