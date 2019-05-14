package com.meils.oa.biz;

import com.meils.oa.entity.SC;

import java.util.List;

public interface SCBiz {

    void addSC(SC sc);

    SC findOne(SC sc);

    List<SC> getSCList(Integer page, Integer pageSize, Integer sId);
    List<SC> getSCList1( Integer sId);
    Integer selectNums(Integer sId);

    List<SC> getSCList2(Integer[] ids, Integer page, Integer pageSize);
    Integer getSCList3(Integer[] ids);
    void delete(Integer id);
}
