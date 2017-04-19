package evg.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class DataDTO {

    public DataDTO(String id, String name){
        this.id = id;
        this.name=name;
    }

    public DataDTO(String id, String name, String category, String description, String[] tag, String studio, String[] promotedIds, Date publishTime) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.tag = tag;
        this.studio = studio;
        this.promotedIds = promotedIds;
        this.publishTime = publishTime;
    }

    private String id;
    @NonNull
    private String name;

    private String category;

    private String description;

    private String[] tag;

    private String studio;

    private String[] promotedIds;

    private Date publishTime;

    public Date getPublishTime() {
        return new Date(publishTime.getTime());
    }

    @Override
    public String toString(){
        return "Data dto id: "+id;
    }

}
