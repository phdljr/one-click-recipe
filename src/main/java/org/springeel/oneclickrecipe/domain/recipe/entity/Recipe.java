package org.springeel.oneclickrecipe.domain.recipe.entity;

import jakarta.persistence.CascadeType;
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
import org.springeel.oneclickrecipe.domain.recipefood.entity.RecipeFood;
import org.springeel.oneclickrecipe.domain.recipelike.entity.RecipeLike;
import org.springeel.oneclickrecipe.domain.recipeprocess.entity.RecipeProcess;
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
    private String videoUrl;

    @Column
    private String folderName;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private List<RecipeProcess> recipeProcesses = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private List<RecipeFood> recipeFoods = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private List<RecipeLike> recipeLikes = new ArrayList<>();

    @Builder
    public Recipe(
        final String title,
        final String intro,
        final Byte serving,
        final String videoUrl,
        final String folderName,
        final String imageUrl,
        final User user
    ) {
        this.title = title;
        this.intro = intro;
        this.serving = serving;
        this.videoUrl = videoUrl;
        this.folderName = folderName;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public void updateRecipe(String title, String intro, Byte serving, String videoUrl,
        String imageUrl) {
        this.title = title;
        this.intro = intro;
        this.serving = serving;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
    }
}
