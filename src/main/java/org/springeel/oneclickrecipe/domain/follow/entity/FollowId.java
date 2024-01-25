package org.springeel.oneclickrecipe.domain.follow.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowId implements Serializable {

    private Long user;
    private Long following;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FollowId followId = (FollowId) o;
        return Objects.equals(user, followId.user) && Objects.equals(following,
            followId.following);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, following);
    }
}
