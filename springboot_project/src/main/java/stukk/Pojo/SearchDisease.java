package stukk.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDisease {

    //mirna.id,rnaname,title,doi,nlm ,pt ,writer ,year,abstract

    private Integer cnt;
    private List<Article> diseases;

}
