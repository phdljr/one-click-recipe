package org.springeel.oneclickrecipe.domain.follow.mapper.entity;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.follow.entity.Follow;
import org.springeel.oneclickrecipe.domain.user.entity.User;

@Mapper(componentModel = SPRING)
public interface FollowEntityMapper {

    @Mapping(source = "user", target = "user")
    @Mapping(source = "following", target = "following")
    Follow tofollow(User user, User following);
}
