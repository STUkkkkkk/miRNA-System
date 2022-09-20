package stukk.Pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SearchDisease {

    //mirna.id,rnaname,title,doi,nlm ,pt ,writer ,year,abstract

    private Integer id;
    private String rnaname;
    private String title;
    private String doi;
    private String nlm;
    private String pt;
    private String writer;
    private Integer year;
    private String abs;

}
