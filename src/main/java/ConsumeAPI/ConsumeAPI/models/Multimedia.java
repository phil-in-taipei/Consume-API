package ConsumeAPI.ConsumeAPI.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Multimedia {
    int rank;
    String subtype;
    String caption;
    String credit;
    String type;
    String url;
    int height;
    int width;

    String subType;
}
