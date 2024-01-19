package org.springeel.oneclickrecipe.domain.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springeel.oneclickrecipe.domain.review.entity.Review;
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
    private String videoPath;

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
        final String videoPath,
        final User user,
        final String folderName
    ) {
        this.title = title;
        this.intro = intro;
        this.serving = serving;
        this.videoPath = videoPath;
        this.user = user;
        this.folderName = folderName;
    }

    public void updateRecipe(String title, String intro, Byte serving, String videoPath) {
        this.title = title;
        this.intro = intro;
        this.serving = serving;
        this.videoPath = videoPath;
    }
}
