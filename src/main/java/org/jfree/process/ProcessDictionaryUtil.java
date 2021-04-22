package org.jfree.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessDictionaryUtil {
    public void processTree(List<ProcessDictionary> processDictionaries) {
        // 查询出同一类型的, 然后根据deep进行排序
        // 层级大于2, 则进行报错提示(不允许出现三层以上.)
        // 递推公式: deep + 1
        // 终止条件: processDictionaries == null
    
        
        
    }
    
    public void test(List<ProcessDictionary> processDictionaries, int deep) {
        if (processDictionaries.isEmpty() || deep > 2) {
            return;
        }
        
        
    }
    
}
