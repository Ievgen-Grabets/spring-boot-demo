package evg.test.util;

import evg.test.dto.DataDTO;
import java.util.Date;

public class FakeDataHolder {

    public static final DataDTO[] DATA_DTO_ARRAY = {
            new DataDTO("26cba770-0115-45b2-8cc0-185dac2243bb", "content1", "category1", "description1", new String[]{"tag2", "tag4"}, "studio1", new  String[]{"1", "2", "3"}, new Date()),
            new DataDTO("11baca0e-3dbc-4066-a035-ce5bcf07ab9d", "content2", "category1", "description2", new String[]{"tag3", "tag4"}, "studio2", new  String[]{"3", "4", "6"}, new Date()),
            new DataDTO("11baca0e-3dbc-4066-a034-ce5bcf07ab9d", "content3", "category2", "description3", new String[]{"tag1", "tag2"}, "studio2", new  String[]{"1", "2", "5"}, new Date())};

}
