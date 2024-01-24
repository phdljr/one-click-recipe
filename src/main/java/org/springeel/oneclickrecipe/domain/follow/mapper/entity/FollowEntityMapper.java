package org.springeel.oneclickrecipe.domain.follow.mapper.entity;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springeel.oneclickrecipe.domain.follow.entity.Follow;
import org.springeel.oneclickrecipe.domain.user.entity.User;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface FollowEntityMapper {
    @Mapping(source = "user", target = "user")
    @Mapping(source = "following", target = "following")
    Follow tofollow(User user, User following);
}
