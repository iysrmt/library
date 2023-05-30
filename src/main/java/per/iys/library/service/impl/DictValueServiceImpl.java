package per.iys.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.iys.library.domain.DictValue;
import per.iys.library.mapper.DictValueMapper;
import per.iys.library.service.DictValueService;

import java.util.List;

@Service
public class DictValueServiceImpl implements DictValueService {

    private final DictValueMapper dictValueMapper;

    @Autowired
    public DictValueServiceImpl(DictValueMapper dictValueMapper) {
        this.dictValueMapper = dictValueMapper;
    }

    @Override
    public List<DictValue> queryDictValueListByTypeCode(String typeCode) {
        return dictValueMapper.selectDictValueListByTypeCode(typeCode);
    }

    @Transactional
    @Override
    public void saveDictValueByTypeCodeList(String typeCode, List<DictValue> dictValueList) {
        dictValueMapper.deleteDictValueByTypeCode(typeCode);
        dictValueMapper.insertDictValueByList(dictValueList);
    }
}
