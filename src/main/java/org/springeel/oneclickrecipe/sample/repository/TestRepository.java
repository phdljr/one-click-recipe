package org.springeel.oneclickrecipe.sample.repository;

import java.util.List;
import org.springeel.oneclickrecipe.sample.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findByName(String name);
}
