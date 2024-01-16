package org.springeel.oneclickrecipe.domain.userorder.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserOrderId implements Serializable {

    private Long user;
    private Long order;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserOrderId that = (UserOrderId) o;
        return Objects.equals(user, that.user) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, order);
    }
}
