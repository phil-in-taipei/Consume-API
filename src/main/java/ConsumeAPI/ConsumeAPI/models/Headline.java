package ConsumeAPI.ConsumeAPI.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Headline {
    String main;

    @JsonProperty("print_headline")
    String printHeadline;
}
