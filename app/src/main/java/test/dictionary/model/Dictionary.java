package test.dictionary.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dictionary {

    @SerializedName("result_type")
    private String resultType;

    @SerializedName("sounds")
    private List<String> sounds;

    @SerializedName("list")
    private List<DictionaryResultItems> list;

    @SerializedName("tags")
    private List<String> tags;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<String> getSounds() {
        return sounds;
    }

    public void setSounds(List<String> sounds) {
        this.sounds = sounds;
    }

    public List<DictionaryResultItems> getList() {
        return list;
    }

    public void setList(List<DictionaryResultItems> list) {
        this.list = list;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return
                "Dictionary{" +
                        "result_type = '" + resultType + '\'' +
                        ",sounds = '" + sounds + '\'' +
                        ",list = '" + list + '\'' +
                        ",tags = '" + tags + '\'' +
                        "}";
    }
}