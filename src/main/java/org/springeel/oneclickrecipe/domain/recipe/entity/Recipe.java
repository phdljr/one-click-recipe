package org.springeel.oneclickrecipe.domain.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springeel.oneclickrecipe.domain.user.entity.User;
import org.springeel.oneclickrecipe.global.entity.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_RECIPE")
@Entity
public class Recipe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String intro;

    @Column
    private Byte serving;

    @Column
    private Short time;

    @Column
    private String videoUrl;

    @Column
    private String folderName;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Recipe(
        final String title,
        final String intro,
        final Byte serving,
        final Short time,
        final String videoUrl,
        final String folderName,
        final String imageUrl,
        final User user
    ) {
        this.title = title;
        this.intro = intro;
        this.serving = serving;
        this.time = time;
        this.videoUrl = videoUrl;
        this.folderName = folderName;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public void updateRecipe(String title, String intro, Byte serving, Short time, String videoUrl,
        String imageUrl) {
        this.title = title;
        this.intro = intro;
        this.serving = serving;
        this.time = time;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
    }
}
