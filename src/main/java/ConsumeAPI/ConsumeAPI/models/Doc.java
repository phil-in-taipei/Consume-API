package ConsumeAPI.ConsumeAPI.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Doc {

    @JsonProperty("abstract")
    String abStract;

    @JsonProperty("web_url")
    String webUrl;

    String source;

    List<Multimedia> multimedia;

    Headline headline;

    @JsonProperty("pub_date")
    String pubDate;

    @JsonProperty("document_type")
    String documentType;

    @JsonProperty("news_desk")
    String newsDesk;

    @JsonProperty("section_name")
    String sectionName;

    Byline byline;

    @JsonProperty("type_of_material")
    String typeOfMaterial;


    @JsonProperty("word_count")
    int wordCount;

    private String imageUrl;

}
