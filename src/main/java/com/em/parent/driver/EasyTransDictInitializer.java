package com.em.parent.driver;


//import org.dromara.trans.service.impl.DictionaryTransService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@Slf4j
//@Configuration
//@RequiredArgsConstructor
public class EasyTransDictInitializer implements ApplicationRunner {
//    private final DictionaryTransService dictionaryTransService;

//    @Resource
//    private final DictValuesMapper dictValuesMapper;

    public void run(ApplicationArguments args) throws Exception {
//        List<DictValues> dictList = dictValuesMapper.selectList(new QueryWrapper<>());
//        Map<String,Map<String,String>> dictMaps = new HashMap<>();
//
//        for (DictValues dictValue : dictList) {
//            String dictKey = dictValue.getDictKey();
//
//            if(Objects.isNull(dictMaps.get(dictKey))) {
//                dictMaps.put(dictKey,new HashMap<>());
//            }
//
//            Map<String,String> dictValues = dictMaps.get(dictKey);
//            dictValues.put(dictValue.getValue(),dictValue.getLabel());
//        }
//        log.info("刷新字典：{}", dictMaps);
//        dictMaps.forEach((key,map)->dictionaryTransService.refreshCache(key,map));
    }
}
