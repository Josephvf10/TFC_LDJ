
package com.dam.proyectotfc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsDetalles {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("platforms")
    @Expose
    private List<Platform> platforms = null;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
