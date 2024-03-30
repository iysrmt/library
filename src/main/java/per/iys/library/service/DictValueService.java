package per.iys.library.service;

import per.iys.library.domain.DictValue;

import java.util.List;

public interface DictValueService {

    /**
     * 根据 typeCode 查找字典表
     *
     * @param typeCode 类型代码
     */
    List<DictValue> queryDictValueListByTypeCode(String typeCode);


    /**
     * 更新字典<br>
     * 先删除指定 typeCode 数据<br>
     * 再插入新的 typeCode 数据
     */
    void saveDictValueByTypeCodeList(String typeCode, List<DictValue> dictValueList);
}
