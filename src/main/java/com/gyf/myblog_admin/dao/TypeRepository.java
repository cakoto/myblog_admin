package com.gyf.myblog_admin.dao;

import com.gyf.myblog_admin.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);

}
